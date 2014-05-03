package functionalUnits;

import instructions.Instruction;

public class IU extends FUNCTIONALUNIT {
	
	private static volatile IU instance;

	public static IU getInstance() {
		if (null == instance)
			synchronized (IU.class) {
				if (null == instance)
					instance = new IU();
			}

		return instance;
	}

	private IU() {
		super();
		this.setPipelined(false);
		this.setClockCyclesRequired(1);
		this.setAvailable(true);
		this.setPipelineSize(1);

	}

	@Override
	public void addInstructionToQueue(Instruction instruction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Instruction removeInstructionFromQueue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void iu(){
		
	}
	
	public void dumpUnitDetails(){
		System.out.println("isPipelined - "+instance.isPipelined());
		System.out.println("isAvailable - "+instance.isAvailable());
		System.out.println("Pipeline Size - "+instance.getPipelineSize());
		System.out.println("Clock Cycles required - "+instance.getClockCyclesRequired());
	}

}
