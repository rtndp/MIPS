package start;

import managers.ProgramManager;
import parsers.ConfigTxtParser;
import parsers.DataTxtParser;
import parsers.InstTxtParser;
import parsers.RegTxtParser;
import stages.CPU;
import stages.CPU.RUN;
import stages.DecodeStage;
import stages.ExStage;
import stages.FetchStage;
import stages.WriteBackStage;
import utility.Display;

public class Start {
	/**
	 * 
	 * @param args
	 *            inst.txt data.txt reg.txt config.txt result.txt
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		InstTxtParser.parse(args[0]);
		ProgramManager.instance.dumpProgram();

		DataTxtParser.parseMemoryFile(args[1]);

		RegTxtParser.parseRegister(args[2]);

		ConfigTxtParser.parseConfigFile(args[3]);

		Display.instance.setResultsPath(args[4]);

		/**
		 * Initialize Global CLOCK and PC to 0
		 */
		CPU.CLOCK = 0;
		CPU.PROGRAM_COUNTER = 0;
		CPU.RUN_TYPE = RUN.MEMORY;

		WriteBackStage wbStage = WriteBackStage.getInstance();
		ExStage exStage = ExStage.getInstance();
		DecodeStage idStage = DecodeStage.getInstance();
		FetchStage ifStage = FetchStage.getInstance();

		try {

			// I run these many clock cycles after HLT to flush pipeline
			int extraCLKCount = 5000;
			while (extraCLKCount != 0) {

				wbStage.execute();
				exStage.execute();

				// Well this is just stupid way of doing this
				if (!Display.instance.isHALT()) {
					idStage.execute();

					if (!Display.instance.isHALT()) {
						ifStage.execute();
					}
				} else
					extraCLKCount--;

				CPU.CLOCK++;
			}
		} catch (Exception e) {
			System.out.println("ERROR: CLOCK=" + CPU.CLOCK);
			e.printStackTrace();
		} finally {
		}
		Thread.sleep(1000L);
		System.out.println("Results");
		Display.instance.printResults();
		Display.instance.writeResults();

	}
}
