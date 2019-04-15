package com.example.viniciuspassari.testefast.Utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.viniciuspassari.testefast.R;
import com.example.viniciuspassari.testefast.ui.fragment.GenresFragment;
import com.example.viniciuspassari.testefast.ui.fragment.MoviesFragment;

public class Fragment extends android.support.v4.app.Fragment {

    public static void replaceFragment(FragmentManager fragmentManager, GenresFragment fragment, String backstack){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentFrame, fragment, backstack).addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public static void replaceFragment(FragmentManager fragmentManager, MoviesFragment fragment, String backstack){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentFrame, fragment, backstack).addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public static void removeFragment(FragmentManager fragmentManager){
        fragmentManager.popBackStack();
    }

    public static void removeAllFragments(FragmentManager fragmentManager){
        FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
        fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}

