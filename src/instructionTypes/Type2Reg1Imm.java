package instructionTypes;

import java.util.ArrayList;
import java.util.List;

import validInstructions.DI;
import validInstructions.SourceObject;
import validInstructions.WriteBackObject;

public abstract class Type2Reg1Imm extends DI {
	public SourceObject src1;
	public WriteBackObject dest;
	public int immediate;

	public Type2Reg1Imm(String sourceLabel,
			String destinationLabel, int immediate) {
		super();
		src1 = new SourceObject(sourceLabel, 0);
		dest = new WriteBackObject(destinationLabel, 0);
		this.immediate = immediate;
	}

	public Type2Reg1Imm(Type2Reg1Imm obj) {
		super(obj);
		setPrintableInstruction(obj.printableInstruction);
		this.src1 = new SourceObject(obj.src1);
		this.dest = new WriteBackObject(obj.dest);
		this.immediate = obj.immediate;
	}

	@Override
	public List<SourceObject> getSourceRegister() {
		List<SourceObject> sourceRegisterList = new ArrayList<SourceObject>();
		sourceRegisterList.add(src1);
		return sourceRegisterList;
	}

	@Override
	public WriteBackObject getDestinationRegister() {
		return dest;
	}
}
