package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

import managers.DCacheManager;
import managers.ICacheManager;
import managers.ProgramManager;
import stages.StageType;
import validInstructions.DI;

public class Display {

	public static final Display instance = new Display();

	private final TreeMap<Integer, DI> instructionMap = new TreeMap<Integer, DI>();

	private BufferedWriter resultsWriter = null;

	private boolean HALT = false;

	public static final String instructionOutputFormatString = "| %-25s | %-4s | %-4s | %-4s | %-4s | %-3s | %-3s | %-3s | %-6s |";

	public static final String instructionDebugFormatString = "%-20s %-10s %-10s %-3s %-3s %-3s %-3s";

	private Display() {
	}

	// call this only once in ur program
	public void setResultsPath(String path) throws IOException {
		if (resultsWriter != null)
			return;
		resultsWriter = new BufferedWriter(new FileWriter(new File(path)));
	}

	public void printResults() {

		System.out.println(String.format(instructionOutputFormatString,
				"Instruction", "FT", "ID", "EX", "WB", "RAW", "WAR", "WAW",
				"Struct"));
		for (int key : instructionMap.keySet()) {
			DI inst = instructionMap.get(key);
			// System.out.format("%-3s ", key);
			// System.out.println(inst.debugString());
			System.out.println(inst.getOutputString());
		}
		System.out.println(ICacheManager.getInstance().getICacheStatistics());
		System.out.println(DCacheManager.instance.getDCacheStatistics());
	}

	public void writeResults() {
		try {
			resultsWriter.write(String.format(instructionOutputFormatString,
					"Instruction", "FT", "ID", "EX", "WB", "RAW", "WAR", "WAW",
					"Struct"));
			resultsWriter.newLine();
			for (int key : instructionMap.keySet()) {
				DI inst = instructionMap.get(key);
				resultsWriter.write(inst.getOutputString());
				resultsWriter.newLine();
			}
			resultsWriter.write(ICacheManager.getInstance()
					.getICacheStatistics());
			resultsWriter.write(DCacheManager.instance.getDCacheStatistics());
			resultsWriter.newLine();
			resultsWriter.flush();
			resultsWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addInstruction(DI instruction) {
		int key = instruction.entryCycle[StageType.IFSTAGE.getId()];
		instructionMap.put(key, instruction);

	}

	public void testPrintWithDummyData() throws Exception {
		int count = 0;
		for (Integer address : ProgramManager.instance.InstructionList.keySet()) {
			DI inst = ProgramManager.instance.getInstructionAtAddress(address);
			inst.entryCycle[0] = count++;
			inst.exitCycle[0] = count;
			inst.STRUCT = (count % 2 == 0) ? true : false;
			addInstruction(inst);
		}

		printResults();

	}

	public boolean isHALT() {
		return HALT;
	}

	public void setHALT(boolean halt) {
		this.HALT = halt;
	}
}
