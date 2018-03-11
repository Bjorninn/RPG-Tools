package eu.elieser.exalted.data.dndspell;

import java.util.List;

/**
 * Created by bjorn on 15/05/16.
 */
public class Archtype
{
    public Archtype()
    {

    }

    public Archtype(String clazz, List<String> types)
    {
        this.clazz = clazz;
        this.types = types;
    }

    private String clazz;
    private List<String> types;

    public String getClazz()
    {
        return clazz;
    }

    public void setClazz(String clazz)
    {
        this.clazz = clazz;
    }

    public List<String> getTypes()
    {
        return types;
    }

    public void setTypes(List<String> types)
    {
        this.types = types;
    }

    @Override
    public String toString()
    {
        return "Archtype{" +
                "clazz='" + clazz + '\'' +
                ", types=" + types +
                '}';
    }
}
