package instructions;

import java.util.ArrayList;
import java.util.List;

public class DSUBI extends Instruction{

	String sourceLabel;
	String destinationLabel;
	
	long source;
	long destination;

	int immediate;
	
	
	public DSUBI(String sourceLabel, String destinationLabel, int immediate) {
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
		return "DSUBI " + destinationLabel +" "+ sourceLabel +" "+ immediate;
	}

	@Override
	public void executeInstruction() {

		destination = source - immediate;
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
