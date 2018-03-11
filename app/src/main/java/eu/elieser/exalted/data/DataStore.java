package eu.elieser.exalted.data;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjorn on 21/04/16.
 */
public class DataStore
{
    private static DataStore instance;
    private Map<String, Charm> maCharmMap;
    private Map<String, Charm> charmMap;
    private Map<String, Keyword> keywordMap;
    private Charms charms;
    private MartialArtsCharms maCharms;
    private Keywords keywords;

    private Spells spells;
    private Map<String, Spell> spellMap;

    public DataStore()
    {
        instance = this;
        charmMap = new HashMap<>();
        maCharmMap = new HashMap<>();
        keywordMap = new HashMap<>();
        spellMap = new HashMap<>();
    }

    public static DataStore getDataStore()
    {
        return instance;
    }

    public void setSpells(Spells spells)
    {
        spellMap.clear();

        this.spells = spells;

        List<Spell> list = spells.getSpells();

        for (Spell spell
                : list)
        {
            spellMap.put(spell.getName(), spell);
        }
    }

    public void setCharms(Charms charms)
    {
        charmMap.clear();

        this.charms = charms;

        List<Charm> list = charms.getCharms();

        for (Charm charm :
                list)
        {
            charmMap.put(charm.getName(), charm);
        }
    }

    public void setMaCharms(MartialArtsCharms charms)
    {
        maCharmMap.clear();

        this.maCharms = charms;

        List<MartialArtsCharm> list = charms.getCharms();

        for (Charm charm :
                list)
        {
            maCharmMap.put(charm.getName(), charm);
        }
    }

    public Charm getCharm(String name)
    {
        if (charmMap.containsKey(name))
        {
            return charmMap.get(name);
        }

        return maCharmMap.get(name);
    }

    public Spells getSpells()
    {
        return spells;
    }

    public Charms getCharms()
    {
        return charms;
    }

    public MartialArtsCharms getMaCharms()
    {
        return maCharms;
    }

    public Charm getMaCharm(String name)
    {
        return maCharmMap.get(name);
    }

    public Keyword getKeyword(String keyword)
    {
        return keywordMap.get(keyword);
    }

    public Keywords getKeywords()
    {
        return keywords;
    }

    public void setKeywords(Keywords keywords)
    {
        this.keywords = keywords;

        keywordMap.clear();

        List<Keyword> list = keywords.getKeywords();

        for (Keyword keyword :
                list)
        {
            keywordMap.put(keyword.getKeyword(), keyword);
        }
    }

    public void loadCharmData(Context context)
    {
        Charms charms = JsonLoader.charmsFromJson(context);
        MartialArtsCharms martialArtsCharms = JsonLoader.maCharmsFromJson(context);
        Keywords keywords = JsonLoader.keywordsFromJson(context);
        setCharms(charms);
        setMaCharms(martialArtsCharms);
        setKeywords(keywords);
    }
}
