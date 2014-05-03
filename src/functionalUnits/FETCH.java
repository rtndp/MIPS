package functionalUnits;

import instructions.Instruction;

public class FETCH extends FUNCTIONALUNIT {
	
	private static volatile FETCH instance;

	public static FETCH getInstance() {
		if (null == instance)
			synchronized (FETCH.class) {
				if (null == instance)
					instance = new FETCH();
			}

		return instance;
	}

	private FETCH() {
		super();
		this.setPipelined(false);
		this.setClockCyclesRequired(1);
		this.setAvailable(true);
		this.setPipelineSize(1);

	}
	
	
	@Override
	public void addInstructionToQueue(Instruction instruction) {
			
	}

	@Override
	public Instruction removeInstructionFromQueue() {
		
		return null;
	}
	
	private void process(){
		
	}
	
	private boolean enqueueInstruction(){
		
		return false;
	}
	
	public boolean fetch(){
		//Called by fetch stage
		process();
		
		if(enqueueInstruction())
			return true;
		else
			return false;
	}
	
	public void dumpUnitDetails(){
		System.out.println("isPipelined - "+instance.isPipelined());
		System.out.println("isAvailable - "+instance.isAvailable());
		System.out.println("Pipeline Size - "+instance.getPipelineSize());
		System.out.println("Clock Cycles required - "+instance.getClockCyclesRequired());
	}

}
