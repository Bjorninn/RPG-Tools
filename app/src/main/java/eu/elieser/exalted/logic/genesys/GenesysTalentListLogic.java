package eu.elieser.exalted.logic.genesys;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import eu.elieser.exalted.adapters.GenesysTalentAdapter;
import eu.elieser.exalted.data.genesys.GenesysDataStore;
import eu.elieser.exalted.data.genesys.Talent;
import eu.elieser.exalted.logic.Logic;
import eu.elieser.exalted.scene.genesys.GenesysTalentListScene;

/**
 * Created by bjornjonsson on 11/03/2018.
 */

public class GenesysTalentListLogic extends Logic<GenesysTalentListScene> implements GenesysTalentAdapter.GenesysTalentAdapterListener
{
    private List<Talent> talents;

    public GenesysTalentListLogic(GenesysTalentListScene scene, Context context)
    {
        super(scene, context);
    }

    public void loadTalentData()
    {
        talents = GenesysDataStore.getInstance().getTalents().getTalents();
    }

    @Override
    public void onPermissionResults(boolean wasAllowed, int requestCode)
    {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }

    @Override
    public void onItemClicked(String charmName)
    {

    }

    public List<Talent> getData()
    {
        return talents;
    }
}
