package instructions;

import java.util.ArrayList;
import java.util.List;

public class LW extends Instruction{
	
	String sourceLabel;
	String destinationLabel;
	long source;
	long destination;
	int immediate;

	public LW(String sourceLabel, String desitnationLabel, int immediate) {
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
		return "LW" + destinationLabel +" "+immediate+"("+sourceLabel +")";
	}

	@Override
	public void executeInstruction() {
		// TODO Auto-generated method stub
		
	}
}
