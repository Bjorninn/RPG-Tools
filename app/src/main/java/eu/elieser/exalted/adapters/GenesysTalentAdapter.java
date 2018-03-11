package eu.elieser.exalted.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.elieser.exalted.R;
import eu.elieser.exalted.data.genesys.Talent;

/**
 * Created by Bjorn on 3/11/2018.
 */

public class GenesysTalentAdapter extends RecyclerView.Adapter<GenesysTalentAdapter.ChooseTalentViewHolder>
{
    private final Context context;
    private final GenesysTalentAdapterListener listener;
    private List<Talent> itemData = new ArrayList<>();

    public GenesysTalentAdapter(Context context, GenesysTalentAdapterListener listener)
    {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public GenesysTalentAdapter.ChooseTalentViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(viewType, parent, false);

        return new ChooseTalentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenesysTalentAdapter.ChooseTalentViewHolder holder, int position)
    {
        Talent talent = itemData.get(position);

        holder.name.setText(talent.getName());
        holder.tier.setText(talent.getTier());
        holder.ranked.setText(talent.getRanked().toString());
        holder.activation.setText(talent.getActivation());
        holder.description.setText(talent.getDescription());
    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    public void setData(List<Talent> data)
    {
        itemData.clear();
        itemData.addAll(data);

        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
            @Override
            public void run()
            {
                notifyDataSetChanged();
            }
        });
    }

    public class ChooseTalentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView name;
        public TextView tier;
        public TextView ranked;
        public TextView activation;
        public TextView description;

        public ChooseTalentViewHolder(View view)
        {
            super(view);

            name = view.findViewById(R.id.talent_name);
            tier = view.findViewById(R.id.talent_tier);
            activation = view.findViewById(R.id.talent_activation);
            ranked = view.findViewById(R.id.talent_ranked);
            description = view.findViewById(R.id.talent_description);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            listener.onItemClicked(name.getText().toString());
        }
    }

    public static interface GenesysTalentAdapterListener
    {
        void onItemClicked(String charmName);
    }
}
