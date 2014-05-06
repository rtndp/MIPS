package start;

import parsers.ConfigTxtParser;
import parsers.DataTxtParser;
import parsers.InstTxtParser;
import parsers.RegTxtParser;
import stages.DecodeStage;
import stages.ExStage;
import stages.FetchStage;
import stages.ProcessorParams;
import stages.WriteBackStage;
import utility.Display;
import enums.ExecutionType;

public class Start {
	/**
	 * 
	 * @param args
	 *            inst.txt data.txt reg.txt config.txt result.txt
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		/**
		 * Initialize CPU parameters
		 */
		ProcessorParams.CC = 0;
		ProcessorParams.PC = 0;
		ProcessorParams.exeType = ExecutionType.M;
		
		/**
		 * Parse inst.txt, data.txt, reg.txt, config.txt result.txt
		 */
		InstTxtParser.parse(args[0]);
		DataTxtParser.parse(args[1]);
		RegTxtParser.parse(args[2]);
		ConfigTxtParser.parse(args[3]);
		Display.instance.setResultsPath(args[4]);

		/**
		 * Initialize singleton instances of all the four stages
		 * 1. WriteBack
		 * 2. Execute
		 * 3. Decode
		 * 4. Fetch
		 */
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

				ProcessorParams.CC++;
			}
		} catch (Exception e) {
			System.out.println("ERROR: CLOCK=" + ProcessorParams.CC);
			e.printStackTrace();
		} finally {
		}
		Thread.sleep(1000L);
		System.out.println("Results");
		Display.instance.printResults();
		Display.instance.writeResults();

	}
}
