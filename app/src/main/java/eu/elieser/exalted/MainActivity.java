package eu.elieser.exalted;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import eu.elieser.exalted.data.DataStore;
import eu.elieser.exalted.data.genesys.GenesysDataStore;
import eu.elieser.exalted.exalted.charms.CharmTree;
import eu.elieser.exalted.navigation.Navigator;
import eu.elieser.exalted.scene.ExaltedHomeScene;
import eu.elieser.exalted.scene.Scene;
import eu.elieser.exalted.scene.genesys.GenesysTalentListScene;

public class MainActivity extends FragmentActivity
{
    public static int CharmFragmentId;

    private final DataStore dataStore = new DataStore();
    private final GenesysDataStore genesysDataStore = new GenesysDataStore();
    private final CharmTree charmTree = new CharmTree();
    private final Navigator navigator = new Navigator(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Scene scene = new ExaltedHomeScene();
        Scene scene = new GenesysTalentListScene();

        dataStore.loadCharmData(this);
        genesysDataStore.loadTalentData(this);

        charmTree.initialize(dataStore.getCharms());
        charmTree.initialize(dataStore.getMaCharms());

        CharmFragmentId = getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, scene, scene.getClass().getName())
                .commit();
    }

    public DataStore getDataStore()
    {
        return dataStore;
    }
}
