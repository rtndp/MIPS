package instructions;

import java.util.List;

public abstract class Instruction {

	/*Hazards*/
	public boolean RAW;
	public boolean WAR;
	public boolean WAW;
	public boolean STRUCT;
	
	public int[] entryCycle = new int[4];
	public int[] exitCycle = new int[4];
	
	public String  printableInstruction;
	
	public Instruction() {
		super();
		this.RAW = false;
		this.WAR = false;
		this.WAW = false;
		this.STRUCT = false;
	}
	
	public Instruction(Instruction obj)
    {
        super();
        // System.out.println("Copy Constructor of Instruction SuperClass: "
        // + obj.printableInstruction);
        this.RAW = obj.RAW;
        this.WAR = obj.WAR;
        this.WAW = obj.WAW;
        this.STRUCT = obj.STRUCT;
        this.entryCycle = new int[obj.entryCycle.length];
        this.exitCycle = new int[obj.exitCycle.length];
        System.arraycopy(obj.entryCycle, 0, this.entryCycle, 0,
                this.entryCycle.length);
        System.arraycopy(obj.exitCycle, 0, this.exitCycle, 0,
                this.exitCycle.length);
    }

    // Purely for decorative purposes
    public void setPrintableInstruction(String str)
    {
        this.printableInstruction = str;
    }
	
	public abstract List<String> getSourceRegister();
	public abstract String getDestinationRegister();
	
	public abstract void executeInstruction();
	public abstract void decodeInstruction();
	
	
	public abstract WriteBackObject getWriteBackObject();
	
}
