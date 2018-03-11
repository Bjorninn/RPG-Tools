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
import eu.elieser.exalted.data.dndspell.Spell;
import eu.elieser.exalted.data.dndspell.Spells;

/**
 * Created by bjorn on 15/05/16.
 */
public class Spell5eAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final List<SpellData> itemData = new ArrayList<>();
    private final Context context;
    private final CharmAdapterListener listener;

    private enum EntryType
    {
        LEVEL,
        SPELL
    }

    public Spell5eAdapter(Context context, CharmAdapterListener listener)
    {
        this.context = context;
        this.listener = listener;
    }

    public void setData(Spells spells)
    {
        itemData.clear();

        for (Spell spell :
                spells.getSpells())
        {
            itemData.add(SpellData.fromSpell(spell));
        }

        notifyDataSetChanged();
    }

    public void setData(List<SpellData> data)
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
    public SpellViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        int resId;

        if (viewType == EntryType.SPELL.ordinal())
        {
            resId = R.layout.widget_spell_list_entry;
        }
        else
        {
            resId = R.layout.widget_spell_list_level_entry;
        }

        View view = LayoutInflater.from(context).inflate(resId, parent, false);

        return new Spell5eAdapter.SpellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        SpellData data = itemData.get(position);

        if (data.isSpell())
        {
            SpellViewHolder vh = (SpellViewHolder) holder;
            vh.name.setText(data.name);
            vh.shortDescription.setText(data.shortInfo);
        }
        else
        {
            SpellLevelViewHolder vh = (SpellLevelViewHolder) holder;
            vh.level.setText(data.level);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        SpellData data = itemData.get(position);

        if (data.isSpell())
        {
            return R.layout.widget_spell_list_entry;
        }

        return R.layout.widget_spell_list_level_entry;
    }

    @Override
    public int getItemCount()
    {
        return itemData.size();
    }

    public class SpellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView name;
        public final TextView shortDescription;

        public SpellViewHolder(View view)
        {
            super(view);

            view.setOnClickListener(this);

            name = (TextView) view.findViewById(R.id.spell_name);
            shortDescription = (TextView) view.findViewById(R.id.spell_info_short);
        }

        @Override
        public void onClick(View view)
        {
            listener.onItemClicked(name.getText().toString());
        }
    }

    public class SpellLevelViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView level;


        public SpellLevelViewHolder(View view)
        {
            super(view);

            level = (TextView) view.findViewById(R.id.spell_level);
        }
    }

    public interface CharmAdapterListener

    {
        void onItemClicked(String charmName);
    }

    public static class SpellData
    {
        public final String level;
        public final String name;
        public final String shortInfo;

        public SpellData(String name, String shortInfo)
        {
            this.level = null;
            this.name = name;
            this.shortInfo = shortInfo;
        }

        public SpellData(String level)
        {
            this.level = level;
            this.name = null;
            this.shortInfo = null;
        }

        public boolean isSpell()
        {
            return level == null;
        }

        public static SpellData fromSpell(Spell spell)
        {
            String shortInfo = spell.getCastingTime().getCastingTime() + "; " + spell.getRange() + "; " + spell.getDuration();

            return new SpellData(spell.getName(), shortInfo);
        }
    }
}