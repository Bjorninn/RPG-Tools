package eu.elieser.exalted.scene.genesys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eu.elieser.exalted.R;
import eu.elieser.exalted.data.BundleKeys;
import eu.elieser.exalted.data.genesys.GenesysDataStore;
import eu.elieser.exalted.data.genesys.Talent;
import eu.elieser.exalted.genesys.talents.TalentHelper;
import eu.elieser.exalted.logic.genesys.GenesysTalentLogic;
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
    }

    private void LoadTalent(Talent talent)
    {
        name.setText(talent.getName());
        tier.setText(talent.getTier().toString());
        activation.setText(TalentHelper.createActivationSpannable(talent.getActivation()));

        if (talent.getRanked())
        {
            ranked.setText("Yes");
        }
        else
        {
            ranked.setText("No");
        }

        if (talent.getRequirement() != 0)
        {
            Talent prereqTalent = GenesysDataStore.getInstance().getTalent(talent.getRequirement());
            prerequisite.setText(TalentHelper.createPrerequisiteSpannable(prereqTalent.getName()));
        }

        description.setText(talent.getDescription());
        keywords.setText(TalentHelper.createKeywordsSpannable(talent.getKeywords()));
        //description.setText(TalentHelper.createDescriptionSpannable(getActivity().getAssets(), talent.getDescription()));
        description.setText(talent.getDescription());
        source.setText(TalentHelper.createSourceSpannable(talent.getSource()));
    }

    @Override
    public boolean onBackPressed()
    {
        return false;
    }

    @Override
    public void onClick(View view)
    {

    }
}