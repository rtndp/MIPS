package functionalUnits;

import instructions.Instruction;
import instructions.NOOP;
import instructions.WriteBackObject;

import java.util.ArrayDeque;

import registers.RegisterManager;
import results.ResultsManager;
import stages.CPU;

public class WriteBackUnit extends FunctionalUnit {

	private static volatile WriteBackUnit instance;

	public static WriteBackUnit getInstance() {
		if (null == instance)
			synchronized (WriteBackUnit.class) {
				if (null == instance)
					instance = new WriteBackUnit();
			}

		return instance;
	}

	private WriteBackUnit() {
		super();
		this.isPipelined = false;
		this.clockCyclesRequired = 1;
		this.pipelineSize = 1;
		this.instructionQueue = new ArrayDeque<Instruction>();
		this.instructionQueue.add(new NOOP());

		this.stageId = 3;

	}

	private Instruction peekInstructionFromQueue() {
		//
		System.out.println("WRITEBACK Instruction Queue Size - "
				+ instructionQueue.size());
		Instruction inst = instructionQueue.peek();
		if (inst instanceof NOOP)
			return null;

		System.out.println("WRITEBACK Valid instruction found in Queue");

		return inst;
	}

	@Override
	public void executeUnit() throws Exception {
		// Remove instruction from the queue if it is not a NOOP

		// Check if an instruction other than NOOP is in its Queue
		Instruction inst = peekInstructionFromQueue();
		if (inst == null)
			return;

		// Write back the data to the destination register if any and unlock
		// destination register as Free
		WriteBackObject writeBackObject = inst.getDestinationRegister();

		if (writeBackObject != null) {
			RegisterManager.instance.setRegisterValue(
					writeBackObject.getDestinationLabel(),
					writeBackObject.getDestination());
			RegisterManager.instance.setRegisterFree(writeBackObject
					.getDestinationLabel());
		}
		// Update the exit cycle in the instruction and pass it on to the result
		// manager for printing
		// Remove the instruction from the queue and enqueue a NOOP

		inst.exitCycle[3] = CPU.CLOCK;
		ResultsManager.instance.addInstruction(inst);

		instructionQueue.remove();
		instructionQueue.add(new NOOP());
	}

	@Override
	public int getClockCyclesRequiredForNonPipeLinedUnit() {
		// TODO Auto-generated method stub
		return clockCyclesRequired;
	}
	/*
	 * public void dumpUnitDetails() { System.out.println("isPipelined - " +
	 * instance.isPipelined()); System.out.println("isAvailable - " +
	 * instance.isAvailable()); System.out.println("Pipeline Size - " +
	 * instance.getPipelineSize());
	 * System.out.println("Clock Cycles required - " +
	 * instance.getClockCyclesRequired()); }
	 */
}
