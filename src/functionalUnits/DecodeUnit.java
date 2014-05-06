package functionalUnits;

import instructions.BEQ;
import instructions.BNE;
import instructions.ConditionalBranchInstruction;
import instructions.FunctionalUnitType;
import instructions.HLT;
import instructions.Instruction;
import instructions.J;
import instructions.NOOP;
import instructions.SourceObject;
import instructions.WriteBackObject;

import java.util.List;

import program.ProgramManager;
import registers.RegisterManager;
import results.ResultsManager;
import stages.CPU;
import stages.ExStage;
import stages.FetchStage;
import stages.StageType;

public class DecodeUnit extends FunctionalUnit
{

    private static volatile DecodeUnit instance;

    public static DecodeUnit getInstance()
    {
        if (null == instance)
            synchronized (DecodeUnit.class)
            {
                if (null == instance)
                    instance = new DecodeUnit();
            }

        return instance;
    }

    private DecodeUnit()
    {
        super();
        isPipelined = false;
        clockCyclesRequired = 1;
        pipelineSize = 1;
        stageId = StageType.IDSTAGE;
        createPipelineQueue(pipelineSize);
    }

    @Override
    public int getClockCyclesRequiredForNonPipeLinedUnit()
    {
        return clockCyclesRequired;
    }

    @Override
    public void executeUnit() throws Exception
    {
        // Called by the decode stage
        validateQueueSize();

        Instruction inst = peekFirst();

        if (inst instanceof NOOP)
            return;

        System.out.println(CPU.CLOCK + " Decode " + inst.debugString());

        boolean hazards = processHazards(inst);

        if (!hazards)
            executeDecode(inst);

        validateQueueSize();

    }

    private void executeDecode(Instruction inst) throws Exception
    {

        updateExitClockCycle(inst);
        ResultsManager.instance.addInstruction(inst);

        // read source registers
        List<SourceObject> sources = inst.getSourceRegister();
        if (sources != null)
        {
            for (SourceObject register : sources)
            {
                register.setSource(RegisterManager.instance
                        .getRegisterValue(register.getSourceLabel()));
            }
        }

        // lock destination register
        WriteBackObject destReg = inst.getDestinationRegister();
        if (destReg != null)
            RegisterManager.instance.setRegisterBusy(destReg
                    .getDestinationLabel());

        // process J instruction
        if (inst instanceof J)
        {
            // update PC to label address
            CPU.PROGRAM_COUNTER = ProgramManager.instance
                    .getInstructionAddreessForLabel(((J) inst)
                            .getDestinationLabel());

            FetchStage.getInstance().flushStage();

        }
        // process BNE,BEQ instruction
        else if (inst instanceof ConditionalBranchInstruction)
        {
            if (inst instanceof BEQ)
            {
                if (((ConditionalBranchInstruction) inst).compareRegisters())
                {
                    // update PC
                    CPU.PROGRAM_COUNTER = ProgramManager.instance
                            .getInstructionAddreessForLabel(((BEQ) inst)
                                    .getDestinationLabel());
                    // Flush fetch stage
                    FetchStage.getInstance().flushStage();
                }
            }
            else if (inst instanceof BNE)
            {
                if (!((ConditionalBranchInstruction) inst).compareRegisters())
                {
                    // update PC
                    CPU.PROGRAM_COUNTER = ProgramManager.instance
                            .getInstructionAddreessForLabel(((BNE) inst)
                                    .getDestinationLabel());
                    // Flush fetch stage
                    FetchStage.getInstance().flushStage();
                }
            }
        }
        // process HLT instruction
        else if (inst instanceof HLT)
        {
            ResultsManager.instance.setHALT(true);
        }
        else
        {

            if (!ExStage.getInstance().checkIfFree(inst))
                throw new Exception(
                        "DecodeUnit: failed in exstage.checkIfFree after resolving struct hazard "
                                + inst.toString());

            ExStage.getInstance().acceptInstruction(inst);

        }

        rotatePipe();
    }

    private boolean processStruct(Instruction inst) throws Exception
    {
        // Check for possible STRUCT hazards
        FunctionalUnitType type = inst.functionalUnitType;
        if (!type.equals(FunctionalUnitType.UNKNOWN))
        {
            if (!(ExStage.getInstance().checkIfFree(inst)))
            {
                inst.STRUCT = true;
                return true;
            }
        }

        return false;
    }

    private boolean processRAW(Instruction inst) throws Exception
    {
        // Check for possible RAW hazards

        List<SourceObject> sources = inst.getSourceRegister();
        if (sources != null)
        {
            for (SourceObject register : sources)
            {
                if (!RegisterManager.instance.isRegisterFree(register
                        .getSourceLabel()))
                {
                    inst.RAW = true;
                    return true;
                }
            }
        }

        return false;
    }

    private boolean processWAW(Instruction inst) throws Exception
    {
        WriteBackObject dest = inst.getDestinationRegister();
        if (dest != null)
        {

            if (!RegisterManager.instance.isRegisterFree(dest
                    .getDestinationLabel()))
            {
                inst.WAW = true;
                return true;
            }
        }
        return false;
    }

    private boolean processWAR(Instruction inst)
    {
        return false;
    }

    private boolean processHazards(Instruction inst) throws Exception
    {
        return (processRAW(inst) || processWAR(inst) || processWAW(inst) || processStruct(inst));
    }
}
