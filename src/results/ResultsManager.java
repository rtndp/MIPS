package results;

import iCache.ICacheManager;
import instructions.Instruction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

import program.ProgramManager;
import stages.StageType;
import dCache.DCacheManager;

public class ResultsManager
{

    public static final ResultsManager          instance       = new ResultsManager();

    private final TreeMap<Integer, Instruction> instructionMap = new TreeMap<Integer, Instruction>();

    private BufferedWriter                      resultsWriter  = null;

    private boolean                             HALT           = false;

    private ResultsManager()
    {
    }

    // call this only once in ur program
    public void setResultsPath(String path) throws IOException
    {
        if (resultsWriter != null)
            return;
        resultsWriter = new BufferedWriter(new FileWriter(new File(path)));
    }

    public void printResults()
    {

        System.out.println(String.format(
                Utils.Constants.instructionOutputFormatString, "Instruction",
                "FT", "ID", "EX", "WB", "RAW", "WAR", "WAW", "Struct"));
        for (int key : instructionMap.keySet())
        {
            Instruction inst = instructionMap.get(key);
            // System.out.format("%-3s ", key);
            // System.out.println(inst.debugString());
            System.out.println(inst.getOutputString());
        }
        System.out.println(ICacheManager.getInstance().getICacheStatistics());
        System.out.println(DCacheManager.instance.getDCacheStatistics());
    }

    public void writeResults()
    {
        try
        {
            resultsWriter.write(String.format(
                    Utils.Constants.instructionOutputFormatString,
                    "Instruction", "FT", "ID", "EX", "WB", "RAW", "WAR", "WAW",
                    "Struct"));
            resultsWriter.newLine();
            for (int key : instructionMap.keySet())
            {
                Instruction inst = instructionMap.get(key);
                resultsWriter.write(inst.getOutputString());
                resultsWriter.newLine();
            }
            resultsWriter.write(ICacheManager.getInstance().getICacheStatistics());
            resultsWriter.write(DCacheManager.instance.getDCacheStatistics());
            resultsWriter.newLine();
            resultsWriter.flush();
            resultsWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addInstruction(Instruction instruction)
    {
        int key = instruction.entryCycle[StageType.IFSTAGE.getId()];
        instructionMap.put(key, instruction);

    }

    public void testPrintWithDummyData() throws Exception
    {
        int count = 0;
        for (Integer address : ProgramManager.instance.InstructionList.keySet())
        {
            Instruction inst = ProgramManager.instance
                    .getInstructionAtAddress(address);
            inst.entryCycle[0] = count++;
            inst.exitCycle[0] = count;
            inst.STRUCT = (count % 2 == 0) ? true : false;
            addInstruction(inst);
        }

        printResults();

    }

    public boolean isHALT()
    {
        return HALT;
    }

    public void setHALT(boolean halt)
    {
        this.HALT = halt;
    }
}
