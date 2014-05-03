package instructions;


public class MULD extends ThreeRegInstruction
{
    public MULD(String sourceLabel1, String sourceLabel2,
            String destinationLabel)
    {
        super(sourceLabel1, sourceLabel2, destinationLabel);
    }

    public MULD(MULD obj)
    {
        super(obj);
    }

    @Override
    public String toString()
    {
        return "MULD " + dest.getDestinationLabel() + ", "
                + src1.getSourceLabel() + ", " + src2.getSourceLabel();
    }

    @Override
    public void executeInstruction()
    {
        dest.setDestination(src1.getSource() * src2.getSource());
    }

}
