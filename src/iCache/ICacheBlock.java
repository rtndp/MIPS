package iCache;

public class ICacheBlock {

	int[] words;
	int tag;

	public ICacheBlock() {
		words = new int[4];
		for(int i =0; i< 4; i++)
			words[i] = -1;
		tag = 1;
	}
}