package Utils;

public class Utils
{

    public static boolean isPowerOf2(int val)
    {
        return (val > 0) && (val & (val - 1)) == 0;
    }

    public static void main(String[] args)
    {

    }

}
