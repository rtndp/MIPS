package main;

import memory.DataMemoryFileParser;
import memory.DataMemoryManager;
import program.ProgramManager;
import program.ProgramParser;
import registers.RegisterFileParser;
import registers.RegisterManager;
import results.ResultsManager;
import stages.CPU;
import stages.CPU.RUN;
import stages.DecodeStage;
import stages.ExStage;
import stages.FetchStage;
import stages.WriteBackStage;
import config.ConfigManager;
import config.ConfigParser;

public class Main
{
    /**
     * 
     * @param args
     *            inst.txt data.txt reg.txt config.txt result.txt
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {

        ProgramParser.parse(args[0]);
        ProgramManager.instance.dumpProgram();

        DataMemoryFileParser.parseMemoryFile(args[1]);
        DataMemoryManager.instance.dumpAllMemory();

        RegisterFileParser.parseRegister(args[2]);
        RegisterManager.instance.dumpAllRegisters();

        ConfigParser.parseConfigFile(args[3]);
        ConfigManager.instance.dumpConfiguration();

        ResultsManager.instance.setResultsPath(args[4]);

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

        try
        {

            // I run these many clock cycles after HLT to flush pipeline
            int extraCLKCount = 5000;
            while (extraCLKCount != 0)
            {

                wbStage.execute();
                exStage.execute();

                // Well this is just stupid way of doing this
                if (!ResultsManager.instance.isHALT())
                {
                    idStage.execute();

                    if (!ResultsManager.instance.isHALT())
                    {
                        ifStage.execute();
                    }
                }
                else
                    extraCLKCount--;

                CPU.CLOCK++;
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR: CLOCK=" + CPU.CLOCK);
            e.printStackTrace();
        }
        finally
        {
        }
        Thread.sleep(1000L);
        System.out.println("Results");
        ResultsManager.instance.printResults();
        ResultsManager.instance.writeResults();

        RegisterManager.instance.dumpAllRegisters();

    }
}
