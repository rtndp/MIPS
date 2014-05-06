package validInstructions;

import java.util.List;

import enums.InstructionType;

public class J extends DI
{

    String destinationLabel;

    public J(String destinationLabel)
    {
        super();
        this.destinationLabel = destinationLabel;
        this.instructionType = InstructionType.JUMP;
    }

    public J(J obj)
    {
        super(obj);
        setPrintableInstruction(obj.printableInstruction);
        destinationLabel = obj.destinationLabel;
    }

    @Override
    public List<SourceObject> getSourceRegister()
    {
        return null;
    }

    @Override
    public WriteBackObject getDestinationRegister()
    {
        return null;
    }

    public String getDestinationLabel()
    {
        return destinationLabel;
    }

    @Override
    public String toString()
    {
        return "J " + destinationLabel;
    }

    @Override
    public void executeInstruction()
    {
        // Do nothing here

    }
}
