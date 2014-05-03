package instructions;

public class SourceObject
{

    private String sourceLabel;
    private long   source;

    public SourceObject(String sourceLabel, long source)
    {
        this.sourceLabel = sourceLabel;
        this.source = source;
    }

    public SourceObject(SourceObject obj)
    {
        super();
        this.sourceLabel = obj.sourceLabel;
        this.source = obj.source;
    }

    public String getSourceLabel()
    {
        return sourceLabel;
    }

    public void setSourceLabel(String sourceLabel)
    {
        this.sourceLabel = sourceLabel;
    }

    public long getSource()
    {
        return source;
    }

    public void setSource(long source)
    {
        this.source = source;
    }

}
