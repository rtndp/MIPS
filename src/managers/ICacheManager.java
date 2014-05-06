package managers;

import stages.ProcessorParams;
import validInstructions.DI;
import caches.ICache;

/**
 * Called by main while fetching instructions if instructions not present in
 * cache keep track of when to populate the instruction in the cache
 * 
 * 
 * 
 * Instruction isInstructionInCache(int pc){ if instruction is not found in the
 * cache track of when to populate the instruction in the cache by marking it in
 * the tracker return null; else return instruction from cache
 * 
 * }
 */

public class ICacheManager {

	int lastRequestInstruction;
	int lastRequestCycle;
	int clockCyclesToBlock;
	int delayToBus;
	boolean cacheHit;
	private int iCacheAccessRequests;
	private int iCacheAccessHits;

	private static volatile ICacheManager instance;

	public static ICacheManager getInstance() {
		if (null == instance)
			synchronized (ICacheManager.class) {
				if (null == instance)
					instance = new ICacheManager();
			}

		return instance;
	}

	private ICacheManager() {

		resetValues();

	}

	private void resetValues() {

		lastRequestInstruction = -1;
		lastRequestCycle = -1;
		clockCyclesToBlock = -1;
		delayToBus = -1;
		cacheHit = false;

	}

	public DI getInstructionFromCache(int pc) throws Exception {

		if (lastRequestInstruction == -1) {
			iCacheAccessRequests++;
			if (ICache.getInstance().isInstructionInCache(pc)) {

				lastRequestInstruction = pc;
				lastRequestCycle = ProcessorParams.CC;
				clockCyclesToBlock = ConfigManager.instance.ICacheLatency - 1;
				cacheHit = true;
				iCacheAccessHits++;

				if (clockCyclesToBlock == 0) {

					resetValues();
					return ProgramManager.instance.getInstructionAtAddress(pc);

				} else
					return null;

			} else {

				lastRequestInstruction = pc;
				lastRequestCycle = ProcessorParams.CC;
				delayToBus = MemoryBusManager.instance.getDelay();
				clockCyclesToBlock = 2
						* (ConfigManager.instance.ICacheLatency + ConfigManager.instance.MemoryLatency)
						+ delayToBus - 1;

				return null;

			}

		} else {

			if (ProcessorParams.CC - lastRequestCycle == clockCyclesToBlock) {
				if (!cacheHit)
					ICache.getInstance().populateICache(pc);
				resetValues();
				return ProgramManager.instance.getInstructionAtAddress(pc);

			} else
				return null;

		}

	}

	public String getICacheStatistics() {
		String format = "%-60s %4s";
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(format,
				"Total number of access requests for instruction cache:",
				getICacheAccessRequests()));
		sb.append('\n');
		sb.append(String.format(format, "Number of instruction cache hits:",
				getICacheAccessHits()));
		return sb.toString();
	}

	public int getICacheAccessRequests() {
		return iCacheAccessRequests;
	}

	public int getICacheAccessHits() {
		return iCacheAccessHits;
	}
}
