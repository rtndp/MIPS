package instructionTypes;

import java.util.ArrayList;
import java.util.List;

import validInstructions.DI;
import validInstructions.SourceObject;
import validInstructions.WriteBackObject;

public abstract class Type3Reg extends DI
{
    public SourceObject    src1, src2;
    public WriteBackObject dest;

    public Type3Reg(String sourceLabel1, String sourceLabel2,
            String destinationLabel)
    {
        super();
        src1 = new SourceObject(sourceLabel1, 0);
        src2 = new SourceObject(sourceLabel2, 0);
        dest = new WriteBackObject(destinationLabel, 0);
    }

    public Type3Reg(Type3Reg obj)
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
