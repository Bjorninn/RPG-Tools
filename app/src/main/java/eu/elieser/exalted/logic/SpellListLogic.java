package eu.elieser.exalted.logic;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import eu.elieser.exalted.MainActivity;
import eu.elieser.exalted.adapters.Spell5eAdapter;
import eu.elieser.exalted.data.DataStore;
import eu.elieser.exalted.data.Spell;
import eu.elieser.exalted.scene.SpellListScene;

/**
 * Created by bjorn on 15/05/16.
 */
public class SpellListLogic extends Logic<SpellListScene>
{
    List<Spell5eAdapter.SpellData> spellData = new ArrayList<>();

    public enum Filter
    {
        CLASS,
        CONCENTRATION,
        LEVEL,
        LEVEL_ASCENDING,
        LEVEL_DESCENDING,
        RITUAL,
    }

    public SpellListLogic(SpellListScene scene, Context context)
    {
        super(scene, context);
    }

    @Override
    public void onPermissionResults(boolean wasAllowed, int requestCode)
    {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }

    public void loadSpellData()
    {
        DataStore dataStore = ((MainActivity) context).getDataStore();

        List<Spell> spellList = dataStore.getSpells().getSpells();

        for (Spell spell :
                spellList)
        {
            //spellData.add(Spell5eAdapter.SpellData.fromSpell(spell));
        }
    }

    public List<Spell5eAdapter.SpellData> getData()
    {
        return spellData;
    }

    public void getDataFiltered(List<Filter> filters)
    {
        List<Spell> tmpList = new ArrayList<>();
        List<Spell> spellList = new ArrayList<>();

        for (Filter filter :
                filters)
        {
            switch (filter)
            {

                case CLASS:
                    break;
                case CONCENTRATION:
                    break;
                case LEVEL:
                    break;
                case LEVEL_ASCENDING:
                    break;
                case LEVEL_DESCENDING:
                    break;
                case RITUAL:
                    break;
            }
        }
    }
}
