package eu.elieser.exalted.scene;

import android.content.Intent;
import android.view.View;

import eu.elieser.exalted.logic.Logic;

/**
 * Created by bjorn on 16/02/16.
 */
public abstract class Scene<L extends Logic> extends android.support.v4.app.Fragment implements View.OnClickListener
{

    protected L logic;

    /**
     * Called when the user presses the hardware back button on the phone. If the back should not
     * be handled by the activity then the function needs to return true otherwise false.
     *
     * @return true if the Scene has devoured the event and nothing further should be done, false
     * otherwise.
     */
    public abstract boolean onBackPressed();

    public L getLogic()
    {
        return logic;
    }

    void setOnClick(Enum<?> tag, View view, View.OnClickListener listener)
    {
        view.setOnClickListener(listener);
        view.setTag(tag);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        logic.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();

        logic = null;
    }

    /*

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.scene_deliver, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

     */
}
