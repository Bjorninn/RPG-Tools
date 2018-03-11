package eu.elieser.exalted.scene;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.elieser.exalted.R;
import eu.elieser.exalted.adapters.DividerItemDecoration;
import eu.elieser.exalted.adapters.Spell5eAdapter;
import eu.elieser.exalted.logic.SpellListLogic;


/**
 * Created by bjorn on 15/05/16.
 */
public class SpellListScene extends Scene<SpellListLogic> implements Spell5eAdapter.CharmAdapterListener
{
    private Spell5eAdapter adapter;

    @Override
    public boolean onBackPressed()
    {
        return false;
    }

    @Override
    public void onClick(View view)
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.scene_5e_spell_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.spell_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        logic = new SpellListLogic(this, getActivity());

        // specify an adapter (see also next example)
        adapter = new Spell5eAdapter(getActivity(), this);

        recyclerView.setAdapter(adapter);

        logic.loadSpellData();
        adapter.setData(logic.getData());

        //setupAbilitySpinner(view);
        //setupAbilityScoreSpinner(view);
        //setupEssenceSpinner(view);
    }

    @Override
    public void onItemClicked(String charmName)
    {

    }
}
