package eu.elieser.exalted.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjorn on 17/11/2016.
 */

public class Spells
{
    private final List<Spell> spells;

    public Spells(List<Spell> spells)
    {
        this.spells = new ArrayList<>();
        this.spells.addAll(spells);
    }

    public Spells()
    {
        spells = new ArrayList<>();
    }

    public List<Spell> getSpells()
    {
        return spells;
    }

    public void setSpells(List<Spell> spells)
    {
        this.spells.clear();
        this.spells.addAll(spells);
    }
}