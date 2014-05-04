package stages;

import instructions.Instruction;

public abstract class Stage {

	public abstract void execute() throws Exception;

	public abstract boolean checkIfFree(Instruction instruction)
			throws Exception;

	public abstract boolean acceptInstruction(Instruction instruction)
			throws Exception;
}
