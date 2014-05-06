package utility;

public class Utility
{
	/**
	 * This function is used to compute a X^2 when required
	 * @param x
	 * @return
	 */
    public static boolean xraisedTo2(int x)
    {
        return (x > 0) && (x & (x - 1)) == 0;
    }

}
