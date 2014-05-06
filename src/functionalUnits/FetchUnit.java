package functionalUnits;

import managers.ICacheManager;
import managers.ProgramManager;
import stages.CPU;
import stages.DecodeStage;
import stages.StageType;
import validInstructions.DI;
import validInstructions.NOOP;

public class FetchUnit extends FunctionalUnit {

	private static volatile FetchUnit instance;

	public static FetchUnit getInstance() {
		if (null == instance)
			synchronized (FetchUnit.class) {
				if (null == instance)
					instance = new FetchUnit();
			}

		return instance;
	}

	private FetchUnit() {
		super();
		this.isPipelined = false;
		this.clockCyclesRequired = 1;
		this.pipelineSize = 1;
		this.stageId = StageType.IFSTAGE;
		createPipelineQueue(pipelineSize);
	}

	@Override
	public int getClockCyclesRequiredForNonPipeLinedUnit() {
		return clockCyclesRequired;
	}

	@Override
	public void executeUnit() throws Exception {
		validateQueueSize();

		DI inst = peekFirst();

		if (!(inst instanceof NOOP)) {
			System.out.println(CPU.CLOCK + " Fetch  ");

			if (DecodeStage.getInstance().checkIfFree(inst)) {

				DecodeStage.getInstance().acceptInstruction(inst);
				updateExitClockCycle(inst);
				rotatePipe();
			}
		}

		fetchNextInstruction();
	}

	public void flushUnit() throws Exception {
		validateQueueSize();

		DI inst = peekFirst();

		System.out.println("FetchUnit flushUnit called for inst: "+inst.toString());

		if (inst instanceof NOOP)
			return;

		// update inst exitcycle
		// updateEntryClockCycle(inst); // hack dont do this!!!
		updateExitClockCycle(inst);
		// send to result manager
		// Display.instance.addInstruction(inst);
		// remove inst & add NOOP
		rotatePipe();

		validateQueueSize();
	}

	private void fetchNextInstruction() throws Exception {
		// fetch a new instruction only if ifStage is free
		if (checkIfFree()) {
			boolean checkInst = false;

			DI next = null;
			switch (CPU.RUN_TYPE) {
			case MEMORY:

				next = ICacheManager.getInstance().getInstructionFromCache(
						CPU.PROGRAM_COUNTER);
				if (next != null)
					checkInst = true;
				break;

			case PIPELINE:
				next = ProgramManager.instance
						.getInstructionAtAddress(CPU.PROGRAM_COUNTER);
				checkInst = true;
				break;
			}

			if (checkInst && checkIfFree()) {
				acceptInstruction(next);
				CPU.PROGRAM_COUNTER++;
			}

		} // end ifStage.checkIfFree

	}
}
