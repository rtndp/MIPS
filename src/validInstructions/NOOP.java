package validInstructions;

import java.util.List;

import enums.InstructionType;

public class NOOP extends DI {

	public NOOP() {
		super();
		this.instructionType = InstructionType.NOOP;
	}

	public NOOP(NOOP obj) {
		super(obj);
		setPrintableInstruction(obj.printableInstruction);
	}

	@Override
	public List<SourceObject> getSourceRegister() {
		// Do nothing here
		return null;
	}

	@Override
	public WriteBackObject getDestinationRegister() {
		// Do nothing here
		return null;
	}

	@Override
	public void executeInstruction() {
		// Do nothing here

	}

	@Override
	public String toString() {
		return "NOOP";
	}

}
