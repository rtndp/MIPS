package instructions;

import java.util.ArrayList;
import java.util.List;

public class DADDI extends Instruction{

	String sourceLabel;
	String destinationLabel;
	
	long source;
	long destination;

	int immediate;
	
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
		return "DADDI " +destinationLabel+" "+sourceLabel+" "+immediate;
	}

}
