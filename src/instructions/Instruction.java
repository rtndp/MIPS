package instructions;

import java.util.List;

public abstract class Instruction {

	/*Hazards*/
	boolean RAW;
	boolean WAR;
	boolean WAW;
	boolean STRUCT;
	
	int[] entryCycle = new int[4];
	int[] exitCycle = new int[4];
	
	public Instruction() {
		super();
		this.RAW = false;
		this.WAR = false;
		this.WAW = false;
		this.STRUCT = false;
	}
	
	public abstract List<String> getSourceRegister();
	public abstract String getDestinationRegister();
	public abstract void executeInstruction();
	
}
