package validInstructions;

import instructionTypes.Type2Reg1Imm;
import enums.FunctionalUnitType;
import enums.InstructionType;

public class ANDI extends Type2Reg1Imm
{

    public ANDI(String sourceLabel, String destinationLabel, int immediate)
    {
        super(sourceLabel, destinationLabel, immediate);
        this.functionalUnitType = FunctionalUnitType.IU;
        this.instructionType = InstructionType.ARITHMETIC_IMM;
    }

    public ANDI(ANDI obj)
    {
        super(obj);
    }

    public int getImmediate()
    {
        return this.immediate;
    }

    @Override
    public String toString()
    {
        return "ANDI " + dest.getDestinationLabel() + ", "
                + src1.getSourceLabel() + ", " + immediate;
    }

    @Override
    public void executeInstruction()
    {
        dest.setDestination(src1.getSource() & immediate);
    }

}
