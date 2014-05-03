package functionalUnits;

import instructions.Instruction;

import java.util.Queue;

public abstract class FUNCTIONALUNIT {
	
	private boolean isPipelined;
	private boolean isAvailable;
	private int clockCyclesRequired;
	private Queue<Instruction> instructionQueue;

	
	
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
	
	public void setInstructionQueue(Queue<Instruction> instructionQueue) {
		this.instructionQueue = instructionQueue;
	}
	
	public abstract void addInstructionToQueue(Instruction instruction);
	public abstract Instruction removeInstructionFromQueue();
	
}
