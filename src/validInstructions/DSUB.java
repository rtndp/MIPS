package validInstructions;

import instructionTypes.Type3Reg;
import enums.FunctionalUnitType;
import enums.InstructionType;

public class DSUB extends Type3Reg {
	public DSUB(String sourceLabel1, String sourceLabel2,
			String destinationLabel) {
		super(sourceLabel1, sourceLabel2, destinationLabel);
		this.functionalUnitType = FunctionalUnitType.IU;
		this.instructionType = InstructionType.ARITHMETIC_REG;
	}

	public DSUB(DSUB obj) {
		super(obj);
	}

	@Override
	public String toString() {
		return "DSUB " + dest.getDestinationLabel() + ", "
				+ src1.getSourceLabel() + ", " + src2.getSourceLabel();
	}

	@Override
	public void executeInstruction() {
		dest.setDestination(src1.getSource() - src2.getSource());
	}

}
