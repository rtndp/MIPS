package dCache;

import java.util.Map;
import java.util.TreeMap;

public class DCacheBlock {

	Map<Integer, Integer> words;
	
	int tag;
	boolean dirty;
	int startAddress;

	public DCacheBlock() {
		words = new TreeMap<Integer, Integer>();
	}
	
	public DCacheBlock(Map<Integer, Integer> map){
		this.words = map;
	}

}
