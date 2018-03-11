package eu.elieser.exalted.logic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.elieser.exalted.MainActivity;
import eu.elieser.exalted.adapters.CharmAdapter;
import eu.elieser.exalted.data.BundleKeys;
import eu.elieser.exalted.data.Charm;
import eu.elieser.exalted.data.Charms;
import eu.elieser.exalted.data.DataStore;
import eu.elieser.exalted.navigation.Navigator;
import eu.elieser.exalted.scene.CharmDetailScene;
import eu.elieser.exalted.scene.CharmListScene;

/**
 * Created by bjorn on 21/04/16.
 */
public class CharmListLogic extends Logic<CharmListScene> implements CharmAdapter.CharmAdapterListener
{
    CharmAdapter.CharmDataComparator comparator = new CharmAdapter.CharmDataComparator();
    List<CharmAdapter.CharmData> charmData = new ArrayList<>();

    public CharmListLogic(CharmListScene scene, Context context)
    {
        super(scene, context);
    }

    public void loadCharmData()
    {
        DataStore dataStore = ((MainActivity) context).getDataStore();
        Charms charms = dataStore.getCharms();

        for (Charm charm :
                charms.getCharms())
        {
            charmData.add(CharmAdapter.CharmData.fromCharm(charm));
        }
    }

    public List<CharmAdapter.CharmData> getFilteredCharms(String abilityName, Integer score, Integer essence)
    {
        DataStore dataStore = ((MainActivity) context).getDataStore();

        List<CharmAdapter.CharmData> sortedData = new ArrayList<>();

        if (score != 5)
        {
            for (CharmAdapter.CharmData data : charmData)
            {
                Charm charm = dataStore.getCharm(data.name);

                if (charm.getMinAbility().getValue() <= score)
                {
                    sortedData.add(data);
                }
            }
        }
        else
        {
            sortedData.addAll(charmData);
        }

        if (essence != 5)
        {
            List<CharmAdapter.CharmData> temp = new ArrayList<>();

            for (CharmAdapter.CharmData data : sortedData)
            {
                Charm charm = dataStore.getCharm(data.name);

                if (charm.getMinEssence().getValue() <= essence)
                {
                    temp.add(data);
                }
            }

            sortedData.clear();
            sortedData.addAll(temp);
        }

        if (!abilityName.equals("All"))
        {
            List<CharmAdapter.CharmData> temp = new ArrayList<>();

            for (CharmAdapter.CharmData data : sortedData)
            {
                if (data.abilitySort.equals(abilityName))
                {
                    temp.add(data);
                }
            }

            sortedData.clear();
            sortedData.addAll(temp);
        }

        if (sortedData.isEmpty())
        {
            sortedData.addAll(charmData);
        }

        Collections.sort(sortedData, comparator);

        return sortedData;
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

        Navigator.getNavigator().navigationEvent(CharmDetailScene.class, bundle);
    }

    public List<CharmAdapter.CharmData> getData()
    {
        return charmData;
    }
}
