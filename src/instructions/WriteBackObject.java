package instructions;

public class WriteBackObject
{

    private String destinationLabel;
    private long   destination;

    public WriteBackObject(String destinationLabel, long destination)
    {
        this.setDestinationLabel(destinationLabel);
        this.setDestination(destination);
    }

    public WriteBackObject(WriteBackObject obj)
    {
        super();
        this.destinationLabel = obj.destinationLabel;
        this.destination = obj.destination;
    }

    public String getDestinationLabel()
    {
        return destinationLabel;
    }

    public void setDestinationLabel(String destinationLabel)
    {
        this.destinationLabel = destinationLabel;
    }

    public long getDestination()
    {
        return destination;
    }

    public void setDestination(long destination)
    {
        this.destination = destination;
    }
}
