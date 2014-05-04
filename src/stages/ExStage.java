package stages;

import instructions.FunctionalUnitType;
import instructions.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.collections4.ListUtils;

import functionalUnits.FpAddUnit;
import functionalUnits.FpDivUnit;
import functionalUnits.FpMulUnit;
import functionalUnits.FunctionalUnit;
import functionalUnits.MemoryUnit;

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
	private List<FunctionalUnit> tieBreakerList;

	private ExStage() {
		super();
		iu = functionalUnits.IntegerUnit.getInstance();
		mem = functionalUnits.MemoryUnit.getInstance();
		fpadd = functionalUnits.FpAddUnit.getInstance();
		fpmul = functionalUnits.FpMulUnit.getInstance();
		fpdiv = functionalUnits.FpDivUnit.getInstance();

		tieBreakerList = new ArrayList<FunctionalUnit>();
		tieBreakerList.add(mem);
		tieBreakerList.add(fpadd);
		tieBreakerList.add(fpmul);
		tieBreakerList.add(fpdiv);
	}

	@Override
	public void execute() throws Exception {
		
		
		//System.out.println(tieBreaker(functionalUnitList).getClass().getName());

		List<FunctionalUnit> readyList = new ArrayList<FunctionalUnit>();

		for (FunctionalUnit functionalUnit : tieBreakerList) {
			if (functionalUnit.isReadyToSend())
				readyList.add(functionalUnit);
		}

		if (readyList.size() <= 1) {
			for (FunctionalUnit functionalUnit : tieBreakerList) {
				functionalUnit.executeUnit();
			}
		} else {
			
			List<FunctionalUnit> winnerList = new ArrayList<FunctionalUnit>();
			winnerList.add(tieBreaker(readyList));//

			List<FunctionalUnit> losersList = ListUtils.subtract(readyList,
					winnerList);
			
			System.out.println("Loser List - ");
			for (FunctionalUnit functionalUnit : losersList) {
				System.out.println(functionalUnit.getClass().getName());
			}

			List<FunctionalUnit> exeList = ListUtils.subtract(tieBreakerList,
					losersList);
			
			System.out.println("Final List [for exe] - ");
			for (FunctionalUnit functionalUnit : exeList) {
				System.out.println(functionalUnit.getClass().getName());
			}
			/*
			 * for (FunctionalUnit functionalUnit :
			 * readyList.removeA(loserList)) { functionalUnit.executeUnit(); }
			 */
		}

		/*iu.executeUnit();*/
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
	public boolean acceptInstruction(Instruction instruction) throws Exception {
		// TODO Implement this method
		FunctionalUnit functionalUnit = getFunctionalUnit(instruction);
		if (!functionalUnit.checkIfFree(instruction))
			throw new Exception("EXSTAGE: Illegal state exception "
					+ instruction.toString());

		functionalUnit.acceptInstruction(instruction);

		return true;
	}

	// This method will be called by ID while executing and passing on the
	// instruction, and check for STRUCT hazard
	@Override
	public boolean checkIfFree(Instruction instruction) throws Exception {
		FunctionalUnit functionalUnit = getFunctionalUnit(instruction);
		return functionalUnit.checkIfFree(instruction);
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
	private FunctionalUnit getFunctionalUnit(Instruction instruction)
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
	
	private FunctionalUnit tieBreaker(List<FunctionalUnit> tieList) {
		TreeMap<Integer, FunctionalUnit> fUMap = new TreeMap<Integer, FunctionalUnit>();

		for (FunctionalUnit fu : tieList) {

			if (fu instanceof MemoryUnit) {
				mergeFUMap(fu.clockCyclesRequired + 1, fu, fUMap);
				continue;
			}

			if (fu.isPipelined) {
				mergeFUMap(fu.clockCyclesRequired, fu, fUMap);
			} else {
				mergeFUMap(1000 + fu.clockCyclesRequired, fu, fUMap);
			}
		}

		return fUMap.get(fUMap.lastKey());
	}

	private void mergeFUMap(int calculatedKey, FunctionalUnit fu,
			TreeMap<Integer, FunctionalUnit> map) {

		if (map.containsKey(calculatedKey)) {

			FunctionalUnit mapEntry = (FunctionalUnit) map.get(calculatedKey);
			int fuEntry = fu.instructionQueue.peekLast().entryCycle[0];
			int localEntry = mapEntry.instructionQueue.peekLast().entryCycle[0];
			if (fuEntry < localEntry)
				map.put(calculatedKey, fu);

		} else {
			map.put(calculatedKey, fu);
		}
	}

	
}
