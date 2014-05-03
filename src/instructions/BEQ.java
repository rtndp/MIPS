package instructions;

import java.util.ArrayList;
import java.util.List;

public class BEQ extends Instruction{
	
	String leftLabel;
	String rightLabel;
	
	long left;
	long right;
	
	String destinationLabel;
	
	public BEQ(String leftLabel, String rightLabel, String destinationLabel) {
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
		return "BEQ "+" "+leftLabel+" "+rightLabel+" "+destinationLabel;
	}

	@Override
	public void executeInstruction() {
		// TODO Auto-generated method stub
		
	}

}
