package eu.elieser.exalted.logic;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import eu.elieser.exalted.data.DataStore;
import eu.elieser.exalted.data.Keyword;
import eu.elieser.exalted.exalted.charms.IKeywordListener;
import eu.elieser.exalted.scene.CharmDetailScene;
import eu.elieser.exalted.widgets.KeywordDialog;

/**
 * Created by bjorn on 23/04/16.
 */
public class CharmDetailLogic extends Logic<CharmDetailScene> implements IKeywordListener
{
    public CharmDetailLogic(CharmDetailScene scene, Context context)
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

    @Override
    public void onClickKeyword(View view, String kw)
    {
        DataStore dataStore = DataStore.getDataStore();
        Keyword keyword = dataStore.getKeyword(kw);

        KeywordDialog keywordDialog = new KeywordDialog();
        keywordDialog.show(context, keyword.getKeyword(), keyword.getDescription());
    }
}
