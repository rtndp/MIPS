package stages;

import instructions.Instruction;


public class DecodeStage extends Stage {
	
	private static volatile DecodeStage instance;

	public static DecodeStage getInstance() {

		if (null == instance)
			synchronized (DecodeStage.class) {
				if (null == instance)
					instance = new DecodeStage();
			}

		return instance;
	}

	private functionalUnits.DecodeUnit decode;

	private DecodeStage() {
		super();
		decode = functionalUnits.DecodeUnit.getInstance();
	}
	
	@Override
	public void execute() {
		System.out.println("------------------------------");
		System.out.println("DECODE - ");
		/*decode.dumpUnitDetails();*/
		//decode.decode();
	}

	@Override
	public boolean checkIfFree(Instruction instruction) throws Exception {
		//TODO Implement this method
		return false;
	}

	@Override
	public boolean acceptInstruction(Instruction instruction) throws Exception {
		//TODO Implement this method
		return false;
	}

}
