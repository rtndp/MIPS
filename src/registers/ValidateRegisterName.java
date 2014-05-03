package registers;

public class ValidateRegisterName
{

    public static String getValidRegisterName(String label) throws Exception
    {

        if (null == label || label.trim().length() == 0)
            throw new Exception("Invalid register name " + label);

        label = label.trim();
        label = label.toUpperCase();
        if (!(label.charAt(0) == 'R' || label.charAt(0) == 'F'))
            throw new Exception("Invalid register name " + label);
        int regNum = Integer.valueOf(label.substring(1));
        if (regNum < 0 || regNum > 31)
            throw new Exception("Invalid register name " + label);

        return label;
    }

    public static String getValidIntegerRegisterName(String label)
            throws Exception
    {
        label = getValidRegisterName(label);

        if (label.charAt(0) != 'R')
            throw new Exception("Invalid register name " + label);
        return label;
    }

    public static String getValidFPRegisterName(String label) throws Exception
    {
        label = getValidRegisterName(label);
        if (label.charAt(0) != 'F')
            throw new Exception("Invalid register name " + label);
        return label;
    }

    public static RegisterType getRegisterType(String label) throws Exception
    {
        label = getValidRegisterName(label);

        return (label.charAt(0) == 'R') ? RegisterType.INT : RegisterType.FP;
    }
}
