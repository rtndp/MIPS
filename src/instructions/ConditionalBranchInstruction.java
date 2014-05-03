package instructions;

import java.util.ArrayList;
import java.util.List;

public abstract class ConditionalBranchInstruction extends Instruction
{
    SourceObject src1, src2;

    String       destinationLabel;

    public ConditionalBranchInstruction(String sourceLabel1,
            String sourceLabel2, String destinationLabel)
    {
        super();
        src1 = new SourceObject(sourceLabel1, 0);
        src2 = new SourceObject(sourceLabel2, 0);
        this.destinationLabel = destinationLabel;
    }

    public ConditionalBranchInstruction(ConditionalBranchInstruction obj)
    {
        super(obj);
        setPrintableInstruction(obj.printableInstruction);
        src1 = new SourceObject(obj.src1);
        src2 = new SourceObject(obj.src2);
        destinationLabel = obj.destinationLabel;
    }

    @Override
    public List<SourceObject> getSourceRegister()
    {
        List<SourceObject> sourceRegisterList = new ArrayList<SourceObject>();

        sourceRegisterList.add(src1);
        sourceRegisterList.add(src2);

        return sourceRegisterList;
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
}
