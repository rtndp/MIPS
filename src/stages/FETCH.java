package stages;

public class FETCH extends STAGE {

	@Override
	public void execute() {
		functionalUnits.FETCH fetch = new functionalUnits.FETCH();
		fetch.fetch();
	}

}
