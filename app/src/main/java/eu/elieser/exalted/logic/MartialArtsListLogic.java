package eu.elieser.exalted.logic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.elieser.exalted.MainActivity;
import eu.elieser.exalted.adapters.MartialArtsCharmAdapter;
import eu.elieser.exalted.data.BundleKeys;
import eu.elieser.exalted.data.Charm;
import eu.elieser.exalted.data.DataStore;
import eu.elieser.exalted.data.MartialArtsCharm;
import eu.elieser.exalted.data.MartialArtsCharms;
import eu.elieser.exalted.navigation.Navigator;
import eu.elieser.exalted.scene.CharmDetailScene;
import eu.elieser.exalted.scene.MartialArtsListScene;

/**
 * Created by bjorn on 24/04/16.
 */
public class MartialArtsListLogic extends Logic<MartialArtsListScene> implements MartialArtsCharmAdapter.CharmAdapterListener
{

    MartialArtsCharmAdapter.MartialArtsCharmDataComparator comparator = new MartialArtsCharmAdapter.MartialArtsCharmDataComparator();
    List<MartialArtsCharmAdapter.MartialArtsCharmData> charmData = new ArrayList<>();

    public MartialArtsListLogic(MartialArtsListScene scene, Context context)
    {
        super(scene, context);
    }

    public void loadCharmData()
    {
        DataStore dataStore = ((MainActivity) context).getDataStore();
        MartialArtsCharms charms = dataStore.getMaCharms();

        for (MartialArtsCharm charm :
                charms.getCharms())
        {
            charmData.add(MartialArtsCharmAdapter.MartialArtsCharmData.fromCharm(charm));
        }
    }

    public List<MartialArtsCharmAdapter.MartialArtsCharmData> getFilteredCharms(String style, Integer essence)
    {
        DataStore dataStore = ((MainActivity) context).getDataStore();

        List<MartialArtsCharmAdapter.MartialArtsCharmData> sortedData = new ArrayList<>();

        if (essence != 5)
        {
            List<MartialArtsCharmAdapter.MartialArtsCharmData> temp = new ArrayList<>();

            for (MartialArtsCharmAdapter.MartialArtsCharmData data : charmData)
            {
                Charm charm = dataStore.getMaCharm(data.name);

                if (charm.getMinEssence().getValue() <= essence)
                {
                    temp.add(data);
                }
            }

            sortedData.clear();
            sortedData.addAll(temp);
        }
        else
        {
            sortedData.addAll(charmData);
        }

        if (!style.equals("All"))
        {
            List<MartialArtsCharmAdapter.MartialArtsCharmData> temp = new ArrayList<>();

            for (MartialArtsCharmAdapter.MartialArtsCharmData data : sortedData)
            {
                if (data.martialArtStyle.equals(style))
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

    public List<MartialArtsCharmAdapter.MartialArtsCharmData> getData()
    {
        return charmData;
    }
}
