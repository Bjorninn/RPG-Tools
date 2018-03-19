package eu.elieser.exalted.scene.genesys;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import eu.elieser.exalted.R;
import eu.elieser.exalted.data.BundleKeys;
import eu.elieser.exalted.data.genesys.GenesysDataStore;
import eu.elieser.exalted.data.genesys.Talent;
import eu.elieser.exalted.genesys.talents.TalentHelper;
import eu.elieser.exalted.logic.genesys.GenesysTalentLogic;
import eu.elieser.exalted.navigation.Navigator;
import eu.elieser.exalted.scene.Scene;

/**
 * Created by bjornjonsson on 10/03/2018.
 */

public class GenesysTalentScene extends Scene<GenesysTalentLogic>
{
    private TextView name;
    private TextView tier;
    private TextView activation;
    private TextView ranked;
    private TextView prerequisite;
    private TextView description;
    private TextView keywords;
    private TextView source;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.scene_genesys_talent, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        logic = new GenesysTalentLogic(this, getActivity());

        name = view.findViewById(R.id.talent_name);
        tier = view.findViewById(R.id.talent_tier);
        activation = view.findViewById(R.id.talent_activation);
        ranked = view.findViewById(R.id.talent_ranked);
        prerequisite = view.findViewById(R.id.talent_prerequisite);
        description = view.findViewById(R.id.talent_description);
        keywords = view.findViewById(R.id.talent_keywords);
        source = view.findViewById(R.id.talent_source);

        String talentName = getArguments().getString(BundleKeys.NAME);
        Talent talent = GenesysDataStore.getInstance().getTalent(talentName);

        LoadTalent(talent);

        Button backButton = view.findViewById(R.id.talent_back_button);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });

        ImageView prevTalentButton = view.findViewById(R.id.prev_talent_btn);
        prevTalentButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    private void LoadTalent(Talent talent)
    {
        name.setText(TalentHelper.createNameSpannable(talent.getName()));
        tier.setText(TalentHelper.createTierSpannable(talent.getTier().toString()));
        activation.setText(TalentHelper.createActivationSpannable(talent.getActivation()));

        if (talent.getRanked())
        {
            ranked.setText(TalentHelper.createRankedSpannable("Yes"));
        }
        else
        {
            ranked.setText(TalentHelper.createRankedSpannable("No"));

        }

        if (talent.getRequirement() != 0)
        {
            Talent prereqTalent = GenesysDataStore.getInstance().getTalent(talent.getRequirement());
            prerequisite.setVisibility(View.VISIBLE);
            prerequisite.setText(TalentHelper.createPrerequisiteSpannable(prereqTalent.getName()));
        }
        else
        {
            prerequisite.setVisibility(View.GONE);
        }

        description.setText(talent.getDescription());

        if (talent.hasKeywords())
        {
            keywords.setVisibility(View.VISIBLE);
            keywords.setText(TalentHelper.createKeywordsSpannable(talent.getKeywords()));
        }
        else
        {
            keywords.setVisibility(View.GONE);
        }

        description.setText(TalentHelper.createDescriptionSpannable(talent.getDescription()));
        //description.setText(TalentHelper.testFont(getActivity().getAssets(), talent.getDescription()));
        //description.setText(talent.getDescription());
        source.setText(TalentHelper.createSourceSpannable(talent.getSource()));
    }

    @Override
    public boolean onBackPressed()
    {
        return Navigator.getNavigator().popFragmentBackStack();
    }

    @Override
    public void onClick(View view)
    {
        Log.d("asd0", "asdasd");
    }
}
