package instructions;


public class ADDD extends ThreeRegInstruction
{

    public ADDD(String sourceLabel1, String sourceLabel2,
            String destinationLabel)
    {
        super(sourceLabel1, sourceLabel2, destinationLabel);
    }

    public ADDD(ADDD obj)
    {
        super(obj);
    }

    @Override
    public String toString()
    {
        return "ADDD " + dest.getDestinationLabel() + ", "
                + src1.getSourceLabel() + ", " + src2.getSourceLabel();
    }

    @Override
    public void executeInstruction()
    {
        dest.setDestination(src1.getSource() + src2.getSource());
    }

}
