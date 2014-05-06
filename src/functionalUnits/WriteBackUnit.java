package functionalUnits;

import registers.RegisterManager;
import stages.CPU;
import stages.StageType;
import validInstructions.DI;
import validInstructions.NOOP;
import validInstructions.WriteBackObject;

public class WriteBackUnit extends FunctionalUnit
{

    private static volatile WriteBackUnit instance;

    public static WriteBackUnit getInstance()
    {
        if (null == instance)
            synchronized (WriteBackUnit.class)
            {
                if (null == instance)
                    instance = new WriteBackUnit();
            }

        return instance;
    }

    private WriteBackUnit()
    {
        super();
        isPipelined = false;
        clockCyclesRequired = 1;
        pipelineSize = 1;
        stageId = StageType.WBSTAGE;
        createPipelineQueue(pipelineSize);
    }

    @Override
    public void executeUnit() throws Exception
    {
        DI inst = peekFirst();

        if (inst instanceof NOOP)
            return;

        System.out.println(CPU.CLOCK + " WBUnit " + inst.toString());

        // Write back the data to the destination register if any and unlock
        // destination register as Free
        WriteBackObject writeBackObject = inst.getDestinationRegister();

        if (writeBackObject != null)
        {
            RegisterManager.instance.setRegisterValue(
                    writeBackObject.getDestinationLabel(),
                    writeBackObject.getDestination());
            RegisterManager.instance.setRegisterFree(writeBackObject
                    .getDestinationLabel());
        }

        // Update the exit cycle in the instruction and pass it on to the result
        updateExitClockCycle(inst);
        // manager for printing
        //Display.instance.addInstruction(inst);

        // Remove the instruction from the queue and enqueue a NOOP
        rotatePipe();
    }

    @Override
    public int getClockCyclesRequiredForNonPipeLinedUnit()
    {
        // TODO Auto-generated method stub
        return clockCyclesRequired;
    }
}
