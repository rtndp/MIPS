package functionalUnits;

import instructions.Instruction;
import instructions.NOOP;

import java.util.Deque;

import stages.CPU;

public abstract class FunctionalUnit {

	boolean isPipelined;
	int clockCyclesRequired;
	int pipelineSize;
	int stageId;
	Deque<Instruction> instructionQueue;

	public abstract void executeUnit() throws Exception;

	// TODO: Increment entry cycle and exit cycle clock depending on stage
	// number
	public void acceptInstruction(Instruction instruction) throws Exception {

		if (!checkIfFree(instruction))
			throw new Exception("FUNCTIONALUNIT: Illegal state of queue");

		instructionQueue.removeFirst();
		instructionQueue.addFirst(instruction);

		validateQueueSize();

		if (this.stageId > 0)
			instruction.exitCycle[this.stageId - 1] = CPU.CLOCK;

		// This is hack for IU to MEM
		if (instruction.entryCycle[this.stageId] == 0)
			instruction.entryCycle[this.stageId] = CPU.CLOCK;

	}

	private void validateQueueSize() throws Exception {
		if (instructionQueue.size() != pipelineSize)
			throw new Exception("FUNCTIONALUNIT: Invalid Queue Size for unit "
					+ this.getClass().getName());
	}

	// This is being done for the execute stage functional units.
	public boolean checkIfFree(Instruction instruction) throws Exception {
		validateQueueSize();

		return (instructionQueue.peekFirst() instanceof NOOP) ? true : false;

	}
}
