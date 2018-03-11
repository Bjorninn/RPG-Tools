package eu.elieser.exalted.logic;

import android.content.Context;
import android.content.Intent;

import eu.elieser.exalted.navigation.Navigator;
import eu.elieser.exalted.scene.CharmListScene;
import eu.elieser.exalted.scene.ExaltedHomeScene;
import eu.elieser.exalted.scene.MartialArtsListScene;

/**
 * Created by bjorn on 24/04/16.
 */
public class ExaltedHomeLogic extends Logic<ExaltedHomeScene>
{
    public static int SolarCharmsFragmentId;

    public ExaltedHomeLogic(ExaltedHomeScene scene, Context context)
    {
        super(scene, context);
    }

    @Override
    public void onPermissionResults(boolean wasAllowed, int requestCode)
    {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }

    public void onClickSolarCharms()
    {
        SolarCharmsFragmentId = Navigator.getNavigator().navigationEvent(CharmListScene.class);
    }

    public void onClickMartialArtsCharms()
    {
        Navigator.getNavigator().navigationEvent(MartialArtsListScene.class);
    }
}
