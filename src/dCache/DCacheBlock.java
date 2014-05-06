package dCache;

class DCacheBlock
{
    int     baseAddress;
    boolean dirty;

    public DCacheBlock(int baseAddress)
    {
        this.baseAddress = baseAddress;
        this.dirty = false;
    }

    public boolean isFree()
    {
        return (baseAddress == -1);
    }

}