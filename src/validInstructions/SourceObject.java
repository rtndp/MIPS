package validInstructions;

public class SourceObject
{

    private String sourceLabel;
    private int   source;

    public SourceObject(String sourceLabel, int source)
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

    public int getSource()
    {
        return source;
    }

    public void setSource(int source)
    {
        this.source = source;
    }

}
