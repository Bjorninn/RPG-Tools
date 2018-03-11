package eu.elieser.exalted.data.dndspell;

import java.util.List;

/**
 * Created by bjorn on 15/05/16.
 */
public class Spells
{
    List<Spell> spells;

    public List<Spell> getSpells()
    {
        return spells;
    }

    public void setSpells(List<Spell> spells)
    {
        this.spells = spells;
    }

    @Override
    public String toString()
    {
        return "Spells{" +
                "spells=" + spells +
                '}';
    }
}
