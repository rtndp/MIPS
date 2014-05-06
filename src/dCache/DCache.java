package dCache;

public class DCache
{
    DCacheSet[] dCacheSet;

    public DCache()
    {
        dCacheSet = new DCacheSet[2];
        dCacheSet[0] = new DCacheSet();
        dCacheSet[1] = new DCacheSet();
    }
    
    private DCacheSet getSet(int address)
    {
        int setId = address & 0b10000;
        setId = setId >> 4;
        return dCacheSet[setId];
    }

    private int getBaseAddress(int address)
    {
        int baseAddress = address >> 2;
        baseAddress = baseAddress << 2;
        return baseAddress;
    }

    public boolean doesAddressExist(int address)
    {
        DCacheSet set = getSet(address);
        int baseAddress = getBaseAddress(address);
        return set.doesAddressExist(baseAddress);
    }

    public boolean isThereAFreeBlock(int address)
    {
        DCacheSet set = getSet(address);
        return set.hasFreeBlock();
    }

    public boolean isLRUBlockDirty(int address)
    {
        DCacheSet set = getSet(address);
        return set.isLRUBlockDirty();
    }

    public void updateBlock(int address, boolean store) throws Exception
    {
        // TODO Auto-generated method stub
        DCacheSet set = getSet(address);
        int baseAddress = getBaseAddress(address);

        DCacheBlock block = null;
        // update same address block, if not then free block , if not then
        // lrublock
        if (doesAddressExist(address))
        {
            block = set.getAddressBlock(baseAddress);
        }
        else if (isThereAFreeBlock(address))
        {
            block = set.getEmptyBlock(baseAddress);
        }
        else
        {
            block = set.getLRUBlock();
        }
        if (block == null)
            throw new Exception("DCache cannot find a null block");
        block.baseAddress = baseAddress;
        block.dirty = store;
        set.toggleLRU(block);
    }

}