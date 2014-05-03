package functionalUnits;

import instructions.Instruction;

import java.util.Queue;

public abstract class FUNCTIONALUNIT {
	
	boolean isPipelined;
	boolean isAvailable;
	int clockCyclesRequired;
	Queue<Instruction> instructionQueue;

	public abstract void addInstructionToQueue(Instruction instruction);
	public abstract Instruction removeInstructionFromQueue();

}
