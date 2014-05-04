package stages;

import instructions.Instruction;

public class WriteBackStage extends Stage {

	private static volatile WriteBackStage instance;

	public static WriteBackStage getInstance() {

		if (null == instance)
			synchronized (WriteBackStage.class) {
				if (null == instance)
					instance = new WriteBackStage();
			}

		return instance;
	}

	private functionalUnits.WriteBackUnit writeBack;

	private WriteBackStage() {
		super();
		writeBack = functionalUnits.WriteBackUnit.getInstance();
	}

	@Override
	public void execute() throws Exception {
		/*
		 * System.out.println("------------------------------");
		 * System.out.println("WRITEBACK - "); writeBack.dumpUnitDetails();
		 * System.out.println("------------------------------");
		 */

		writeBack.executeUnit();
	}

	@Override
	public boolean acceptInstruction(Instruction instruction) throws Exception {
		writeBack.acceptInstruction(instruction);
		return true;
	}

	@Override
	public boolean checkIfFree(Instruction instruction) throws Exception {
		return writeBack.checkIfFree(instruction);
	}
}
