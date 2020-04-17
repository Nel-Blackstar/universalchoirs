package com.blackstar.MkOpportunity.Mychoir.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.blackstar.MkOpportunity.Mychoir.TabEvenements;
import com.blackstar.MkOpportunity.Mychoir.TabFinances;
import com.blackstar.MkOpportunity.Mychoir.TabChants;
import com.blackstar.MkOpportunity.Mychoir.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
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