package functionalUnits;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;

import stages.WriteBackStage;
import validInstructions.DADDI;
import validInstructions.DI;
import validInstructions.HLT;
import validInstructions.J;
import validInstructions.NOOP;

public abstract class FPFunctionalUnit extends FunctionalUnit
{

    @Override
    public void executeUnit() throws Exception
    {
        validateQueueSize();

        DI inst = peekFirst();
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

    public void rotatePipelineOnHazard() throws Exception
    {
        validateQueueSize();
        if (!isPipelined)
            return;
        // non pipelined, now iterate in reverse

        DI objects[] = pipelineToArray();

        for (int i = 0; i < objects.length - 1; i++)
        {
            if (objects[i] instanceof NOOP)
            {
                DI temp = objects[i];
                objects[i] = objects[i + 1];
                objects[i + 1] = temp;
            }
        }

        createPipelineQueue(0);
        for (int i = 0; i < objects.length; i++)
        {
            addLast(objects[i]);
        }
        validateQueueSize();
    }

    public static void main(String[] args)
    {
        ArrayDeque<DI> deque = new ArrayDeque<DI>();
        deque.add(new HLT());
        deque.add(new NOOP());
        deque.add(new J("Jump"));
        deque.add(new NOOP());
        deque.add(new NOOP());
        deque.add(new DADDI("src1", "src2", 123));

        DI[] objects = (DI[]) deque
                .toArray(new DI[deque.size()]);

        System.out.println(Arrays.toString(objects));

        for (int i = 0; i < objects.length - 1; i++)
        {
            if (objects[i] instanceof NOOP)
            {
                DI temp = objects[i];
                objects[i] = objects[i + 1];
                objects[i + 1] = temp;
            }
        }

        System.out.println(Arrays.toString(objects));

        deque = new ArrayDeque<DI>();
        for (DI instruction : objects)
        {
            deque.add(instruction);
        }

        for (Iterator<DI> itr = deque.iterator(); itr.hasNext();)
        {
            System.out.print(itr.next().toString() + " ");
        }

    }
}
