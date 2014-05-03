package functionalUnits;

import instructions.Instruction;

public class DECODE extends FUNCTIONALUNIT {

	@Override
	public void addInstructionToQueue(Instruction instruction) {
		
		
	}

	@Override
	public Instruction removeInstructionFromQueue() {
		
		return null;
	}
	
	private boolean checkSTRUCT(){
		//Check for possible STRUCT hazards
		return false;
	}
	
	private boolean checkRAW(){
		//Check for possible RAW hazards
		
		//Check if the source registers are free, by making a call to RegisterManager, by passing the labels
		//RegisterManager.isRegisterFree(Label1)
		//RegisterManager.isRegisterFree(Label2)
		//if(true and true)
		//		No RAW hazard present
		//		Update the instruction to indicate the absence of RAW hazard; "N" 
		//else
		//		RAW hazard present
		// 		Update the instruction to indicate the presence of RAW hazard; "Y"
		
		return false;
	}
	
	private boolean checkWAW(){
		//Check for possible WAW hazards
		
		//Check if the destination register is free/available by making a call to the RegisterManager, by passing the destination label
		//RegisterManager.isRegisterFree(Destination)
		//if(true)
		//		No WAW hazard present
		//		Update the instruction to indicate the absence of WAW hazard; "N"
		//else
		//		WAW hazard present
		//		Update the instruction to indicate to presence of WAW hazard; "Y"
		
		return false;
	}
	
	private boolean checkWAR(){
		//Check for possible WAR hazards
		return false;
	}
	
	private String whatFunctionalUnit(){
		//Check what functional unit
		checkRAW();
		checkWAR();
		checkWAW();
		return null;
	}
	
	private boolean isFunctionalUnitFree(){
		//
		checkSTRUCT();
		return false;
	}
	
	
	
	public boolean decode(){
		//Called by the decode stage
		
		whatFunctionalUnit();
		isFunctionalUnitFree();
		
		
		return false;
	}

}
