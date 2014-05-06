package dCache;

import memory.DataMemoryManager;

public class DCache {
	public static final DCache instance = new DCache();

	DCacheSet[] dCacheSet = new DCacheSet[2];

	public DCache() {
		for (int i = 0; i < 2; i++) {
			dCacheSet[i] = new DCacheSet();
		}
	}

	public boolean isDataInCache(int address) {

		int setIndex;
		int tempAddress = address;

		// To store the remaining 26/28 bits of address
		int tag = address >> 5;

		// What Set?
		setIndex = address & 0b10000;

		DCacheSet dCacheSet = this.dCacheSet[setIndex];

		for (int i = 0; i < 2; i++) {
			if (tag == dCacheSet.dCacheBlocks[i].tag)
				return true;
		}

		return false;
	}

	public void populateDCache(int address) throws Exception {

		int setIndex;
		int tempAddress = address;

		// To store the remaining 26/28 bits of address
		int tag = tempAddress >> 5;

		// What Set?
		setIndex = tempAddress & 0b10000;


		DCacheSet dCacheSet = this.dCacheSet[setIndex];
		
		DCacheBlock dCacheBlock = new DCacheBlock(
				DataMemoryManager.instance.getMemoryBlockOfAddress(address));
		
		dCacheSet.dCacheBlocks[dCacheSet.lru] = dCacheBlock;
		
		if(dCacheSet.lru == 0)
			dCacheSet.lru = 1;
		else
			dCacheSet.lru = 0;
	}
}
