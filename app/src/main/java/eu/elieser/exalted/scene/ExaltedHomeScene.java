package eu.elieser.exalted.scene;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.elieser.exalted.R;
import eu.elieser.exalted.logic.ExaltedHomeLogic;

/**
 * Created by bjorn on 24/04/16.
 */
public class ExaltedHomeScene extends Scene<ExaltedHomeLogic>
{

    private enum ButtonType
    {
        SOLAR_CHARMS,
        MARTIAL_ARTS,
        SPELLS
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.scene_exalted_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        logic = new ExaltedHomeLogic(this, getActivity());

        View charmsButton = view.findViewById(R.id.solar_charms_button);
        View martialArtsButton = view.findViewById(R.id.martial_arts_button);
        View spellsButton = view.findViewById(R.id.spells_button);

        setOnClick(ButtonType.SOLAR_CHARMS, charmsButton, this);
        setOnClick(ButtonType.MARTIAL_ARTS, martialArtsButton, this);
        setOnClick(ButtonType.SPELLS, spellsButton, this);
    }

    @Override
    public boolean onBackPressed()
    {
        return false;
    }

    @Override
    public void onClick(View view)
    {
        ButtonType type = (ButtonType) view.getTag();

        switch (type)
        {
            case SOLAR_CHARMS:
                logic.onClickSolarCharms();
                break;
            case MARTIAL_ARTS:
                logic.onClickMartialArtsCharms();
                break;
            case SPELLS:
                break;
        }
    }
}
