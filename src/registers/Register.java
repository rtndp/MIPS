package registers;

public class Register<T>
{
    String label;

    T      value;

    public Register(String label)
    {
        this.label = label;
    }

}
