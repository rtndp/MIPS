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
    Map<String, Register> registerMap      = new TreeMap<String, Register>();

    public void initializeRegisters()
    {
        // create 32 Int & 32 Float registers and initialize to 0
        // create registerMap

        for (int i = 0; i < 32; i++)
        {
            String intRKey = "R" + i;
            String FPRKey = "F" + i;

            Register intR = new Register(intRKey);
            intR.data = 0;
            Register fpR = new Register(FPRKey);
            fpR.data = 0;

            registerMap.put(intRKey, intR);
            registerMap.put(FPRKey, fpR);

            registerStateMap.put(intRKey, RegisterState.IDLE);
            registerStateMap.put(FPRKey, RegisterState.IDLE);
        }
    }

    public void setRegisterValue(String label, int val) throws Exception
    {
        label = ValidateRegisterName.getValidRegisterName(label);
        Register reg = registerMap.get(label);
        reg.data = val;
    }

    public int getRegisterValue(String label) throws Exception
    {
        label = ValidateRegisterName.getValidRegisterName(label);
        Register reg = registerMap.get(label);
        return reg.data;
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

    

}
