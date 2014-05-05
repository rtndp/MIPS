package stages;

public enum StageType
{

    IFSTAGE(0), IDSTAGE(1), EXSTAGE(2), WBSTAGE(3);

    private int id;

    private StageType(int val)
    {
        this.id = val;
    }

    public int getId()
    {
        return id;
    }

}
