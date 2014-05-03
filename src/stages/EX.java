package stages;


public class EX extends STAGE {
	
	private static volatile EX instance;

	public static EX getInstance() {

		if (null == instance)
			synchronized (EX.class) {
				if (null == instance)
					instance = new EX();
			}

		return instance;
	}
	
	private functionalUnits.IU iu;
	private functionalUnits.MEM mem;
	private functionalUnits.FPADD fpadd;
	private functionalUnits.FPMUL fpmul;
	private functionalUnits.FPDIV fpdiv;
	
	private EX() {
		super();
		iu = functionalUnits.IU.getInstance();
		mem = functionalUnits.MEM.getInstance();
		fpadd = functionalUnits.FPADD.getInstance();
		fpmul = functionalUnits.FPMUL.getInstance();
		fpdiv = functionalUnits.FPDIV.getInstance();
	}
	

	@Override
	public void execute() {
		System.out.println("------------------------------");
		System.out.println("IU - ");
		iu.dumpUnitDetails();
		
		System.out.println("------------------------------");
		System.out.println("MEM - ");
		mem.dumpUnitDetails();
		
		System.out.println("------------------------------");
		System.out.println("FPADD - ");
		fpadd.dumpUnitDetails();
		
		System.out.println("------------------------------");
		System.out.println("FPMUL - ");
		fpmul.dumpUnitDetails();
		
		System.out.println("------------------------------");
		System.out.println("FPDIV - ");
		fpdiv.dumpUnitDetails();
		
		/*List<String> functionalUnits = new ArrayList<String>();
		
		List<String> winnerFunctionalUnit = tiebreaker(functionalUnits);
		
		System.out.println(winnerFunctionalUnit);
		
		if(peek().size() <= 1){
				
			fpadd.fpadd();
			fpmul.fpmul();
			fpdiv.fpdiv();
			iu.iu();
			mem.mem();
		
		}else{
			tiebreaker(peek());
			//run all the funtional units except the loser(s) of tiebreaker()
		}*/
	}
	
	/*private List<String> tiebreaker(List<String> functionalUnits){
		
		return null;
	}
	
	private List<String> peek(){
		List<String> temp = new ArrayList<String>();
		
		return temp;
	}*/

}
