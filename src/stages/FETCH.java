package stages;

public class FETCH extends STAGE {
	
	private static volatile FETCH instance;

	public static FETCH getInstance() {

		if (null == instance)
			synchronized (FETCH.class) {
				if (null == instance)
					instance = new FETCH();
			}

		return instance;
	}

	private functionalUnits.FETCH fetch;

	private FETCH() {
		super();
		fetch = functionalUnits.FETCH.getInstance();
	}

	@Override
	public void execute() {
		System.out.println("------------------------------");
		System.out.println("FETCH - ");
		fetch.dumpUnitDetails();
		/*if(fetch.fetch())
			CPU.PROGRAM_COUNTER++;
		else
			return;*/
	}

}
