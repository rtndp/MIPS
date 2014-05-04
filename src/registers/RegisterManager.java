package registers;

import java.util.Map;
import java.util.TreeMap;

public class RegisterManager
{

    public static final RegisterManager instance = new RegisterManager();

    private RegisterManager()
    {
        initializeRegisters();
    }

    // keep track of all 32 Integer & 32 Floating registers if they're idle or
    // they're busy

    Map<String, RegisterState>  registerStateMap = new TreeMap<String, RegisterState>();
    Map<String, Register<Long>> registerMap      = new TreeMap<String, Register<Long>>();

    public void initializeRegisters()
    {
        // create 32 Int & 32 Float registers and initialize to 0
        // create registerMap

        for (int i = 0; i < 32; i++)
        {
            String intRKey = "R" + i;
            String FPRKey = "F" + i;

            Register<Long> intR = new Register<Long>(intRKey);
            intR.value = 0L;
            Register<Long> fpR = new Register<Long>(FPRKey);
            fpR.value = 0L;

            registerMap.put(intRKey, intR);
            registerMap.put(FPRKey, fpR);

            registerStateMap.put(intRKey, RegisterState.IDLE);
            registerStateMap.put(FPRKey, RegisterState.IDLE);
        }
    }

    public void setRegisterValue(String label, long val) throws Exception
    {
        label = ValidateRegisterName.getValidRegisterName(label);
        Register<Long> reg = registerMap.get(label);
        reg.value = (long) val;
    }

    public long getRegisterValue(String label) throws Exception
    {
        label = ValidateRegisterName.getValidRegisterName(label);
        Register<Long> reg = registerMap.get(label);
        return reg.value;
    }

    public boolean isRegisterFree(String label) throws Exception
    {
        label = ValidateRegisterName.getValidRegisterName(label);
        RegisterState reg = registerStateMap.get(label);
        return reg.equals(RegisterState.IDLE);
    }

    public void setRegisterFree(String label) throws Exception
    {
        label = ValidateRegisterName.getValidRegisterName(label);
        registerStateMap.put(label, RegisterState.IDLE);
    }

    public void setRegisterBusy(String label) throws Exception
    {
        label = ValidateRegisterName.getValidRegisterName(label);
        registerStateMap.put(label, RegisterState.BUSY);
    }

    public void dumpAllRegisters()
    {
        String leftAlignFormat = "| %-5s | %-5s | %-10d |%n";
        for (String label : registerMap.keySet())
        {
            Register<Long> reg = registerMap.get(label);
            System.out.format(leftAlignFormat, label,
                    registerStateMap.get(label), reg.value);
        }
    }

}
