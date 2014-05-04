package functionalUnits;

import instructions.Instruction;
import instructions.InstructionType;
import instructions.LD;
import instructions.LW;
import instructions.NOOP;
import instructions.SD;
import instructions.SW;

import java.util.ArrayDeque;

import config.ConfigManager;

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

	@Override
	public int getClockCyclesRequiredForNonPipeLinedUnit() throws Exception {
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
