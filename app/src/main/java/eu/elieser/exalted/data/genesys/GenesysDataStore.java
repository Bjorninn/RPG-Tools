package eu.elieser.exalted.data.genesys;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjornjonsson on 11/03/2018.
 */

public class GenesysDataStore
{
    private static GenesysDataStore instance;

    private Talents talents;
    private Map<String, Talent> talentMap;
    private Map<Integer, Talent> talentIdMap;

    public GenesysDataStore()
    {
        instance = this;
        talentMap = new HashMap<>();
        talentIdMap = new HashMap<>();
    }


    public static GenesysDataStore getInstance()
    {
        return instance;
    }

    public Talent getTalent(String talentName)
    {
        return talentMap.get(talentName);
    }

    public Talent getTalent(int talentId)
    {
        return talentIdMap.get(talentId);
    }

    public void setTalents(Talents talents)
    {
        this.talents = talents;

        for (Talent talent :
                talents.getTalents())
        {
            talentMap.put(talent.getName(), talent);
            talentIdMap.put(talent.getId(), talent);
        }
    }

    public Talents getTalents()
    {
        return talents;
    }
}
