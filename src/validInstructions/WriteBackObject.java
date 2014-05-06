package validInstructions;

public class WriteBackObject
{

    private String destinationLabel;
    private int   destination;

    public WriteBackObject(String destinationLabel, int destination)
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

    public int getDestination()
    {
        return destination;
    }

    public void setDestination(int destination)
    {
        this.destination = destination;
    }
}
