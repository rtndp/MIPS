package stages;

import java.util.ArrayList;
import java.util.List;

import functionalUnits.FPADD;
import functionalUnits.FPDIV;
import functionalUnits.FPMUL;
import functionalUnits.IU;
import functionalUnits.MEM;

public class EX extends STAGE {

	@Override
	public void execute() {
		
		List<String> functionalUnits = new ArrayList<String>();
		
		List<String> winnerFunctionalUnit = tiebreaker(functionalUnits);
		
		System.out.println(winnerFunctionalUnit);
		
		if(peek().size() <= 1){
				
			FPADD fpadd = new FPADD();
			fpadd.fpadd();
			
			FPMUL fpmul = new FPMUL();
			fpmul.fpmul();
			
			FPDIV fpdiv = new FPDIV();
			fpdiv.fpdiv();
			
			IU iu = new IU();
			iu.iu();
			
			MEM mem = new MEM();
			mem.mem();
		
		}else{
			tiebreaker(peek());
			//run all the funtional units except the loser(s) of tiebreaker()
		}
		
		
	}
	
	private List<String> tiebreaker(List<String> functionalUnits){
		
		return null;
	}
	
	private List<String> peek(){
		List<String> temp = new ArrayList<String>();
		
		return temp;
	}

}
