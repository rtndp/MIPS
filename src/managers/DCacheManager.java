package managers;

import caches.DCache;
import stages.CPU;
import validInstructions.DI;

public class DCacheManager {

	private DCache cache;
	private DCacheRequestData request;
	// private DCacheRequestData request;

	private int dCacheAccessRequests;
	private int dCacheAccessHits;
	public static final DCacheManager instance = new DCacheManager();

	private DCacheManager() {
		cache = new DCache();
		request = new DCacheRequestData();
		dCacheAccessRequests = 0;
		dCacheAccessHits = 0;
	}

	public boolean canProceed(DI inst) throws Exception {
		int address = (int) inst.address;
		if (request.lastRequestInstruction == null) {
			// first time request
			request.lastRequestInstruction = inst;
			request.lastRequestInstructionEntryClock = CPU.CLOCK;
			request.clockCyclesToBlock = 0;

			if (DI.isStore(inst)) {

				// check if address of this instruction actually exists in
				// DCache
				// if it does, then just write to DCache & mark it dirty. --> it
				// is a cache hit
				if (cache.doesAddressExist(address))
					request.clockCyclesToBlock += ConfigManager.instance.DCacheLatency;
				else {
					// if address doesnt exist in DCache, then check if a block
					// is
					// free in Dcache
					// if free, then just write to Dcache and mark it dirty
					if (cache.isThereAFreeBlock(address)) {
						request.clockCyclesToBlock += ConfigManager.instance.DCacheLatency;
					} else {
						// if doesnt exist && lru block is dirty, then writeback
						// to
						// memory , then update dcache & mark dirty
						// if lru block is not dirty, then just write to dcache
						if (cache.isLRUBlockDirty(address)) {
							request.clockCyclesToBlock += MemoryBusManager.instance
									.getDelay();
							request.clockCyclesToBlock += 2 * (ConfigManager.instance.DCacheLatency + ConfigManager.instance.MemoryLatency);
						} else {
							request.clockCyclesToBlock += ConfigManager.instance.DCacheLatency;
						}
					}

				}
			} else {
				// Cache hit and found the same address - > return the value
				// from cache
				// latency is cache access time
				if (cache.doesAddressExist(address)) {
					request.clockCyclesToBlock += ConfigManager.instance.DCacheLatency;
				} else {
					// Cache miss and cache block is free - > write in the cache
					// and
					// return the value
					// latency is 2(t + k)
					if (cache.isThereAFreeBlock(address)) {
						request.clockCyclesToBlock += MemoryBusManager.instance
								.getDelay();
						request.clockCyclesToBlock += 2 * (ConfigManager.instance.DCacheLatency + ConfigManager.instance.MemoryLatency);
					} else {
						// Cache miss and cache block is full - > Check if dirty
						// if dirty - > write back and update cache
						// else(not dirty) - > just update cache
						if (cache.isLRUBlockDirty(address)) {
							request.clockCyclesToBlock += MemoryBusManager.instance
									.getDelay();
							request.clockCyclesToBlock += (ConfigManager.instance.MemoryLatency);
							request.clockCyclesToBlock += 2 * (ConfigManager.instance.DCacheLatency + ConfigManager.instance.MemoryLatency);
						} else {
							request.clockCyclesToBlock += ConfigManager.instance.DCacheLatency;
						}
					}

				}
			}

		} else if (!request.lastRequestInstruction.equals(inst)) {
			throw new Exception(this.getClass().getSimpleName()
					+ " Cannot get different instructions from memory unit");
		} else {

			if (!request.hasAccessVariablesSet) {
				dCacheAccessRequests++;
				if (cache.doesAddressExist(address))
					dCacheAccessHits++;
				request.hasAccessVariablesSet = true;
			}
			// For Store, find block, mark dirty & update address & lru
			// for Load, find block, update address & lru
			cache.updateBlock(address, DI.isStore(inst));
		}

		return validateClockCyclesToBlock();
	}

	private boolean validateClockCyclesToBlock() throws Exception {
		if (CPU.CLOCK - request.lastRequestInstructionEntryClock == request.clockCyclesToBlock) {
			// do any cache updates at this point?
			// cache.setInCache(request.lastRequestInstruction); // hack
			request.resetValues();
			return true;
		} else
			return false;
	}

	public String getDCacheStatistics() {
		String format = "%-60s %4s";
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(format,
				"Total number of access requests for Data cache:",
				dCacheAccessRequests));
		sb.append('\n');
		sb.append(String.format(format, "Number of Data cache hits:",
				dCacheAccessHits));
		return sb.toString();
	}

}

class DCacheRequestData {
	DI lastRequestInstruction;
	int lastRequestInstructionEntryClock;
	int clockCyclesToBlock;
	boolean hasAccessVariablesSet;

	public DCacheRequestData() {
		resetValues();
	}

	public void resetValues() {

		lastRequestInstruction = null;
		lastRequestInstructionEntryClock = -1;
		clockCyclesToBlock = -1;
		hasAccessVariablesSet = false;
	}

}