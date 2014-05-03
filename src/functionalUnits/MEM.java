package functionalUnits;

import config.ConfigManager;
import instructions.Instruction;

public class MEM extends FUNCTIONALUNIT {

	private static volatile MEM instance;

	public static MEM getInstance() {
		if (null == instance)
			synchronized (MEM.class) {
				if (null == instance)
					instance = new MEM();
			}

		return instance;
	}

	private MEM() {
		super();
		this.setPipelined(false);
		this.setClockCyclesRequired(ConfigManager.instance.MemoryLatency);
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

	public void mem(){
		
	}
	
	public void dumpUnitDetails(){
		System.out.println("isPipelined - "+instance.isPipelined());
		System.out.println("isAvailable - "+instance.isAvailable());
		System.out.println("Pipeline Size - "+instance.getPipelineSize());
		System.out.println("Clock Cycles required - "+instance.getClockCyclesRequired());
	}
	
}
