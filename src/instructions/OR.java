package instructions;

import java.util.ArrayList;
import java.util.List;

public class OR extends Instruction{

	String sourceLabel1;
	String sourceLabel2;
	String destinationLabel;
	
	long source1;
	long source2;
	long destination;

	@Override
	public List<String> getSourceRegister() {
		List<String> sourceRegisterList = new ArrayList<String>();
		
		sourceRegisterList.add(this.sourceLabel1);
		sourceRegisterList.add(this.sourceLabel2);
		
		return sourceRegisterList;
	}

	@Override
	public String getDestinationRegister() {

		return destinationLabel;
	}
	
	@Override
	public String toString() {
		return "OR " + destinationLabel +" "+ sourceLabel1 +" "+ sourceLabel2;
	}

	@Override
	public void executeInstruction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decodeInstruction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeBackResult() {
		// TODO Auto-generated method stub
		
	}

}
