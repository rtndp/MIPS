package instructions;

public class SW extends TwoRegImmediateInstruction
{

    public SW(String sourceLabel, String destinationLabel, int immediate)
    {
        super(sourceLabel, destinationLabel, immediate);
        this.functionalUnitType = FunctionalUnitType.IU;
    }

    public SW(SW obj)
    {
        super(obj);
    }

    @Override
    public String toString()
    {
        return "SW" + dest.getDestinationLabel() + ", " + immediate + "("
                + src1.getSourceLabel() + ")";
    }

    @Override
    public void executeInstruction()
    {
    }

}
