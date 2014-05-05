package functionalUnits;

import instructions.Instruction;
import instructions.NOOP;

import java.util.ArrayDeque;
import java.util.Iterator;

import stages.WriteBackStage;

public abstract class FPFunctionalUnit extends FunctionalUnit
{

    protected void createPipelineQueue(int size)
    {
        instructionQueue = new ArrayDeque<Instruction>();
        for (int i = 0; i < size; i++)
            instructionQueue.add(new NOOP());
    }

    @Override
    public void executeUnit() throws Exception
    {
        validateQueueSize();

        Instruction inst = instructionQueue.peekLast();
        inst.executeInstruction();

        // TODO clean this up!!!
        if (!(inst instanceof NOOP))
        {
            if (isReadyToSend())
            {
                if (!WriteBackStage.getInstance().checkIfFree(inst))
                    throw new Exception(this.getClass().getSimpleName()
                            + " won tie, WB Stage should always be free");

                WriteBackStage.getInstance().acceptInstruction(inst);
                updateExitClockCycle(inst);
            }
            else if (!isPipelined)
            {
                return;
            }
        }

        // This is the same effect as running pipleine once :)
        rotatePipe();
    }

    @Override
    public int getClockCyclesRequiredForNonPipeLinedUnit()
    {
        return clockCyclesRequired;
    }

    public void rotatePipelineOnHazard()
    {
        if (!isPipelined)
            return;
        // non pipelined, now iterate in reverse

        for (Iterator<Instruction> itr = instructionQueue.descendingIterator(); itr
                .hasNext();)
        {
            Instruction inst = itr.next();

            if (inst instanceof NOOP)
                break;
        }
    }
}
