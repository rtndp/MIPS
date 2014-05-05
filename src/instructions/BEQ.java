package instructions;

public class BEQ extends ConditionalBranchInstruction
{

    public BEQ(String sourceLabel1, String sourceLabel2, String destinationLabel)
    {
        super(sourceLabel1, sourceLabel2, destinationLabel);
        this.instructionType = InstructionType.BRANCH;
    }

    public BEQ(BEQ obj)
    {
        super(obj);
    }

    @Override
    public String toString()
    {
        return "BEQ " + " " + src1.getSourceLabel() + ", "
                + src2.getSourceLabel() + ", " + destinationLabel;
    }

    @Override
    public void executeInstruction()
    {
        // Do nothing here

    }

}
