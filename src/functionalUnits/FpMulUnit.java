package functionalUnits;

import java.util.ArrayDeque;

import config.ConfigManager;
import instructions.Instruction;
import instructions.NOOP;

public class FpMulUnit extends FunctionalUnit {

	private static volatile FpMulUnit instance;

	public static FpMulUnit getInstance() {
		if (null == instance)
			synchronized (FpMulUnit.class) {
				if (null == instance)
					instance = new FpMulUnit();
			}

		return instance;
	}

	private FpMulUnit() {
		super();
		this.isPipelined = ConfigManager.instance.FPMultPipelined;
		this.clockCyclesRequired = ConfigManager.instance.FPMultLatency;

		this.pipelineSize = this.isPipelined ? ConfigManager.instance.FPMultLatency
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
