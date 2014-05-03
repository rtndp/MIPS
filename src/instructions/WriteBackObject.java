package instructions;

public class WriteBackObject
{

    public String destinationLabel;
    public long   destination;

    public WriteBackObject(String destinationLabel, long destination)
    {
        this.destinationLabel = destinationLabel;
        this.destination = destination;
    }
}
