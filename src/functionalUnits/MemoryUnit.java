package functionalUnits;

import java.util.ArrayDeque;

import config.ConfigManager;
import instructions.Instruction;
import instructions.NOOP;

public class MemoryUnit extends FunctionalUnit {

	private static volatile MemoryUnit instance;

	public static MemoryUnit getInstance() {
		if (null == instance)
			synchronized (MemoryUnit.class) {
				if (null == instance)
					instance = new MemoryUnit();
			}

		return instance;
	}

	private MemoryUnit() {
		super();
		this.isPipelined = false;
		this.clockCyclesRequired = ConfigManager.instance.MemoryLatency;
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
