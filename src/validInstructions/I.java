package validInstructions;

import java.util.List;

public interface I {
	/*
	 * need list to check if not busy also need to set values in ID
	 */
	public List<SourceObject> getSourceRegister();

	/*
	 * Need to check for WAW hazards, WB & set dest busy
	 */
	public WriteBackObject getDestinationRegister();

	/*
	 * All execute does is locally do arithmetic operations or calculate target
	 * address
	 */
	public void executeInstruction();

	/*
	 * For Decode Instruction we need the following
	 */

}
