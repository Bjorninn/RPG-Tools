package eu.elieser.exalted.logic.genesys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Collections;
import java.util.List;

import eu.elieser.exalted.adapters.GenesysTalentAdapter;
import eu.elieser.exalted.data.BundleKeys;
import eu.elieser.exalted.data.genesys.GenesysDataStore;
import eu.elieser.exalted.data.genesys.Talent;
import eu.elieser.exalted.genesys.talents.TalentHelper;
import eu.elieser.exalted.logic.Logic;
import eu.elieser.exalted.navigation.Navigator;
import eu.elieser.exalted.scene.genesys.GenesysTalentListScene;
import eu.elieser.exalted.scene.genesys.GenesysTalentScene;

/**
 * Created by bjornjonsson on 11/03/2018.
 */

public class GenesysTalentListLogic extends Logic<GenesysTalentListScene> implements GenesysTalentAdapter.GenesysTalentAdapterListener
{
    private List<Talent> talents;
    private GenesysTalentAdapter.TalentDataComparator comparator = new GenesysTalentAdapter.TalentDataComparator();

    public GenesysTalentListLogic(GenesysTalentListScene scene, Context context)
    {
        super(scene, context);
        TalentHelper.initalize();
    }

    public void loadTalentData()
    {
        talents = GenesysDataStore.getInstance().getTalents().getTalents();

        Collections.sort(talents, comparator);
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
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeys.NAME, charmName);

        Navigator.getNavigator().navigationEvent(GenesysTalentScene.class, bundle);
    }

    public List<Talent> getData()
    {
        return talents;
    }
}
