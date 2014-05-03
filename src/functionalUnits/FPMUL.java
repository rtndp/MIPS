package functionalUnits;

import config.ConfigManager;
import instructions.Instruction;

public class FPMUL extends FUNCTIONALUNIT {
	
	private static volatile FPMUL instance;

	public static FPMUL getInstance() {
		if (null == instance)
			synchronized (FPMUL.class) {
				if (null == instance)
					instance = new FPMUL();
			}

		return instance;
	}

	private FPMUL() {
		super();
		this.setPipelined(ConfigManager.instance.FPMultPipelined);
		this.setClockCyclesRequired(ConfigManager.instance.FPMultLatency);
		this.setAvailable(true);
		
		if(ConfigManager.instance.FPMultPipelined)
			this.setPipelineSize(ConfigManager.instance.FPMultLatency);
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

	public void fpmul(){
		
	}
	
	public void dumpUnitDetails(){
		System.out.println("isPipelined - "+instance.isPipelined());
		System.out.println("isAvailable - "+instance.isAvailable());
		System.out.println("Pipeline Size - "+instance.getPipelineSize());
		System.out.println("Clock Cycles required - "+instance.getClockCyclesRequired());
	}
}
