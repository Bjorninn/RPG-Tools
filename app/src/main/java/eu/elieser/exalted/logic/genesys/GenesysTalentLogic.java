package eu.elieser.exalted.logic.genesys;

import android.content.Context;
import android.content.Intent;

import eu.elieser.exalted.logic.Logic;
import eu.elieser.exalted.scene.genesys.GenesysTalentScene;

/**
 * Created by bjornjonsson on 11/03/2018.
 */

public class GenesysTalentLogic extends Logic<GenesysTalentScene>
{
    public GenesysTalentLogic(GenesysTalentScene scene, Context context)
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
}
