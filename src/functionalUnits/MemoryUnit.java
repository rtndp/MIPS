package functionalUnits;

import instructions.Instruction;
import instructions.InstructionType;
import instructions.NOOP;
import instructions.StoreInstruction;
import memory.DataMemoryManager;
import stages.CPU;
import stages.StageType;
import stages.WriteBackStage;
import config.ConfigManager;
import dCache.DCacheManager;

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
        isPipelined = false;
        clockCyclesRequired = ConfigManager.instance.MemoryLatency;
        pipelineSize = 1;
        stageId = StageType.EXSTAGE;
        createPipelineQueue(pipelineSize);
    }

    @Override
    public void executeUnit() throws Exception
    {
        validateQueueSize();

        Instruction inst = peekFirst();
        if (!(inst instanceof NOOP))
        {

            // TODO for pipelined execution
            // check if inst has spent enough time in this unit

            switch (CPU.RUN_TYPE)
            {
                case MEMORY:
                    // TODO call DCacheManager only if inst is type of Memory
                    // Operation
                    if (Instruction.isLoadStore(inst)
                            && !DCacheManager.instance.canProceed(inst))
                        return;
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

            if (Instruction.isLoadStore(inst))
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
        rotatePipe();

    }

    @Override
    public int getClockCyclesRequiredForNonPipeLinedUnit() throws Exception
    {
        // TODO Auto-generated method stub
        Instruction inst = peekFirst();
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
