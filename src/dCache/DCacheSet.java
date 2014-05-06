package dCache;

class DCacheSet
{
    DCacheBlock[] dCacheBlocks;
    int           lru;

    public DCacheSet()
    {
        dCacheBlocks = new DCacheBlock[2];
        dCacheBlocks[0] = new DCacheBlock(-1);
        dCacheBlocks[1] = new DCacheBlock(-1);
        lru = 0;
    }

    public void toggleLRU(DCacheBlock block)
    {
        lru = (dCacheBlocks[0].equals(block)) ? 1 : 0;
    }

    public boolean isFree(int id)
    {
        return dCacheBlocks[id].isFree();
    }

    public boolean hasFreeBlock()
    {
        return isFree(0) || isFree(1);
    }

    public boolean doesAddressExist(int baseAddress)
    {
        return (dCacheBlocks[0].baseAddress == baseAddress)
                || (dCacheBlocks[0].baseAddress == baseAddress);
    }

    public boolean isLRUBlockDirty()
    {
        return dCacheBlocks[lru].dirty;
    }

    public DCacheBlock getAddressBlock(int baseAddress)
    {

        if (!doesAddressExist(baseAddress))
            return null;

        if (dCacheBlocks[0].baseAddress == baseAddress)
            return dCacheBlocks[0];
        return dCacheBlocks[1];
    }

    public DCacheBlock getEmptyBlock(int baseAddress)
    {
        if (!hasFreeBlock())
            return null;
        if (dCacheBlocks[0].baseAddress == -1)
            return dCacheBlocks[0];
        return dCacheBlocks[1];
    }

    public DCacheBlock getLRUBlock()
    {
        return dCacheBlocks[lru];
    }

}