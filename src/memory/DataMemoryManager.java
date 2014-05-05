package memory;

import java.util.Map;
import java.util.TreeMap;

import Utils.Utils;

public class DataMemoryManager
{
    public static final DataMemoryManager instance = new DataMemoryManager();

    private DataMemoryManager()
    {

    }

    // address to data
    // address starts at 0x100 or 256 decimal
    private Map<Integer, Integer> memoryDataMap = new TreeMap<Integer, Integer>();

    // Used only at the start by the parser
    public void setValueToAddress(int address, int data)
    {
        memoryDataMap.put(address, data);
    }

    // get Value from address - used for testing and pipeline only
    // implementation
    public int getValueFromAddress(int address) throws Exception
    {
        if (!memoryDataMap.containsKey(address))
            throw new Exception("Data Memory Address Not Found " + address);

        return memoryDataMap.get(address);
    }

    // get block from memory given a base address, eg, if 257 asked, then give
    // 256,257,258,259
    /*
     * Default block size is size of 4
     */
    public Map<Integer, Integer> getMemoryBlockOfAddress(int address)
            throws Exception
    {
        return getMemoryBlockOfAddress(address, 4);
    }

    /**
     * 
     * @param address
     * @param blocksize
     *            should be a power of 2 only
     * @return
     */
    public Map<Integer, Integer> getMemoryBlockOfAddress(int address,
            int blocksize) throws Exception
    {
        Map<Integer, Integer> returnMap = new TreeMap<Integer, Integer>();

        if (!Utils.isPowerOf2(blocksize))
            throw new Exception("Need a power of 2 only for block size "
                    + blocksize);
        int temp = address - (address % blocksize);

        for (int i = temp; i < temp + blocksize; i++)
        {
            if (!memoryDataMap.containsKey(i))
                System.out.println("Location not found in data memory: " + i);
            else
                returnMap.put(i, memoryDataMap.get(i));

        }
        return returnMap;
    }

    /**
     * printout contents of all memory locations
     */
    public void dumpAllMemory()
    {
        String leftAlignFormat = "| %-5d | %-10d |%n";
        for (Integer key : memoryDataMap.keySet())
        {
            System.out.format(leftAlignFormat, key, memoryDataMap.get(key));
        }
    }
}
