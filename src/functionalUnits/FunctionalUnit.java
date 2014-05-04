package functionalUnits;

import instructions.Instruction;
import instructions.NOOP;

import java.util.Deque;

import stages.CPU;

public abstract class FunctionalUnit {

	public boolean isPipelined;
	public int clockCyclesRequired;
	public int pipelineSize;
	public int stageId;
	public Deque<Instruction> instructionQueue;

	public abstract void executeUnit() throws Exception;

	public abstract int getClockCyclesRequiredForNonPipeLinedUnit()
			throws Exception;

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
		/* if (instruction.entryCycle[this.stageId] == 0) - Removed */
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

	public boolean isReadyToSend() throws Exception {
		if (isPipelined) {
			if (instructionQueue.peekLast() instanceof NOOP) {
				return true;
			}
		} else {
			if (instructionQueue.peekLast() instanceof NOOP
					&& CPU.CLOCK
							- instructionQueue.peekLast().entryCycle[stageId] >= getClockCyclesRequiredForNonPipeLinedUnit()) {
				return true;
			}
		}

		return false;
	}
}
