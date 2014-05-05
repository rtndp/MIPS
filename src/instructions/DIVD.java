package instructions;

public class DIVD extends ThreeRegInstruction
{
    public DIVD(String sourceLabel1, String sourceLabel2,
            String destinationLabel)
    {
        super(sourceLabel1, sourceLabel2, destinationLabel);
        this.functionalUnitType = FunctionalUnitType.FPDIV;
        this.instructionType = InstructionType.ARITHMETIC_FPREG;
    }

    public DIVD(DIVD obj)
    {
        super(obj);
    }

    @Override
    public String toString()
    {
        return "DIVD " + dest.getDestinationLabel() + ", "
                + src1.getSourceLabel() + ", " + src2.getSourceLabel();
    }

    @Override
    public void executeInstruction()
    {
        if (src2.getSource() == 0)
            System.out.println(this.getClass().getSimpleName()
                    + " Divide by ZERO for instruction:" + debugString());
        else
            dest.setDestination(src1.getSource() / src2.getSource());
    }

}
