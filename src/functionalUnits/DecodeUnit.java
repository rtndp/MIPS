package functionalUnits;

import java.util.ArrayDeque;

import instructions.Instruction;
import instructions.NOOP;

public class DecodeUnit extends FunctionalUnit {

	private static volatile DecodeUnit instance;

	public static DecodeUnit getInstance() {
		if (null == instance)
			synchronized (DecodeUnit.class) {
				if (null == instance)
					instance = new DecodeUnit();
			}

		return instance;
	}

	private DecodeUnit() {
		super();
		this.isPipelined = false;
		this.clockCyclesRequired = 1;
		this.pipelineSize = 1;
		this.stageId = 1;

		this.instructionQueue = new ArrayDeque<Instruction>();
		for (int i = 0; i < this.pipelineSize; i++)
			this.instructionQueue.add(new NOOP());

	}

	private boolean checkSTRUCT() {
		// Check for possible STRUCT hazards
		return false;
	}

	private boolean checkRAW() {
		// Check for possible RAW hazards

		// Check if the source registers are free, by making a call to
		// RegisterManager, by passing the labels
		// RegisterManager.isRegisterFree(Label1)
		// RegisterManager.isRegisterFree(Label2)
		// if(true and true)
		// No RAW hazard present
		// Update the instruction to indicate the absence of RAW hazard; "N"
		// else
		// RAW hazard present
		// Update the instruction to indicate the presence of RAW hazard; "Y"

		return false;
	}

	private boolean checkWAW() {
		// Check for possible WAW hazards

		// Check if the destination register is free/available by making a call
		// to the RegisterManager, by passing the destination label
		// RegisterManager.isRegisterFree(Destination)
		// if(true)
		// No WAW hazard present
		// Update the instruction to indicate the absence of WAW hazard; "N"
		// else
		// WAW hazard present
		// Update the instruction to indicate to presence of WAW hazard; "Y"

		return false;
	}

	private boolean checkWAR() {
		// Check for possible WAR hazards
		return false;
	}

	private String whatFunctionalUnit() {
		// Check what functional unit
		checkRAW();
		checkWAR();
		checkWAW();
		return null;
	}

	private boolean isFunctionalUnitFree() {
		//
		checkSTRUCT();
		return false;
	}
	
	@Override
	public void executeUnit() {
		// Called by the decode stage

		whatFunctionalUnit();
		isFunctionalUnitFree();

	}
	
	@Override
	public int getClockCyclesRequiredForNonPipeLinedUnit() {
		// TODO Auto-generated method stub
		return clockCyclesRequired;
	}
	/*public void dumpUnitDetails() {
		System.out.println("isPipelined - " + instance.isPipelined());
		System.out.println("isAvailable - " + instance.isAvailable());
		System.out.println("Pipeline Size - " + instance.getPipelineSize());
		System.out.println("Clock Cycles required - "
				+ instance.getClockCyclesRequired());
	}*/

}
