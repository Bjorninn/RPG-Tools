
package eu.elieser.exalted.data.genesys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Talents
{

    @SerializedName("talents")
    @Expose
    private List<Talent> talents = null;

    public List<Talent> getTalents()
    {
        return talents;
    }

    public void setTalents(List<Talent> talents)
    {
        this.talents = talents;
    }
}
