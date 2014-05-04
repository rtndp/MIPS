package functionalUnits;

import java.util.ArrayDeque;

import instructions.Instruction;
import instructions.NOOP;

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
		this.stageId = 1;
		this.instructionQueue = new ArrayDeque<Instruction>();
		for (int i = 0; i < this.pipelineSize; i++)
			this.instructionQueue.add(new NOOP());

	}

	private void process() {

	}

	private boolean enqueueInstruction() {

		return false;
	}

	@Override
	public void executeUnit() {
		// Called by fetch stage
		process();

		enqueueInstruction();

	}

	/*
	 * public void dumpUnitDetails(){
	 * System.out.println("isPipelined - "+instance.isPipelined());
	 * System.out.println("isAvailable - "+instance.isAvailable());
	 * System.out.println("Pipeline Size - "+instance.getPipelineSize());
	 * System.
	 * out.println("Clock Cycles required - "+instance.getClockCyclesRequired
	 * ()); }
	 */

}
