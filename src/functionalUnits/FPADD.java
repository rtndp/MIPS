package functionalUnits;

import config.ConfigManager;
import instructions.Instruction;


public class FPADD extends FUNCTIONALUNIT {
	
	private static volatile FPADD instance;

	public static FPADD getInstance() {
		if (null == instance)
			synchronized (FPADD.class) {
				if (null == instance)
					instance = new FPADD();
			}

		return instance;
	}

	private FPADD() {
		super();
		
		this.setPipelined(ConfigManager.instance.FPAdderPipelined);
		this.setClockCyclesRequired(ConfigManager.instance.FPAdderLatency);
		this.setAvailable(true);
		
		if(ConfigManager.instance.FPAdderPipelined)
			this.setPipelineSize(ConfigManager.instance.FPAdderLatency);
		else
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
	
	public void fpadd(){
		
	}
	
	public void dumpUnitDetails(){
		System.out.println("isPipelined - "+instance.isPipelined());
		System.out.println("isAvailable - "+instance.isAvailable());
		System.out.println("Pipeline Size - "+instance.getPipelineSize());
		System.out.println("Clock Cycles required - "+instance.getClockCyclesRequired());
	}

}
