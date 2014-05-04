package functionalUnits;

import instructions.Instruction;

import java.util.Deque;
import java.util.Queue;

public abstract class FUNCTIONALUNIT {

	boolean isPipelined;
	boolean isAvailable;
	int clockCyclesRequired;
	int pipelineSize;
	Deque<Instruction> instructionQueue;

	public boolean isPipelined() {
		return isPipelined;
	}

	public void setPipelined(boolean isPipelined) {
		this.isPipelined = isPipelined;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getClockCyclesRequired() {
		return clockCyclesRequired;
	}

	public void setClockCyclesRequired(int clockCyclesRequired) {
		this.clockCyclesRequired = clockCyclesRequired;
	}

	public Queue<Instruction> getInstructionQueue() {
		return instructionQueue;
	}

	public void setInstructionQueue(Deque<Instruction> instructionQueue) {
		this.instructionQueue = instructionQueue;
	}

	public int getPipelineSize() {
		return pipelineSize;
	}

	public void setPipelineSize(int pipelineSize) {
		this.pipelineSize = pipelineSize;
	}

	public abstract void addInstructionToQueue(Instruction instruction);

	public abstract Instruction removeInstructionFromQueue();

}
