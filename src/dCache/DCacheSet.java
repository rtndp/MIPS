package dCache;

public class DCacheSet {
	DCacheBlock[] dCacheBlocks;
	int lru;

	public static final DCacheSet instance = new DCacheSet();

	private DCacheSet() {

		dCacheBlocks = new DCacheBlock[2];
		for (int i = 0; i < 2; i++) {
			dCacheBlocks[i] = new DCacheBlock();
		}

	}
}
