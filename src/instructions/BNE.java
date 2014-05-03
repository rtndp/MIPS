package instructions;


public class BNE extends ConditionalBranchInstruction
{

    public BNE(String sourceLabel1, String sourceLabel2, String destinationLabel)
    {
        super(sourceLabel1, sourceLabel2, destinationLabel);
    }

    public BNE(BNE obj)
    {
        super(obj);
    }

    @Override
    public String toString()
    {
        return "BNE " + " " + src1.getSourceLabel() + ", "
                + src2.getSourceLabel() + ", " + destinationLabel;
    }

    @Override
    public void executeInstruction()
    {
        // Do nothing here

    }

}
