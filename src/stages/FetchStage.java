package stages;

import validInstructions.DI;
import enums.StageType;

public class FetchStage extends Stage
{

    private static volatile FetchStage instance;

    public static FetchStage getInstance()
    {

        if (null == instance)
            synchronized (FetchStage.class)
            {
                if (null == instance)
                    instance = new FetchStage();
            }

        return instance;
    }

    private functionalUnits.FetchUnit fetch;

    private FetchStage()
    {
        super();
        fetch = functionalUnits.FetchUnit.getInstance();
        this.stageType = StageType.IFSTAGE;
    }

    @Override
    public void execute() throws Exception
    {
        fetch.executeUnit();
    }

    @Override
    public boolean checkIfFree(DI instruction) throws Exception
    {
        return fetch.checkIfFree(instruction);
    }

    @Override
    public boolean acceptInstruction(DI instruction) throws Exception
    {
        if (!fetch.checkIfFree(instruction))
            throw new Exception("FetchStage: Illegal state exception "
                    + instruction.toString());

        fetch.acceptInstruction(instruction);
        return true;
    }

    public void flushStage() throws Exception
    {
        fetch.flushUnit();
    }

}
