package instructions;

import java.util.List;

public class J extends Instruction{
	
	String destinationLabel;
	
	public J(String destinationLabel) {
		super();
		this.destinationLabel = destinationLabel;
	}

	@Override
	public List<String> getSourceRegister() {
		return null;
	}

	@Override
	public String getDestinationRegister() {
		return destinationLabel;
	}
	
	@Override
	public String toString() {
		return "J " +destinationLabel;
	}

	@Override
	public void executeInstruction() {
		// TODO Auto-generated method stub
		
	}

}
