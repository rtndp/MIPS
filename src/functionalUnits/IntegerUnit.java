package functionalUnits;

import instructions.Instruction;
import instructions.NOOP;

import java.util.ArrayDeque;

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
        this.isPipelined = false;
        this.clockCyclesRequired = 1;
        this.pipelineSize = 1;

        this.instructionQueue = new ArrayDeque<Instruction>();
        for (int i = 0; i < this.pipelineSize; i++)
            this.instructionQueue.add(new NOOP());

        this.stageId = StageType.EXSTAGE;

    }

    @Override
    public void executeUnit() throws Exception
    {
        validateQueueSize();

        Instruction inst = instructionQueue.peekLast();

        if (inst instanceof NOOP)
            return;

        inst.executeInstruction();

        if (MemoryUnit.getInstance().checkIfFree(inst))
        {
            MemoryUnit.getInstance().acceptInstruction(inst);
            updateExitClockCycle(inst);
            instructionQueue.removeLast();
            instructionQueue.addFirst(new NOOP());
        }
        else
        {
            markStructHazard();
        }

    }

    @Override
    public int getClockCyclesRequiredForNonPipeLinedUnit()
    {
        // TODO Auto-generated method stub
        return clockCyclesRequired;
    }
}
