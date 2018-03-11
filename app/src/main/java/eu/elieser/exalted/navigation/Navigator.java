package eu.elieser.exalted.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.reflect.Constructor;

import eu.elieser.exalted.R;
import eu.elieser.exalted.scene.ExaltedHomeScene;
import eu.elieser.exalted.scene.Scene;

/**
 * Created by bjorn on 12/04/15.
 */
public class Navigator
{
    private final FragmentActivity activity;
    private static Navigator navigator;

    public Scene getCurrentScene()
    {
        return (Scene) activity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    private Class currentScene = ExaltedHomeScene.class; //TODO change to start scene

    public Navigator(Activity activity)
    {
        this.activity = (FragmentActivity) activity;
        navigator = this;
    }

    public void clearBackStackToHome()
    {
        activity.getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentManager manager = activity.getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0)
        {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        navigationEvent(ExaltedHomeScene.class); //TODO change to start scene
    }

    public int navigationEvent(Class<? extends Fragment> clazz)
    {
        return navigationEvent(clazz, null);
    }

    public int navigationEvent(Class<? extends Fragment> clazz, Bundle bundle)
    {
        try
        {
            Constructor<?> constructor = clazz.getConstructors()[0];
            Fragment fragment = (Fragment) constructor.newInstance();
            if (bundle != null)
            {
                fragment.setArguments(bundle);
            }

            return navigationEvent(fragment);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    public int navigationEvent(Fragment fragment)
    {
        currentScene = fragment.getClass();

        //actionBarShenanigans((MainActivity) activity, (FibActionBarDelegate) fragment);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.slide_in_left, R.animator.slide_out_right);
        //transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_right);

        transaction.add(R.id.fragment_container, fragment, fragment.getClass().getName());
        transaction.addToBackStack(currentScene.getName());
        return transaction.commit();
    }

    public boolean popFragmentBackStack()
    {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        boolean didPop = fragmentManager.popBackStackImmediate();

        if (didPop)
        {
            Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
            //actionBarShenanigans((MainActivity) activity, (FibActionBarDelegate) fragment);

            currentScene = fragment.getClass();

        }

        return didPop;
    }

    public void popFragmentBackStackToScene(String name)
    {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        fragmentManager.popBackStackImmediate(name, 0);

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        //actionBarShenanigans((MainActivity) activity, (FibActionBarDelegate) fragment);

        currentScene = fragment.getClass();
    }

    public void popFragmentBackStackToBottom()
    {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        //actionBarShenanigans((MainActivity) activity, (FibActionBarDelegate) fragment);

        currentScene = fragment.getClass();
    }

    public boolean emptyFragmentBackStack()
    {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        int id = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getId();

        return fragmentManager.popBackStackImmediate(id, 0);
    }

//    private void actionBarShenanigans(MainActivity activity, FibActionBarDelegate delegate)
//    {
//        activity.setActionBarDelegate(delegate);
//        activity.setActionBarIcons(delegate.getLeftButtonType(), delegate.getRightButtonType());
//        activity.setActionBarTitle(delegate.getSceneName(activity));
//    }

    public static Navigator getNavigator()
    {
        return navigator;
    }
}
