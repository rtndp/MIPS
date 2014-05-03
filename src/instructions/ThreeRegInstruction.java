package instructions;

import java.util.ArrayList;
import java.util.List;

public abstract class ThreeRegInstruction extends Instruction
{
    SourceObject    src1, src2;
    WriteBackObject dest;

    public ThreeRegInstruction(String sourceLabel1, String sourceLabel2,
            String destinationLabel)
    {
        super();
        src1 = new SourceObject(sourceLabel1, 0);
        src2 = new SourceObject(sourceLabel2, 0);
        dest = new WriteBackObject(destinationLabel, 0);
    }

    public ThreeRegInstruction(ThreeRegInstruction obj)
    {
        super(obj);
        setPrintableInstruction(obj.printableInstruction);

        src1 = new SourceObject(obj.src1);
        src2 = new SourceObject(obj.src2);
        dest = new WriteBackObject(obj.dest);

    }

    @Override
    public List<SourceObject> getSourceRegister()
    {
        List<SourceObject> sourceRegisterList = new ArrayList<SourceObject>();

        sourceRegisterList.add(this.src1);
        sourceRegisterList.add(this.src2);

        return sourceRegisterList;
    }

    @Override
    public WriteBackObject getDestinationRegister()
    {
        return dest;
    }

}
