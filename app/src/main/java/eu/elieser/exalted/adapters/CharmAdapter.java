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
import java.util.Comparator;
import java.util.List;

import eu.elieser.exalted.R;
import eu.elieser.exalted.data.Charm;
import eu.elieser.exalted.data.Charms;

/**
 * Created by bjorn on 21/04/16.
 */
public class CharmAdapter extends RecyclerView.Adapter<CharmAdapter.ChooseCharmViewHolder>
{
    private final List<CharmData> itemData = new ArrayList<>();
    private final Context context;
    private final CharmAdapterListener listener;

    public CharmAdapter(Context context, CharmAdapterListener listener)
    {
        this.context = context;
        this.listener = listener;
    }

    public void setData(Charms charms)
    {
        itemData.clear();

        for (Charm charm :
                charms.getCharms())
        {
            itemData.add(CharmData.fromCharm(charm));
        }

        notifyDataSetChanged();
    }

    public void setData(List<CharmData> data)
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

    @Override
    public ChooseCharmViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(viewType, parent, false);

        return new ChooseCharmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChooseCharmViewHolder holder, int position)
    {
        CharmData charm = itemData.get(position);

        holder.ability.setText(charm.ability);
        holder.essence.setText(charm.essence);
        holder.cost.setText(charm.cost);
        holder.type.setText(charm.type);
        holder.duration.setText(charm.duration);
        holder.name.setText(charm.name);
        holder.description.setText(charm.description);
    }

    @Override
    public int getItemViewType(int position)
    {
        return R.layout.widget_charm_list_entry;
    }

    @Override
    public int getItemCount()
    {
        return itemData.size();
    }

    public class ChooseCharmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView ability;
        public TextView essence;
        public TextView cost;
        public TextView type;
        public TextView duration;
        public TextView name;
        public TextView description;

        public ChooseCharmViewHolder(View view)
        {
            super(view);

            ability = (TextView) view.findViewById(R.id.ability);
            essence = (TextView) view.findViewById(R.id.essence);
            cost = (TextView) view.findViewById(R.id.cost);
            type = (TextView) view.findViewById(R.id.type);
            duration = (TextView) view.findViewById(R.id.duration);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            listener.onItemClicked(name.getText().toString());
        }
    }

    public static interface CharmAdapterListener
    {
        void onItemClicked(String charmName);
    }

    public static class CharmDataComparator implements Comparator<CharmData>
    {
        @Override
        public int compare(CharmData c1, CharmData c2)
        {
            int compare = c1.abilitySort.compareTo(c2.abilitySort);

            if (compare == 0)
            {
                compare = c1.essence.compareTo(c2.essence);

                if (compare == 0)
                {
                    compare = c1.ability.compareTo(c2.ability);

                    if (compare == 0)
                    {
                        compare = c1.name.compareTo(c2.name);
                    }
                }
            }

            return compare;
        }

        @Override
        public boolean equals(Object o)
        {
            return false;
        }
    }

    public static class CharmData
    {
        public String abilitySort;
        public String ability;
        public String essence;
        public String cost;
        public String type;
        public String duration;
        public String name;
        public String description;

        public static CharmData fromCharm(Charm charm)
        {
            CharmData data = new CharmData();

            data.essence = charm.getMinEssence().getName() + " " + charm.getMinEssence().getValue();
            data.ability = charm.getMinAbility().getName() + " " + charm.getMinAbility().getValue();

            data.abilitySort = charm.getAbility();
            data.cost = charm.getCost();
            data.type = charm.getType();
            data.duration = charm.getDuration();
            data.name = charm.getName();

            int dotIndex = charm.getDescription().indexOf(".") + 1;

            data.description = charm.getDescription().substring(0, dotIndex);

            return data;
        }
    }
}
