package validInstructions;

import instructionTypes.Type2Reg1Imm;
import enums.FunctionalUnitType;
import enums.InstructionType;

public class LW extends Type2Reg1Imm {

	public LW(String sourceLabel, String destinationLabel, int immediate) {
		super(sourceLabel, destinationLabel, immediate);
		this.functionalUnitType = FunctionalUnitType.IU;
		this.instructionType = InstructionType.MEMORY_REG;
	}

	public LW(LW obj) {
		super(obj);
	}

	@Override
	public String toString() {
		return "LW " + dest.getDestinationLabel() + ", " + immediate + "("
				+ src1.getSourceLabel() + ")";
	}

	@Override
	public void executeInstruction() {
		this.address = immediate + src1.getSource();
	}

}
