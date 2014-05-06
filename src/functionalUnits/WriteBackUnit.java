package functionalUnits;

import registers.RegisterManager;
import stages.ProcessorParams;
import utility.Display;
import validInstructions.DI;
import validInstructions.NOOP;
import validInstructions.WriteBackObject;
import enums.StageType;

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

        System.out.println(ProcessorParams.CC + " WBUnit " + inst.toString());

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
        Display.instance.queueInstructionForDisplay(inst);

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
