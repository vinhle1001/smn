package com.vinhle.smn.adapter;

import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.vinhle.smn.fragment.BaseFragment;
import com.vinhle.smn.handler.OnAfterInstantiateItemListener;

/**
 * Created by VinhLe on 5/25/2017.
 */

public class FragmentPagerItemAdapter extends com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter {

    private OnAfterInstantiateItemListener onAfterInstantiateItemListener;

    public FragmentPagerItemAdapter(FragmentManager fm, FragmentPagerItems pages, OnAfterInstantiateItemListener onAfterInstantiateItemListener) {
        super(fm, pages);
        this.onAfterInstantiateItemListener = onAfterInstantiateItemListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object item = super.instantiateItem(container, position);
        if (onAfterInstantiateItemListener != null && item != null && item instanceof BaseFragment)
            onAfterInstantiateItemListener.OnAfterInstantiateItemListener(item);
        return item;
    }
}
