package functionalUnits;

import instructions.Instruction;
import instructions.NOOP;
import instructions.WriteBackObject;

import java.util.ArrayDeque;

import registers.RegisterManager;
import results.ResultsManager;
import stages.CPU;

public class WRITEBACK extends FUNCTIONALUNIT {

	private static volatile WRITEBACK instance;

	public static WRITEBACK getInstance() {
		if (null == instance)
			synchronized (WRITEBACK.class) {
				if (null == instance)
					instance = new WRITEBACK();
			}

		return instance;
	}

	private WRITEBACK() {
		super();
		this.setPipelined(false);
		this.setClockCyclesRequired(1);
		this.setAvailable(true);
		this.setPipelineSize(1);
		this.instructionQueue = new ArrayDeque<Instruction>();
		this.instructionQueue.add(new NOOP());

	}

	@Override
	public void addInstructionToQueue(Instruction instruction) {
		// This instruction will do nothing in the WRITEBACK stage

	}

	@Override
	public Instruction removeInstructionFromQueue() {
		//
		System.out.println("WRITEBACK Instruction Queue Size - "
				+ instructionQueue.size());
		Instruction inst = instructionQueue.peek();
		if (inst instanceof NOOP)
			return null;

		System.out.println("WRITEBACK Valid instruction found in Queue");

		return inst;
	}

	// This method will be invoked by the WRITEBACK stage
	public void writeBack() throws Exception {
		// Remove instruction from the queue if it is not a NOOP

		// Check if an instruction other than NOOP is in its Queue
		Instruction inst = removeInstructionFromQueue();
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

	public void dumpUnitDetails() {
		System.out.println("isPipelined - " + instance.isPipelined());
		System.out.println("isAvailable - " + instance.isAvailable());
		System.out.println("Pipeline Size - " + instance.getPipelineSize());
		System.out.println("Clock Cycles required - "
				+ instance.getClockCyclesRequired());
	}

	public boolean acceptIntruction(Instruction instruction) {

		if (!(instructionQueue.peekFirst() instanceof NOOP))
			System.out
					.println("WRITEBACK FPU Found a Real Instruction while offering a instruction: "
							+ instruction.printableInstruction);

		instructionQueue.addFirst(instruction);
		return true;
	}

}
