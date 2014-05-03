package instructions;


public class LW extends TwoRegImmediateInstruction
{

    public LW(String sourceLabel, String destinationLabel, int immediate)
    {
        super(sourceLabel, destinationLabel, immediate);
    }

    public LW(LW obj)
    {
        super(obj);
    }

    @Override
    public String toString()
    {
        return "LW " + dest.getDestinationLabel() + ", " + immediate + "("
                + src1.getSourceLabel() + ")";
    }

    @Override
    public void executeInstruction()
    {
    }

}
