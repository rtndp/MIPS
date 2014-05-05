package functionalUnits;

import instructions.Instruction;
import instructions.InstructionType;
import instructions.LD;
import instructions.LW;
import instructions.NOOP;
import instructions.StoreInstruction;

import java.util.ArrayDeque;

import memory.DataMemoryManager;
import stages.CPU;
import stages.StageType;
import stages.WriteBackStage;
import config.ConfigManager;

public class MemoryUnit extends FunctionalUnit
{

    private static volatile MemoryUnit instance;

    public static MemoryUnit getInstance()
    {
        if (null == instance)
            synchronized (MemoryUnit.class)
            {
                if (null == instance)
                    instance = new MemoryUnit();
            }

        return instance;
    }

    private MemoryUnit()
    {
        super();
        this.isPipelined = false;
        this.clockCyclesRequired = ConfigManager.instance.MemoryLatency;
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
        if (!(inst instanceof NOOP))
        {

            // TODO for pipelined execution
            // check if inst has spent enough time in this unit

            switch (CPU.RUN_TYPE)
            {
                case MEMORY:

                    break;

                case PIPELINE:
                    if (!((CPU.CLOCK - inst.entryCycle[stageId.getId()]) >= this
                            .getClockCyclesRequiredForNonPipeLinedUnit()))
                        return;
                    break;
                default:
                    throw new Exception("MemoryUnit Illegal CPU.RUN_TYPE ");
            }

            // TODO for cache, check if data is available yet

            if (inst.instructionType.equals(InstructionType.MEMORY_FPREG)
                    || inst.instructionType.equals(InstructionType.MEMORY_REG))
            {
                if (inst instanceof StoreInstruction)
                    DataMemoryManager.instance.setValueToAddress(
                            (int) inst.address, (int) ((StoreInstruction) inst)
                                    .getValueToWrite().getSource());
                else
                    inst.getDestinationRegister().setDestination(
                            DataMemoryManager.instance
                                    .getValueFromAddress((int) inst.address));
            }

            if (!WriteBackStage.getInstance().checkIfFree(inst))
                throw new Exception(
                        "MemoryUnit: won tie, WB Stage should always be free");

            WriteBackStage.getInstance().acceptInstruction(inst);
            updateExitClockCycle(inst);
        }
        instructionQueue.removeLast();
        instructionQueue.addFirst(new NOOP());

    }

    @Override
    public int getClockCyclesRequiredForNonPipeLinedUnit() throws Exception
    {
        // TODO Auto-generated method stub
        Instruction inst = instructionQueue.peekLast();
        if (inst.instructionType.equals(InstructionType.MEMORY_FPREG)
                || inst.instructionType.equals(InstructionType.MEMORY_REG))
            return clockCyclesRequired;
        else if (inst.instructionType.equals(InstructionType.ARITHMETIC_REG)
                || inst.instructionType.equals(InstructionType.ARITHMETIC_IMM))
            return 1;

        throw new Exception("MemoryUnit: Illegal instruction in Memory Unit: "
                + inst.toString());
    }

    // TODO override acceptInstruction here, first call super.accept , then get
    // data from datamanager

}
