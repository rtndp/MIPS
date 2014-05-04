package main;

import java.util.ArrayList;
import java.util.List;

import instructions.Instruction;
import memory.DataMemoryFileParser;
import program.ProgramManager;
import program.ProgramParser;
import registers.RegisterFileParser;
import registers.RegisterManager;
import results.ResultsManager;
import stages.CPU;
import stages.DecodeStage;
import stages.ExStage;
import stages.FetchStage;
import stages.WriteBackStage;
import config.ConfigParser;
import functionalUnits.FpAddUnit;
import functionalUnits.FpDivUnit;
import functionalUnits.FpMulUnit;
import functionalUnits.FunctionalUnit;
import functionalUnits.MemoryUnit;

public class Main {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++)
			System.out.print(args[i] + " ");
		System.out.println();

		ProgramParser.parse(args[0]);

		DataMemoryFileParser.parseMemoryFile(args[1]);

		RegisterFileParser.parseRegister(args[2]);

		ConfigParser.parseConfigFile(args[3]);
		/* ConfigManager.instance.dumpConfiguration(); */

		CPU.CLOCK = 0;
		CPU.PROGRAM_COUNTER = 0;

		/*
		 * FETCH fetch = FETCH.getInstance(); DECODE decode =
		 * DECODE.getInstance(); EX ex = EX.getInstance();
		 */
		WriteBackStage writeBack = WriteBackStage.getInstance();
		ExStage ex = ExStage.getInstance();

		/*
		 * fetch.execute(); decode.execute(); ex.execute(); writeBack.execute();
		 */

		/*
		 * DECODE decode = DECODE.getInstance(); EX ex = EX.getInstance();
		 * WRITEBACK writeBack = WRITEBACK.getInstance();
		 */

		while (CPU.CLOCK < 1) {

			CPU.CLOCK++;
			Instruction test = ProgramManager.instance
					.getInstructionAtAddress(0);
			test.entryCycle[0] = 1;
			test.entryCycle[1] = 2;
			test.entryCycle[2] = 3;
			test.entryCycle[3] = 5;

			test.exitCycle[0] = 5;
			test.exitCycle[1] = 6;
			test.exitCycle[2] = 7;
			test.exitCycle[3] = 8;

			test.RAW = true;
			test.getDestinationRegister().setDestination(1000);

			writeBack.acceptInstruction(test);
			writeBack.execute();

			System.out.println(RegisterManager.instance.getRegisterValue(test
					.getDestinationRegister().getDestinationLabel()));
			
			//ex.execute();
			/*
			 * // Writeback
			 * 
			 * // Execute ex.execute(); // Decode decode.execute(); // Fetch
			 * fetch.execute();
			 */
			
					
			

		}

		ResultsManager.instance.printResults();

	}
}
