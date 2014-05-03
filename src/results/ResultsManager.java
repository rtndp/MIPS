package results;

import instructions.Instruction;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import program.ProgramManager;

public class ResultsManager
{

    public static final ResultsManager          instance       = new ResultsManager();

    private final TreeMap<Integer, Instruction> instructionMap = new TreeMap<Integer, Instruction>();

    private BufferedWriter                      resultsWriter  = null;

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
        // http://stackoverflow.com/questions/15215326/how-can-i-create-ascii-table-in-java-in-a-console
        // use String.format() and then print at the end
        // ordered by key which is clock entry of entry into IF
        //
        String formatStr = "| %-15s | %-4s | %-4s | %-4s | %-4s | %-3s | %-3s | %-3s | %-4s |%n";
        for (int key : instructionMap.keySet())
        {
            Instruction inst = instructionMap.get(key);

            System.out.format(formatStr, inst.printableInstruction,
                    inst.exitCycle[0], inst.exitCycle[1], inst.exitCycle[2],
                    inst.exitCycle[3], inst.RAW ? 'Y' : 'N', inst.WAR ? 'Y'
                            : 'N', inst.WAW ? 'Y' : 'N', inst.STRUCT ? 'Y'
                            : 'N');

        }
    }

    public void writeResults()
    {
        // use the printResults String, write it to writer and then close

        try
        {
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
        int key = instruction.entryCycle[0];
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
}
