package stages;


public class DECODE extends STAGE {

	@Override
	public void execute() {
		functionalUnits.DECODE decode = new functionalUnits.DECODE();
		decode.decode();
	}

}
