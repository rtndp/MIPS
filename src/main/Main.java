package main;

import memory.DataMemoryFileParser;
import program.ProgramParser;
import registers.RegisterFileParser;
import stages.CPU;
import stages.DECODE;
import stages.EX;
import stages.FETCH;
import stages.WRITEBACK;
import config.ConfigParser;


public class Main {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++)
			System.out.print(args[i] + " ");
		System.out.println();

		ProgramParser.parse(args[0]);

		DataMemoryFileParser.parseMemoryFile(args[1]);

		RegisterFileParser.parseRegister(args[2]);

		ConfigParser.parseConfigFile(args[3]);
		/*ConfigManager.instance.dumpConfiguration();*/
		

		CPU.CLOCK = 0;
		CPU.PROGRAM_COUNTER = 0;

		FETCH fetch = FETCH.getInstance();
		DECODE decode = DECODE.getInstance();
		EX ex = EX.getInstance();
		WRITEBACK writeBack = WRITEBACK.getInstance();
		
		fetch.execute();
		decode.execute();
		ex.execute();
		writeBack.execute();
		
		/*DECODE decode = DECODE.getInstance();
		EX ex = EX.getInstance();
		WRITEBACK writeBack = WRITEBACK.getInstance();*/

		while (CPU.CLOCK < 100) {

			CPU.CLOCK++;

			/*// Writeback
			writeBack.execute();
			// Execute
			ex.execute();
			// Decode
			decode.execute();
			// Fetch
			fetch.execute();*/

		}

		//ResultsManager.instance.testPrintWithDummyData();

	}
}
