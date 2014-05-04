package stages;

import functionalUnits.FunctionalUnit;
import instructions.FunctionalUnitType;
import instructions.Instruction;

public class ExStage extends Stage {

	private static volatile ExStage instance;

	public static ExStage getInstance() {

		if (null == instance)
			synchronized (ExStage.class) {
				if (null == instance)
					instance = new ExStage();
			}

		return instance;
	}

	private functionalUnits.IntegerUnit iu;
	private functionalUnits.MemoryUnit mem;
	private functionalUnits.FpAddUnit fpadd;
	private functionalUnits.FpMulUnit fpmul;
	private functionalUnits.FpDivUnit fpdiv;

	private ExStage() {
		super();
		iu = functionalUnits.IntegerUnit.getInstance();
		mem = functionalUnits.MemoryUnit.getInstance();
		fpadd = functionalUnits.FpAddUnit.getInstance();
		fpmul = functionalUnits.FpMulUnit.getInstance();
		fpdiv = functionalUnits.FpDivUnit.getInstance();
	}

	@Override
	public void execute() {
		System.out.println("------------------------------");
		System.out.println("IU - ");
		/* iu.dumpUnitDetails(); */

		System.out.println("------------------------------");
		System.out.println("MEM - ");
		// mem.dumpUnitDetails();

		System.out.println("------------------------------");
		System.out.println("FPADD - ");
		// fpadd.dumpUnitDetails();

		System.out.println("------------------------------");
		System.out.println("FPMUL - ");
		// fpmul.dumpUnitDetails();

		System.out.println("------------------------------");
		System.out.println("FPDIV - ");
		// fpdiv.dumpUnitDetails();

		/*
		 * List<String> functionalUnits = new ArrayList<String>();
		 * 
		 * List<String> winnerFunctionalUnit = tiebreaker(functionalUnits);
		 * 
		 * System.out.println(winnerFunctionalUnit);
		 * 
		 * if(peek().size() <= 1){
		 * 
		 * fpadd.fpadd(); fpmul.fpmul(); fpdiv.fpdiv(); iu.iu(); mem.mem();
		 * 
		 * }else{ tiebreaker(peek()); //run all the funtional units except the
		 * loser(s) of tiebreaker() }
		 */
	}

	/*
	 * private List<String> tiebreaker(List<String> functionalUnits){
	 * 
	 * return null; }
	 * 
	 * private List<String> peek(){ List<String> temp = new ArrayList<String>();
	 * 
	 * return temp; }
	 */

	// This method will be called by ID while executing and passing on the
	// instruction
	@Override
	public boolean acceptInstruction(Instruction instruction) {
		// TODO Implement this method
		return false;
	}

	// This method will be called by ID while executing and passing on the
	// instruction, and check for STRUCT hazard
	@Override
	public boolean checkIfFree(Instruction instruction) throws Exception {
		FunctionalUnit functionalUnit = whichFunctionalUnit(instruction);
		// TODO Implement this method
		return false;
	}

	/**
	 * 
	 * @param instruction
	 *            to find which FU to use
	 * @return
	 * @throws Exception
	 *             defensive
	 */
	@SuppressWarnings("incomplete-switch")
	private FunctionalUnit whichFunctionalUnit(Instruction instruction)
			throws Exception {

		if (instruction.functionalUnitType == FunctionalUnitType.UNKNOWN
				|| instruction.functionalUnitType == null)
			throw new Exception("EXSTAGE: Incorrect type"
					+ instruction.toString());

		switch (instruction.functionalUnitType) {

		case FPADD:
			return fpadd;

		case FPDIV:
			return fpdiv;

		case FPMUL:
			return fpmul;

		case IU:
			return iu;
		}

		return null;
	}

}
