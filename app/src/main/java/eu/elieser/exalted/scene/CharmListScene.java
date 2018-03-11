package eu.elieser.exalted.scene;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import eu.elieser.exalted.R;
import eu.elieser.exalted.adapters.CharmAdapter;
import eu.elieser.exalted.adapters.DividerItemDecoration;
import eu.elieser.exalted.logic.CharmListLogic;

/**
 * Created by bjorn on 21/04/16.
 */
public class CharmListScene extends Scene<CharmListLogic>
{
    private final List<String> abilities = Arrays.asList("All", "Archery", "Athletics", "Awareness", "Brawl", "Bureaucracy",
            "Craft", "Dodge", "Integrity", "Investigation", "Larceny", "Linguistics", "Lore", "Martial Arts", "Medicine",
            "Melee", "Occult", "Performance", "Presence", "Resistance", "Ride", "Sail", "Socialize", "Stealth", "Survival", "Thrown", "War");

    private final List<Integer> abilityScores = Arrays.asList(1, 2, 3, 4, 5);

    private CharmAdapter adapter;

    private Spinner abilitySpinner;
    private Spinner abilityScoreSpinner;
    private Spinner essenceScoreSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.scene_charm_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.charm_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        logic = new CharmListLogic(this, getActivity());

        // specify an adapter (see also next example)
        adapter = new CharmAdapter(getActivity(), logic);
        recyclerView.setAdapter(adapter);

        logic.loadCharmData();
        adapter.setData(logic.getData());

        setupAbilitySpinner(view);
        setupAbilityScoreSpinner(view);
        setupEssenceSpinner(view);
    }

    private void setupAbilitySpinner(View view)
    {
        abilitySpinner = (Spinner) view.findViewById(R.id.ability);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, abilities);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        abilitySpinner.setAdapter(adapter);
        abilitySpinner.setSelection(0);

        abilitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
            {
                onSpinnerItemChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });
    }

    private void setupAbilityScoreSpinner(View view)
    {
        abilityScoreSpinner = (Spinner) view.findViewById(R.id.ability_score);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, abilityScores);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        abilityScoreSpinner.setAdapter(adapter);
        abilityScoreSpinner.setSelection(4);

        abilityScoreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
            {
                onSpinnerItemChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });
    }

    private void setupEssenceSpinner(View view)
    {
        essenceScoreSpinner = (Spinner) view.findViewById(R.id.essence_score);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, abilityScores);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        essenceScoreSpinner.setAdapter(adapter);
        essenceScoreSpinner.setSelection(4);

        essenceScoreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)
            {
                onSpinnerItemChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        });
    }

    private void onSpinnerItemChanged()
    {
        String ability = abilitySpinner.getSelectedItem().toString();
        Integer score = (Integer) abilityScoreSpinner.getSelectedItem();
        Integer essence = (Integer) essenceScoreSpinner.getSelectedItem();

        if (ability == null)
        {
            ability = "All";
        }

        if (score == null)
        {
            score = 5;
        }

        if (essence == null)
        {
            essence = 5;
        }

        List<CharmAdapter.CharmData> filteredCharms = logic.getFilteredCharms(ability, score, essence);


        adapter.setData(filteredCharms);
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
