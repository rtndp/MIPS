package validInstructions;

import instructionTypes.Type3Reg;
import enums.FunctionalUnitType;
import enums.InstructionType;

public class ADDD extends Type3Reg
{

    public ADDD(String sourceLabel1, String sourceLabel2,
            String destinationLabel)
    {
        super(sourceLabel1, sourceLabel2, destinationLabel);
        this.functionalUnitType = FunctionalUnitType.FPADD;
        this.instructionType = InstructionType.ARITHMETIC_FPREG;
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
