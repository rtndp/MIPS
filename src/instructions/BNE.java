package instructions;

import java.util.ArrayList;
import java.util.List;

public class BNE extends Instruction{

	String leftLabel;
	String rightLabel;
	
	long left;
	long right;
	
	String destinationLabel;
	
	public BNE(String leftLabel, String rightLabel, String destinationLabel) {
		super();
		this.leftLabel = leftLabel;
		this.rightLabel = rightLabel;
		this.destinationLabel = destinationLabel;
	}

	@Override
	public List<String> getSourceRegister() {
		List<String> sourceRegisterList = new ArrayList<String>();
		
		sourceRegisterList.add(this.leftLabel);
		sourceRegisterList.add(this.rightLabel);
		
		return sourceRegisterList;
	}

	@Override
	public String getDestinationRegister() {
		
		return null;
	}
	
	public String getDestinationLabel(){
		return destinationLabel;
	}
	
	@Override
	public String toString() {
		return "BNE "+" "+leftLabel+" "+rightLabel+" "+destinationLabel;
	}

	@Override
	public void executeInstruction() {
		// Do nothing here
		
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
