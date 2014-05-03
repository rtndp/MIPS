package instructions;

import java.util.ArrayList;
import java.util.List;

public class SD extends Instruction{

	String sourceLabel;
	String destinationLabel;
	long source;
	long destination;
	int immediate;

	public SD(String sourceLabel, String desitnationLabel, int immediate) {
		super();
		this.sourceLabel = sourceLabel;
		this.destinationLabel = desitnationLabel;
		this.immediate = immediate;
	}

	@Override
	public List<String> getSourceRegister() {
		List<String> sourceRegisterList = new ArrayList<String>();
				sourceRegisterList.add(sourceLabel);
		return sourceRegisterList;
	}

	@Override
	public String getDestinationRegister() {
		return destinationLabel;
	}
	
	public int getImmediate(){
		return immediate;
	}
	
	@Override
	public String toString() {
		return "SD " + sourceLabel +" "+immediate+"("+destinationLabel+")";
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
