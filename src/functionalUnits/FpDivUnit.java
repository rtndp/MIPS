package functionalUnits;

import java.util.ArrayDeque;

import config.ConfigManager;
import instructions.Instruction;
import instructions.NOOP;

public class FpDivUnit extends FunctionalUnit {

	private static volatile FpDivUnit instance;

	public static FpDivUnit getInstance() {
		if (null == instance)
			synchronized (FpDivUnit.class) {
				if (null == instance)
					instance = new FpDivUnit();
			}

		return instance;
	}

	private FpDivUnit() {
		super();
		this.isPipelined = ConfigManager.instance.FPDividerPipelined;
		this.clockCyclesRequired = ConfigManager.instance.FPDivideLatency;

		this.pipelineSize = this.isPipelined ? ConfigManager.instance.FPDivideLatency
				: 1;

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
