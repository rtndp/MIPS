package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ConfigParser
{

    public static void parseConfigFile(String fileName) throws Exception
    {
        BufferedReader bfread = null;
        try
        {

            bfread = new BufferedReader(new FileReader(new File(fileName)));

            String line = null;
            int count = 0;

            while ((line = bfread.readLine()) != null)
            {
                line = line.trim();
                if (line.length() == 0)
                    continue;
                count++;

                try
                {
                    parseConfigurationLine(line);
                }
                catch (Exception e)
                {
                    throw new Exception("Error in Config File line: " + line);
                }
            }
            System.out.println("Total Number of Config Elements = " + count);
        }
        finally
        {
            if (bfread != null)
                bfread.close();
        }

    }

    private static void parseConfigurationLine(String line)
    {
        String s[], s1[];
        line = line.trim();
        line = line.toLowerCase();

        s = line.split(":");

        String configItem = s[0].trim();

        switch (configItem)
        {
            case "fp adder":
                s1 = s[1].split(",");
                ConfigManager.instance.FPAdderLatency = Integer.parseInt(s1[0]
                        .trim());
                ConfigManager.instance.FPAdderPipelined = s1[1].trim()
                        .toLowerCase().equals("yes");
                break;

            case "fp multiplier":
                s1 = s[1].split(",");
                ConfigManager.instance.FPMultLatency = Integer.parseInt(s1[0]
                        .trim());
                ConfigManager.instance.FPMultPipelined = s1[1].trim()
                        .toLowerCase().equals("yes");
                break;

            case "fp divider":
                s1 = s[1].split(",");
                ConfigManager.instance.FPDivideLatency = Integer.parseInt(s1[0]
                        .trim());
                ConfigManager.instance.FPDividerPipelined = s1[1].trim()
                        .toLowerCase().equals("yes");
                break;

            case "main memory":
                ConfigManager.instance.MemoryLatency = Integer.parseInt(s[1]
                        .trim());
                break;

            case "i-cache":
                ConfigManager.instance.ICacheLatency = Integer.parseInt(s[1]
                        .trim());
                break;

            case "d-cache":
                ConfigManager.instance.DCacheLatency = Integer.parseInt(s[1]
                        .trim());
                break;
        }

    }
}