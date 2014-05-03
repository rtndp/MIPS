package functionalUnits;

import instructions.Instruction;

public class FETCH extends FUNCTIONALUNIT {

	@Override
	public void addInstructionToQueue(Instruction instruction) {
			
	}

	@Override
	public Instruction removeInstructionFromQueue() {
		
		return null;
	}
	
	private void process(){
		
	}
	
	private void enqueueInstruction(){
		
	}
	
	public void fetch(){
		//Called by fetch stage
		process();
		enqueueInstruction();
	}

}
