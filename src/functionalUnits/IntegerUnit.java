package functionalUnits;

import java.util.ArrayDeque;

import instructions.Instruction;
import instructions.NOOP;

public class IntegerUnit extends FunctionalUnit {

	private static volatile IntegerUnit instance;

	public static IntegerUnit getInstance() {
		if (null == instance)
			synchronized (IntegerUnit.class) {
				if (null == instance)
					instance = new IntegerUnit();
			}

		return instance;
	}

	private IntegerUnit() {
		super();
		this.isPipelined = false;
		this.clockCyclesRequired = 1;
		this.pipelineSize = 1;

		this.instructionQueue = new ArrayDeque<Instruction>();
		for (int i = 0; i < this.pipelineSize; i++)
			this.instructionQueue.add(new NOOP());

		this.stageId = 2;

	}

	@Override
	public void executeUnit() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public int getClockCyclesRequiredForNonPipeLinedUnit() {
		// TODO Auto-generated method stub
		return clockCyclesRequired;
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
