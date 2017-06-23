package com.vinhle.smn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.vinhle.smn.R;
import com.vinhle.smn.adapter.FragmentPagerItemAdapter;
import com.vinhle.smn.handler.OnAfterInstantiateItemListener;

/**
 * Created by VinhLe on 5/23/2017.
 */
public class BillFragment extends BaseFragment {

    private SmartTabLayout mSTLTabViewPaper;
    private ViewPager mVPPage;
    private FragmentPagerItemAdapter mSTAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_bill, container, Boolean.FALSE);

        mSTAdapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(), FragmentPagerItems.with(getContext())
                .add(R.string.label_tab_bill_date, SearchBillRangeDateFragment.class)
                .add(R.string.label_tab_bill_text, SearchBillByTextFragment.class)
                .create(),
                onAfterInstantiateItemListener);

        mSTLTabViewPaper = (SmartTabLayout) root.findViewById(R.id.viewpager_tab);
        mSTLTabViewPaper.setOnPageChangeListener(onPageChangeListener);

        mVPPage = (ViewPager) root.findViewById(R.id.viewpager);
        mVPPage.setAdapter(mSTAdapter);
        mSTLTabViewPaper.setViewPager(mVPPage);

        return root;
    }

    /*----------------------------------- Method Action Listener BillFragment ----------------------------------------------------*/
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            /*if (mSTAdapter != null) {
                Fragment fragment = mSTAdapter.getPage(position);
                if (fragment != null && fragment instanceof BaseFragment)
                    ((BaseFragment) fragment).setOnFragmentSelectionChanged(onFragmentSelectionChanged);
            }*/
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private OnAfterInstantiateItemListener onAfterInstantiateItemListener = new OnAfterInstantiateItemListener() {
        @Override
        public void OnAfterInstantiateItemListener(Object obj) {
            if (obj != null && obj instanceof BaseFragment)
                ((BaseFragment) obj).setOnFragmentSelectionChanged(onFragmentSelectionChanged);
        }
    };
}
