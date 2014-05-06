package validInstructions;

import instructionTypes.Type3Reg;
import enums.FunctionalUnitType;
import enums.InstructionType;

public class MULD extends Type3Reg
{
    public MULD(String sourceLabel1, String sourceLabel2,
            String destinationLabel)
    {
        super(sourceLabel1, sourceLabel2, destinationLabel);
        this.functionalUnitType = FunctionalUnitType.FPMUL;
        this.instructionType = InstructionType.ARITHMETIC_FPREG;
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
