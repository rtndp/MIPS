package utility;

public class Utils
{

    public static boolean xraisedTo2(int x)
    {
        return (x > 0) && (x & (x - 1)) == 0;
    }

    /*public static void main(String[] args)
    {

    }*/

}
