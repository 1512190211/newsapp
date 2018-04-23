package com.example.newsapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class MyFragmentViewAdapter extends FragmentPagerAdapter {
    private List<ListViewFragment> list;
    private List<String> title;
    boolean[] fragmentsUpdateFlag = {false, false, false, false, false, false, false, false};
    FragmentManager fm;

    private Context context;

    public MyFragmentViewAdapter(FragmentManager fm, List<ListViewFragment> list, List<String> title) {
        super(fm);
        this.fm = fm;
        this.title = title;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup Container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(Container, position);
        String fragmentTag = fragment.getTag();
        if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fragment);
            fragment = list.get(position % list.size());
            ft.add(Container.getId(), fragment, fragmentTag);
            ft.attach(fragment);
            ft.commit();
            fragmentsUpdateFlag[position % fragmentsUpdateFlag.length] = false;
        }
        return fragment;
    }
}


