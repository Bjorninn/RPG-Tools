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
import eu.elieser.exalted.adapters.DividerItemDecoration;
import eu.elieser.exalted.adapters.MartialArtsCharmAdapter;
import eu.elieser.exalted.logic.MartialArtsListLogic;

/**
 * Created by bjorn on 24/04/16.
 */
public class MartialArtsListScene extends Scene<MartialArtsListLogic>
{

    private static final List<String> maStyles = Arrays.asList("All", "Black Claw Style", "Crane Style", "Dreaming Pearl Courtesan Style", "Ebon Shadow Style", "Righteous Devil Style",
            "Silver-Voiced Nightingale Style", "Single Point Shining Into the Void Style", "Snake Style", "Steel Devil Style", "Tiger Style", "White Reaper Style");

    private final List<Integer> abilityScores = Arrays.asList(1, 2, 3, 4, 5);

    private MartialArtsCharmAdapter adapter;

    private Spinner styleSpinner;
    private Spinner essenceScoreSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.scene_martial_arts_charm_list, container, false);
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

        logic = new MartialArtsListLogic(this, getActivity());

        // specify an adapter (see also next example)
        adapter = new MartialArtsCharmAdapter(getActivity(), logic);
        recyclerView.setAdapter(adapter);

        logic.loadCharmData();
        adapter.setData(logic.getData());

        setupStyleSpinner(view);
        setupEssenceSpinner(view);
    }

    private void setupStyleSpinner(View view)
    {
        styleSpinner = (Spinner) view.findViewById(R.id.style);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, maStyles);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        styleSpinner.setAdapter(adapter);
        styleSpinner.setSelection(0);

        styleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
        String style = styleSpinner.getSelectedItem().toString();
        Integer essence = (Integer) essenceScoreSpinner.getSelectedItem();

        if (style == null)
        {
            style = "All";
        }

        if (essence == null)
        {
            essence = 5;
        }

        List<MartialArtsCharmAdapter.MartialArtsCharmData> filteredCharms = logic.getFilteredCharms(style, essence);

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
