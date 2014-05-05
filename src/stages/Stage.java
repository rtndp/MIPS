package stages;

import instructions.Instruction;

public abstract class Stage
{
    protected StageType stageType;

    public abstract void execute() throws Exception;

    public abstract boolean checkIfFree(Instruction instruction)
            throws Exception;

    public boolean checkIfFree() throws Exception
    {
        return checkIfFree(null); // will throw exception for ExStage if used
    }

    public abstract boolean acceptInstruction(Instruction instruction)
            throws Exception;
}
