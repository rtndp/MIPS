package instructions;

public class ANDI extends TwoRegImmediateInstruction
{

    public ANDI(String sourceLabel, String destinationLabel, int immediate)
    {
        super(sourceLabel, destinationLabel, immediate);
    }

    public ANDI(ANDI obj)
    {
        super(obj);
    }

    public int getImmediate()
    {
        return this.immediate;
    }

    @Override
    public String toString()
    {
        return "ANDI " + dest.getDestinationLabel() + ", "
                + src1.getSourceLabel() + ", " + immediate;
    }

    @Override
    public void executeInstruction()
    {
        dest.setDestination(src1.getSource() & immediate);
    }

}
