package stages;

public class ProcessorParams
{
    public static int CLOCK           = 0;

    public static int PROGRAM_COUNTER = 0;

    public static RUN RUN_TYPE        = RUN.MEMORY; // DEFAULT

    public enum RUN
    {
        PIPELINE, MEMORY
    };

}
