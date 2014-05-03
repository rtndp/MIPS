package functionalUnits;

import instructions.Instruction;

public class WRITEBACK extends FUNCTIONALUNIT {

	private static volatile WRITEBACK instance;

	public static WRITEBACK getInstance() {
		if (null == instance)
			synchronized (WRITEBACK.class) {
				if (null == instance)
					instance = new WRITEBACK();
			}

		return instance;
	}

	private WRITEBACK() {
		super();
		this.setPipelined(false);
		this.setClockCyclesRequired(1);
		this.setAvailable(true);
		this.setPipelineSize(1);

	}

	@Override
	public void addInstructionToQueue(Instruction instruction) {
		// This instruction will do nothing in the WRITEBACK stage

	}

	@Override
	public Instruction removeInstructionFromQueue() {
		// TODO Auto-generated method stub
		
		return null;
	}

	// This method will be invoked by the WRITEBACK stage
	public void writeBack() {
		// Remove instruction from the queue if it is not a NOOP

		removeInstructionFromQueue();

	}
	
	public void dumpUnitDetails(){
		System.out.println("isPipelined - "+instance.isPipelined());
		System.out.println("isAvailable - "+instance.isAvailable());
		System.out.println("Pipeline Size - "+instance.getPipelineSize());
		System.out.println("Clock Cycles required - "+instance.getClockCyclesRequired());
	}

}
