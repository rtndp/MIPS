package instructions;

import java.util.List;

public class NOOP extends Instruction
{

    public NOOP()
    {
        super();
        this.instructionType = InstructionType.NOOP;
    }

    public NOOP(NOOP obj)
    {
        super(obj);
        setPrintableInstruction(obj.printableInstruction);
    }

    @Override
    public List<SourceObject> getSourceRegister()
    {
        // Do nothing here
        return null;
    }

    @Override
    public WriteBackObject getDestinationRegister()
    {
        // Do nothing here
        return null;
    }

    @Override
    public void executeInstruction()
    {
        // Do nothing here

    }

    @Override
    public String toString()
    {
        return "NOOP";
    }

}
