package functionalUnits;

import managers.ConfigManager;
import enums.StageType;

public class FpDivUnit extends FPFunctionalUnit
{

    private static volatile FpDivUnit instance;

    public static FpDivUnit getInstance()
    {
        if (null == instance)
            synchronized (FpDivUnit.class)
            {
                if (null == instance)
                    instance = new FpDivUnit();
            }

        return instance;
    }

    private FpDivUnit()
    {
        super();
        isPipelined = ConfigManager.instance.FPDividerPipelined;
        clockCyclesRequired = ConfigManager.instance.FPDivideLatency;
        pipelineSize = isPipelined ? ConfigManager.instance.FPDivideLatency : 1;
        stageId = StageType.EXSTAGE;
        createPipelineQueue(pipelineSize);
    }
}
