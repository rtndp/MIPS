package instructions;


public class SD extends TwoRegImmediateInstruction
{

    public SD(String sourceLabel, String destinationLabel, int immediate)
    {
        super(sourceLabel, destinationLabel, immediate);
    }

    public SD(SD obj)
    {
        super(obj);
    }

    @Override
    public String toString()
    {
        return "SD " + dest.getDestinationLabel() + ", " + immediate + "("
                + src1.getSourceLabel() + ")";
    }

    @Override
    public void executeInstruction()
    {
    }
}
