package eu.elieser.exalted.logic;

import android.content.Context;
import android.content.Intent;

import eu.elieser.exalted.scene.Scene;

/**
 * Created by bjorn on 07/03/16.
 */
public abstract class Logic<S extends Scene>
{
    final S scene;
    final Context context;

    public Logic(S scene, Context context)
    {
        this.scene = scene;
        this.context = context;
    }

    public abstract void onPermissionResults(boolean wasAllowed, int requestCode);

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);
}
