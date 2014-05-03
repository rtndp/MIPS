package instructions;

import java.util.ArrayList;
import java.util.List;

public class ANDI extends Instruction{

	String sourceLabel;
	String destinationLabel;
	
	long source;
	long destination;

	int immediate;
	
	public ANDI(String sourceLabel, String destinationLabel, int immediate) {
		super();
		this.sourceLabel = sourceLabel;
		this.destinationLabel = destinationLabel;
		this.immediate = immediate;
	}

	@Override
	public List<String> getSourceRegister() {

		List<String> sourceRegisterList = new ArrayList<String>();
		sourceRegisterList.add(this.sourceLabel);
		return sourceRegisterList;
	
	}

	@Override
	public String getDestinationRegister() {

		return destinationLabel;
	
	}
	
	
	public int getImmediate(){
		
		return this.immediate;
		
	}
	
	@Override
	public String toString() {
		return "ANDI " + destinationLabel +" "+ sourceLabel +" "+ immediate;
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
