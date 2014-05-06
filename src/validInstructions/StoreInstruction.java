package validInstructions;

import java.util.ArrayList;
import java.util.List;

public abstract class StoreInstruction extends DI {
	SourceObject src1, src2;
	int immediate;

	public StoreInstruction(String sourceLabel1, String sourceLabel2,
			int immediate) {
		super();
		src1 = new SourceObject(sourceLabel1, 0);
		src2 = new SourceObject(sourceLabel2, 0);
		this.immediate = immediate;
	}

	public StoreInstruction(StoreInstruction obj) {
		super(obj);
		setPrintableInstruction(obj.printableInstruction);
		this.src1 = new SourceObject(obj.src1);
		this.src2 = new SourceObject(obj.src2);
		this.immediate = obj.immediate;
	}

	@Override
	public List<SourceObject> getSourceRegister() {
		List<SourceObject> sourceRegisterList = new ArrayList<SourceObject>();
		sourceRegisterList.add(src1);
		sourceRegisterList.add(src2);
		return sourceRegisterList;
	}

	@Override
	public WriteBackObject getDestinationRegister() {
		return null;
	}

	@Override
	public void executeInstruction() {
		this.address = immediate + src2.getSource();
	}

	public SourceObject getValueToWrite() {
		return src1;
	}
}
