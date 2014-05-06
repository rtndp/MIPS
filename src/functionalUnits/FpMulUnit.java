package functionalUnits;

import managers.ConfigManager;
import stages.StageType;

public class FpMulUnit extends FPFunctionalUnit
{

    private static volatile FpMulUnit instance;

    public static FpMulUnit getInstance()
    {
        if (null == instance)
            synchronized (FpMulUnit.class)
            {
                if (null == instance)
                    instance = new FpMulUnit();
            }

        return instance;
    }

    private FpMulUnit()
    {
        super();
        isPipelined = ConfigManager.instance.FPMultPipelined;
        clockCyclesRequired = ConfigManager.instance.FPMultLatency;
        pipelineSize = isPipelined ? ConfigManager.instance.FPMultLatency : 1;
        stageId = StageType.EXSTAGE;
        createPipelineQueue(pipelineSize);
    }
}
