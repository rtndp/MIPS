package validInstructions;

import java.util.List;

import enums.InstructionType;

public class HLT extends DI
{
    public HLT()
    {
        super();
        this.instructionType = InstructionType.HALT;
    }

    public HLT(HLT obj)
    {
        super(obj);
        setPrintableInstruction(obj.printableInstruction);
    }

    @Override
    public List<SourceObject> getSourceRegister()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WriteBackObject getDestinationRegister()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString()
    {
        return "HLT";
    }

    @Override
    public void executeInstruction()
    {
        // Do nothing here

    }

}
