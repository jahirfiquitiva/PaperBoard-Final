package com.jahirfiquitiva.paperboard.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jahirfiquitiva.paperboard.views.SlidingTabLayout;

import jahirfiquitiva.paperboard.sample.R;

public class PreviewsFragment extends Fragment {
	
	private ViewPager mPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_all_icons, container, false);

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (toolbar != null)
            toolbar.setTitle(R.string.section_two);

        mPager = (ViewPager) root.findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));

        TabLayout mTabs = (TabLayout) layout.findViewById(R.id.tabs);
        mTabs.setupWithViewPager(mPager);
        mTabs.setTabTextColors(getResources().getColor(R.color.semitransparent_white),
                getResources().getColor(android.R.color.white));
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.accent));
        mTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toolbar appbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            appbar.setElevation(0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toolbar appbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            appbar.setElevation((int) getResources().getDimension(R.dimen.toolbar_elevation));
        }
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        final String[] tabs;

        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new Fragment();
            switch (position) {
                case 0:
                    f = IconsFragment.newInstance(R.array.latest);
                    break;
				case 1:
                    f = IconsFragment.newInstance(R.array.system);
                    break;
                case 2:
                    f = IconsFragment.newInstance(R.array.google);
                    break;
                case 3:
                    f = IconsFragment.newInstance(R.array.games);
                    break;
                case 4:
                    f = IconsFragment.newInstance(R.array.icon_pack);
                    break;
                case 5:
                    f = IconsFragment.newInstance(R.array.drawer);
                    break;
            }
            return f;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return tabs.length;
        }
    }
}
