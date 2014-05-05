package functionalUnits;

import stages.StageType;
import config.ConfigManager;

public class FpAddUnit extends FPFunctionalUnit
{

    private static volatile FpAddUnit instance;

    public static FpAddUnit getInstance()
    {
        if (null == instance)
            synchronized (FpAddUnit.class)
            {
                if (null == instance)
                    instance = new FpAddUnit();
            }

        return instance;
    }

    private FpAddUnit()
    {
        super();
        isPipelined = ConfigManager.instance.FPAdderPipelined;
        clockCyclesRequired = ConfigManager.instance.FPAdderLatency;
        pipelineSize = isPipelined ? ConfigManager.instance.FPAdderLatency : 1;
        stageId = StageType.EXSTAGE;
        createPipelineQueue(pipelineSize);
    }
}
