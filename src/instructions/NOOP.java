package instructions;

import java.util.List;

public class NOOP extends Instruction{

	@Override
	public List<String> getSourceRegister() {
		// Do nothing here 
		return null;
	}

	@Override
	public String getDestinationRegister() {
		// Do nothing here
		return null;
	}

	@Override
	public void executeInstruction() {
		// Do nothing here
		
	}

	@Override
	public void decodeInstruction() {
		// Do nothing here
		
	}

	@Override
    public WriteBackObject getWriteBackObject()
    {
        return null;
    }

}
