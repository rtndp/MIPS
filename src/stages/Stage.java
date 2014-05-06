package stages;

import validInstructions.DI;
import enums.StageType;

public abstract class Stage
{
    protected StageType stageType;

    public abstract void execute() throws Exception;

    public abstract boolean checkIfFree(DI instruction)
            throws Exception;

    public boolean checkIfFree() throws Exception
    {
        return checkIfFree(null); // will throw exception for ExStage if used
    }

    public abstract boolean acceptInstruction(DI instruction)
            throws Exception;
}
