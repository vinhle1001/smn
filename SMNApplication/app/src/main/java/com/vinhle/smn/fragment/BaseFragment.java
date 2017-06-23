package com.vinhle.smn.fragment;

import android.support.v4.app.Fragment;

import com.vinhle.smn.handler.OnFragmentSelectionChanged;

/**
 * Created by VinhLe on 5/9/2017.
 */

public class BaseFragment extends Fragment {

    public OnFragmentSelectionChanged onFragmentSelectionChanged;

    public void setOnFragmentSelectionChanged(OnFragmentSelectionChanged onFragmentSelectionChanged) {
        this.onFragmentSelectionChanged = onFragmentSelectionChanged;
    }
}
