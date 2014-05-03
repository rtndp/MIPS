package stages;


public class DECODE extends STAGE {
	
	private static volatile DECODE instance;

	public static DECODE getInstance() {

		if (null == instance)
			synchronized (DECODE.class) {
				if (null == instance)
					instance = new DECODE();
			}

		return instance;
	}

	private functionalUnits.DECODE decode;

	private DECODE() {
		super();
		decode = functionalUnits.DECODE.getInstance();
	}
	
	@Override
	public void execute() {
		System.out.println("------------------------------");
		System.out.println("DECODE - ");
		decode.dumpUnitDetails();
		//decode.decode();
	}

}
