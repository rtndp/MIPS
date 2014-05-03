package functionalUnits;

import config.ConfigManager;
import instructions.Instruction;

public class FPDIV extends FUNCTIONALUNIT {

	private static volatile FPDIV instance;

	public static FPDIV getInstance() {
		if (null == instance)
			synchronized (FPDIV.class) {
				if (null == instance)
					instance = new FPDIV();
			}

		return instance;
	}

	private FPDIV() {
		super();
		this.setPipelined(ConfigManager.instance.FPDividerPipelined);
		this.setClockCyclesRequired(ConfigManager.instance.FPDivideLatency);
		this.setAvailable(true);
		
		if(ConfigManager.instance.FPDividerPipelined)
			this.setPipelineSize(ConfigManager.instance.FPDivideLatency);
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
	
	public void fpdiv(){
		
	}
	
	public void dumpUnitDetails(){
		System.out.println("isPipelined - "+instance.isPipelined());
		System.out.println("isAvailable - "+instance.isAvailable());
		System.out.println("Pipeline Size - "+instance.getPipelineSize());
		System.out.println("Clock Cycles required - "+instance.getClockCyclesRequired());
	}

}
