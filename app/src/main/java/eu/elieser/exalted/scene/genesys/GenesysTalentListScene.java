package eu.elieser.exalted.scene.genesys;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.elieser.exalted.R;
import eu.elieser.exalted.adapters.DividerItemDecoration;
import eu.elieser.exalted.adapters.GenesysTalentAdapter;
import eu.elieser.exalted.logic.genesys.GenesysTalentListLogic;
import eu.elieser.exalted.scene.Scene;

/**
 * Created by bjornjonsson on 10/03/2018.
 */

public class GenesysTalentListScene extends Scene<GenesysTalentListLogic>
{
    private GenesysTalentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.scene_genesys_talent_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.talent_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        logic = new GenesysTalentListLogic(this, getActivity());

        // specify an adapter (see also next example)
        adapter = new GenesysTalentAdapter(getActivity(), logic);
        recyclerView.setAdapter(adapter);

        logic.loadTalentData();
        adapter.setData(logic.getData());
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
