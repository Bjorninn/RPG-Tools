package eu.elieser.exalted.data.dndspell;

/**
 * Created by bjorn on 15/05/16.
 */
public class CastingTime
{
    public CastingTime()
    {

    }

    public CastingTime(Integer value, String type)
    {
        this.value = value;
        this.type = type;
    }

    private Integer value;
    private String type;

    public String getCastingTime()
    {
        return value + " " + type;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
