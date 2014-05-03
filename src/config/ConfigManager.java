package config;

public class ConfigManager
{

    public static final ConfigManager instance = new ConfigManager();

    private ConfigManager()
    {

    }

    public int     FPAdderLatency     = 0;
    public int     FPMultLatency      = 0;
    public int     FPDivideLatency    = 0;

    public boolean FPAdderPipelined   = false;
    public boolean FPMultPipelined    = false;
    public boolean FPDividerPipelined = false;

    public int     MemoryLatency      = 0;
    public int     ICacheLatency      = 0;
    public int     DCacheLatency      = 0;

    public void dumpConfiguration()
    {
        String leftAlignFormat = "| %-20s | %-10s |%n";

        System.out.format(leftAlignFormat, "FPAdderLatency", FPAdderLatency);
        System.out
                .format(leftAlignFormat, "FPAdderPipelined", FPAdderPipelined);

        System.out.format(leftAlignFormat, "FPMultLatency", FPMultLatency);
        System.out.format(leftAlignFormat, "FPMultPipelined", FPMultPipelined);

        System.out.format(leftAlignFormat, "FPDividerPipelined",
                FPDivideLatency);
        System.out.format(leftAlignFormat, "FPDividerPipelined",
                FPDividerPipelined);

        System.out.format(leftAlignFormat, "MemoryLatency", MemoryLatency);
        System.out.format(leftAlignFormat, "ICacheLatency", ICacheLatency);
        System.out.format(leftAlignFormat, "DCacheLatency", DCacheLatency);
    }
}
