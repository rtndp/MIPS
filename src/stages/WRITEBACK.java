package stages;

import instructions.Instruction;

public class WRITEBACK extends STAGE {

	private static volatile WRITEBACK instance;

	public static WRITEBACK getInstance() {

		if (null == instance)
			synchronized (WRITEBACK.class) {
				if (null == instance)
					instance = new WRITEBACK();
			}

		return instance;
	}

	private functionalUnits.WRITEBACK writeBack;

	private WRITEBACK() {
		super();
		writeBack = functionalUnits.WRITEBACK.getInstance();
	}

	@Override
	public void execute() throws Exception {
		System.out.println("------------------------------");
		System.out.println("WRITEBACK - ");
		writeBack.dumpUnitDetails();
		System.out.println("------------------------------");

		writeBack.writeBack();
	}

	public boolean acceptIntruction(Instruction instruction) {
		return writeBack.acceptIntruction(instruction);
	}
}
