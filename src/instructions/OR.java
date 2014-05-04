package instructions;

public class OR extends ThreeRegInstruction
{
    public OR(String sourceLabel1, String sourceLabel2, String destinationLabel)
    {
        super(sourceLabel1, sourceLabel2, destinationLabel);
        this.functionalUnitType = FunctionalUnitType.IU;
    }

    public OR(OR obj)
    {
        super(obj);
    }

    @Override
    public String toString()
    {
        return "OR " + dest.getDestinationLabel() + ", "
                + src1.getSourceLabel() + ", " + src2.getSourceLabel();
    }

    @Override
    public void executeInstruction()
    {
        dest.setDestination(src1.getSource() | src2.getSource());
    }

}
