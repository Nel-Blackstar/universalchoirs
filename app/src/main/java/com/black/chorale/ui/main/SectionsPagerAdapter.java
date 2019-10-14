package com.black.chorale.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.black.chorale.MainActivity;
import com.black.chorale.TabEvenements;
import com.black.chorale.TabFinances;
import com.black.chorale.R;
import com.black.chorale.TabChants;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.Chants, R.string.Evenements,R.string.Finances};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabChants tabChants=new TabChants();
                return tabChants;
            case 1:
                TabEvenements tabEvenements=new TabEvenements();
                return tabEvenements;
            case 2:
                TabFinances tabFinances=new TabFinances();
                return tabFinances;
                default:
                    return  null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}