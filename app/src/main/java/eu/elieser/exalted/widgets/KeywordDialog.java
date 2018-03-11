package eu.elieser.exalted.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import eu.elieser.exalted.R;

/**
 * Created by bjorn on 26/04/16.
 */
public class KeywordDialog
{
    public void show(Context context, String title, String message)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {

                    }
                });

        builder.create().show();
    }
}
