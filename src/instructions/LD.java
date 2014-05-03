package instructions;

import java.util.ArrayList;
import java.util.List;

public class LD extends Instruction{
	
	String sourceLabel;
	String destinationLabel;
	long source;
	long destination;
	int immediate;

	public LD(String sourceLabel, String desitnationLabel, int immediate) {
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
		return "LD" + destinationLabel +" "+immediate+"("+sourceLabel +")";
	}
}
