package stages;

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
	public void execute() {
		System.out.println("------------------------------");
		System.out.println("WRITEBACK - ");
		writeBack.dumpUnitDetails();
		//writeBack.writeBack();
	}

}
