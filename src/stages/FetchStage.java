package stages;

import instructions.Instruction;

public class FetchStage extends Stage {

	private static volatile FetchStage instance;

	public static FetchStage getInstance() {

		if (null == instance)
			synchronized (FetchStage.class) {
				if (null == instance)
					instance = new FetchStage();
			}

		return instance;
	}

	private functionalUnits.FetchUnit fetch;

	private FetchStage() {
		super();
		fetch = functionalUnits.FetchUnit.getInstance();
	}

	@Override
	public void execute() {
		System.out.println("------------------------------");
		System.out.println("FETCH - ");
		// fetch.dumpUnitDetails();
		/*
		 * if(fetch.fetch()) CPU.PROGRAM_COUNTER++; else return;
		 */
	}

	@Override
	public boolean checkIfFree(Instruction instruction) throws Exception {
		// TODO Implement this method
		return false;
	}

	@Override
	public boolean acceptInstruction(Instruction instruction) throws Exception {
		// TODO Implement this method
		return false;
	}

}
