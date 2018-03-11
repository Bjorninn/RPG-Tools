package eu.elieser.exalted.scene;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import eu.elieser.exalted.R;
import eu.elieser.exalted.data.BundleKeys;
import eu.elieser.exalted.data.Charm;
import eu.elieser.exalted.data.DataStore;
import eu.elieser.exalted.data.MartialArtsCharm;
import eu.elieser.exalted.exalted.charms.CharmHelper;
import eu.elieser.exalted.exalted.charms.CharmTree;
import eu.elieser.exalted.logic.CharmDetailLogic;
import eu.elieser.exalted.navigation.Navigator;

/**
 * Created by bjorn on 23/04/16.
 */
public class CharmDetailScene extends Scene<CharmDetailLogic>
{
    private TextView costAndMins;
    private TextView type;
    private TextView duration;
    private TextView name;
    private TextView description;
    private TextView keywords;
    private TextView prerequisiteCharms;
    private TextView unlocksCharms;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.scene_charm, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        logic = new CharmDetailLogic(this, getActivity());

        name = (TextView) view.findViewById(R.id.name);
        costAndMins = (TextView) view.findViewById(R.id.cost_and_mins);
        type = (TextView) view.findViewById(R.id.type);

        keywords = (TextView) view.findViewById(R.id.keywords);
        duration = (TextView) view.findViewById(R.id.duration);
        prerequisiteCharms = (TextView) view.findViewById(R.id.prerequisite_charms);
        unlocksCharms = (TextView) view.findViewById(R.id.unlocks_charms);

        description = (TextView) view.findViewById(R.id.description);

        String charmName = getArguments().getString(BundleKeys.NAME);
        DataStore dataStore = DataStore.getDataStore();
        Charm charm = dataStore.getCharm(charmName);

        loadCharm(charm, view);

        Button backButton = (Button) view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Navigator.getNavigator().popFragmentBackStackToScene(CharmListScene.class.getName());
            }
        });

//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Navigator.getNavigator().popFragmentBackStackToScene(CharmListScene.class.getName());
//            }
//        });
    }

    private void loadCharm(Charm charm, View view)
    {
        int blue = getResources().getColor(R.color.headline_blue);

        name.setText(charm.getName());
        costAndMins.setText(CharmHelper.createCostAndMinsSpannable(charm.getCost(), charm.getMinAbility(), charm.getMinEssence()));
        type.setText(CharmHelper.createTypeSpannable(charm.getType()));
        duration.setText(CharmHelper.createDurationSpannable(charm.getDuration()));
        description.setText(charm.getDescription());

        keywords.setMovementMethod(LinkMovementMethod.getInstance());
        keywords.setText(CharmHelper.createKeywordsSpannable(charm.getKeywords(), logic, blue));

        CharmTree charmTree = CharmTree.getCharmTree();
        List<Charm> charmsThatUnlock = charmTree.getCharmsThatUnlock(charm.getName());
        List<String> names = CharmHelper.charmsToNames(charmsThatUnlock);

        unlocksCharms.setMovementMethod(LinkMovementMethod.getInstance());
        unlocksCharms.setText(CharmHelper.createUnlocksCharmsSpannable(names, blue));
        unlocksCharms.setHighlightColor(Color.TRANSPARENT);

        prerequisiteCharms.setMovementMethod(LinkMovementMethod.getInstance());
        prerequisiteCharms.setText(CharmHelper.createPrerequisiteCharmsSpannable(charm.getPrerequisiteCharms(), blue));
        prerequisiteCharms.setHighlightColor(Color.TRANSPARENT);

        if (charm instanceof MartialArtsCharm)
        {
            loadMartialArts((MartialArtsCharm) charm, view);
        }
    }

    private void loadMartialArts(MartialArtsCharm charm, View view)
    {
        TextView style = (TextView) view.findViewById(R.id.style);
        style.setText(charm.getMartialArtStyle());
        style.setVisibility(View.VISIBLE);

        loadMartialArtData(R.id.terrestrial, charm.getTerrestrial(), "Terrestrial: ", view);
        loadMartialArtData(R.id.mastery, charm.getMastery(), "Mastery: ", view);
        loadMartialArtData(R.id.special_activation, charm.getSpecialActivationRules(), "Special activation rules: ", view);
    }

    private void loadMartialArtData(@IdRes int lid, String data, String prefix, View view)
    {
        if (data != null && !data.isEmpty())
        {
            TextView textView = (TextView) view.findViewById(lid);
            textView.setVisibility(View.VISIBLE);
            textView.setText(CharmHelper.createFlexibleSpannable(data, prefix));
        }
    }

    @Override
    public boolean onBackPressed()
    {
        return Navigator.getNavigator().popFragmentBackStack();
    }

    @Override
    public void onClick(View view)
    {

    }
}
