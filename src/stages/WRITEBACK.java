package stages;

public class WRITEBACK extends STAGE {

	@Override
	public void execute() {
		
		functionalUnits.WRITEBACK writeBack = new functionalUnits.WRITEBACK();
		writeBack.writeBack();
	}

}
