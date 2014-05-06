package functionalUnits;

import java.util.List;

import managers.ProgramManager;
import registers.RegisterManager;
import stages.ProcessorParams;
import stages.ExStage;
import stages.FetchStage;
import utility.Display;
import validInstructions.BEQ;
import validInstructions.BNE;
import validInstructions.CB;
import validInstructions.DI;
import validInstructions.HLT;
import validInstructions.J;
import validInstructions.NOOP;
import validInstructions.SourceObject;
import validInstructions.WriteBackObject;
import enums.FunctionalUnitType;
import enums.StageType;

public class DecodeUnit extends FunctionalUnit {

	private static volatile DecodeUnit instance;

	public static DecodeUnit getInstance() {
		if (null == instance)
			synchronized (DecodeUnit.class) {
				if (null == instance)
					instance = new DecodeUnit();
			}

		return instance;
	}

	private DecodeUnit() {
		super();
		isPipelined = false;
		clockCyclesRequired = 1;
		pipelineSize = 1;
		stageId = StageType.IDSTAGE;
		createPipelineQueue(pipelineSize);
	}

	@Override
	public int getClockCyclesRequiredForNonPipeLinedUnit() {
		return clockCyclesRequired;
	}

	@Override
	public void executeUnit() throws Exception {
		// Called by the decode stage
		validateQueueSize();

		DI inst = peekFirst();

		if (inst instanceof NOOP)
			return;

		System.out.println(ProcessorParams.CC + " Decode " + inst.toString());

		boolean hazards = processHazards(inst);

		if (!hazards)
			executeDecode(inst);

		validateQueueSize();

	}

	private void executeDecode(DI inst) throws Exception {

		updateExitClockCycle(inst);
		Display.instance.queueInstructionForDisplay(inst);

		// read source registers
		List<SourceObject> sources = inst.getSourceRegister();
		if (sources != null) {
			for (SourceObject register : sources) {
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
		if (inst instanceof J) {
			// update PC to label address
			ProcessorParams.PC = ProgramManager.instance
					.getInstructionAddreessForLabel(((J) inst)
							.getDestinationLabel());

			FetchStage.getInstance().flushStage();

		}
		// process BNE,BEQ instruction
		else if (inst instanceof CB) {
			if (inst instanceof BEQ) {
				if (((CB) inst).compareRegisters()) {
					// update PC
					ProcessorParams.PC = ProgramManager.instance
							.getInstructionAddreessForLabel(((BEQ) inst)
									.getDestinationLabel());
					// Flush fetch stage
					FetchStage.getInstance().flushStage();
				}
			} else if (inst instanceof BNE) {
				if (!((CB) inst).compareRegisters()) {
					// update PC
					ProcessorParams.PC = ProgramManager.instance
							.getInstructionAddreessForLabel(((BNE) inst)
									.getDestinationLabel());
					// Flush fetch stage
					FetchStage.getInstance().flushStage();
				}
			}
		}
		// process HLT instruction
		else if (inst instanceof HLT) {
			Display.instance.setHALT(true);
		} else {

			if (!ExStage.getInstance().checkIfFree(inst))
				throw new Exception(
						"DecodeUnit: failed in exstage.checkIfFree after resolving struct hazard "
								+ inst.toString());

			ExStage.getInstance().acceptInstruction(inst);

		}

		rotatePipe();
	}

	private boolean processStruct(DI inst) throws Exception {
		// Check for possible STRUCT hazards
		FunctionalUnitType type = inst.functionalUnitType;
		if (!type.equals(FunctionalUnitType.UNKNOWN)) {
			if (!(ExStage.getInstance().checkIfFree(inst))) {
				inst.STRUCT = true;
				return true;
			}
		}

		return false;
	}

	private boolean processRAW(DI inst) throws Exception {
		// Check for possible RAW hazards

		List<SourceObject> sources = inst.getSourceRegister();
		if (sources != null) {
			for (SourceObject register : sources) {
				if (!RegisterManager.instance.isRegisterFree(register
						.getSourceLabel())) {
					inst.RAW = true;
					return true;
				}
			}
		}

		return false;
	}

	private boolean processWAW(DI inst) throws Exception {
		WriteBackObject dest = inst.getDestinationRegister();
		if (dest != null) {

			if (!RegisterManager.instance.isRegisterFree(dest
					.getDestinationLabel())) {
				inst.WAW = true;
				return true;
			}
		}
		return false;
	}

	private boolean processWAR(DI inst) {
		return false;
	}

	private boolean processHazards(DI inst) throws Exception {
		return (processRAW(inst) || processWAR(inst) || processWAW(inst) || processStruct(inst));
	}
}
