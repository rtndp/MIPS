package functionalUnits;

import instructions.Instruction;
import instructions.NOOP;
import instructions.WriteBackObject;

import java.util.ArrayDeque;

import registers.RegisterManager;
import results.ResultsManager;
import stages.CPU;
import stages.StageType;

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
        this.isPipelined = false;
        this.clockCyclesRequired = 1;
        this.pipelineSize = 1;
        this.instructionQueue = new ArrayDeque<Instruction>();
        this.instructionQueue.add(new NOOP());

        this.stageId = StageType.WBSTAGE;

    }

    @Override
    public void executeUnit() throws Exception
    {
        Instruction inst = instructionQueue.peekLast();

        if (inst instanceof NOOP)
            return;

        System.out.println(CPU.CLOCK + " WBUnit " + inst.debugString());

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
        ResultsManager.instance.addInstruction(inst);

        // Remove the instruction from the queue and enqueue a NOOP
        instructionQueue.remove();
        instructionQueue.add(new NOOP());
    }

    @Override
    public int getClockCyclesRequiredForNonPipeLinedUnit()
    {
        // TODO Auto-generated method stub
        return clockCyclesRequired;
    }
}
