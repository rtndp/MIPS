package functionalUnits;

import instructions.Instruction;
import instructions.NOOP;
import stages.StageType;

public class IntegerUnit extends FunctionalUnit
{

    private static volatile IntegerUnit instance;

    public static IntegerUnit getInstance()
    {
        if (null == instance)
            synchronized (IntegerUnit.class)
            {
                if (null == instance)
                    instance = new IntegerUnit();
            }

        return instance;
    }

    private IntegerUnit()
    {
        super();
        isPipelined = false;
        clockCyclesRequired = 1;
        pipelineSize = 1;
        stageId = StageType.EXSTAGE;
        createPipelineQueue(pipelineSize);

    }

    @Override
    public void executeUnit() throws Exception
    {
        validateQueueSize();

        Instruction inst = peekFirst();

        if (inst instanceof NOOP)
            return;

        inst.executeInstruction();

        if (MemoryUnit.getInstance().checkIfFree(inst))
        {
            MemoryUnit.getInstance().acceptInstruction(inst);
            updateExitClockCycle(inst);
            rotatePipe();
        }
        else
        {
            markStructHazard();
        }

    }

    @Override
    public int getClockCyclesRequiredForNonPipeLinedUnit()
    {
        return clockCyclesRequired;
    }
}
