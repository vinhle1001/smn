package com.vinhle.smn.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TextView;

import com.vinhle.smn.R;

/**
 * Created by VinhLe on 5/23/2017.
 */

public class DateRangePickerFragment extends DialogFragment implements View.OnClickListener {

    private OnDateRangeSelectedListener onDateRangeSelectedListener;

    private TabHost tabHost;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private TextView btnDone;
    private TextView btnCancel;
    private boolean is24HourMode;

    public DateRangePickerFragment() {
        // Required empty public constructor
    }

    public static DateRangePickerFragment newInstance(OnDateRangeSelectedListener callback, boolean is24HourMode) {
        DateRangePickerFragment dateRangePickerFragment = new DateRangePickerFragment();
        dateRangePickerFragment.initialize(callback, is24HourMode);
        return dateRangePickerFragment;
    }

    public void initialize(OnDateRangeSelectedListener callback, boolean is24HourMode) {
        onDateRangeSelectedListener = callback;
        this.is24HourMode = is24HourMode;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(Boolean.FALSE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.date_range_dialog, container, Boolean.FALSE);
        tabHost = (TabHost) root.findViewById(R.id.tabHost);
        btnDone = (TextView) root.findViewById(R.id.btn_done);
        btnCancel = (TextView) root.findViewById(R.id.btn_cancel);
        startDatePicker = (DatePicker) root.findViewById(R.id.start_date_picker);
        endDatePicker = (DatePicker) root.findViewById(R.id.end_date_picker);
        btnDone.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        tabHost.findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec startDatePage = tabHost.newTabSpec("start");
        startDatePage.setContent(R.id.start_date_group);
        startDatePage.setIndicator(getString(R.string.title_tab_start_date));

        TabHost.TabSpec endDatePage = tabHost.newTabSpec("end");
        endDatePage.setContent(R.id.end_date_group);
        endDatePage.setIndicator(getString(R.string.title_tab_end_date));

        tabHost.addTab(startDatePage);
        tabHost.addTab(endDatePage);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null) return;
//        getDialog();
    }


    public void setOnDateRangeSelectedListener(OnDateRangeSelectedListener callback) {
        this.onDateRangeSelectedListener = callback;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (v.getId() == R.id.btn_done && onDateRangeSelectedListener != null)
            onDateRangeSelectedListener.onDateRangeSelected(
                    startDatePicker.getDayOfMonth(), startDatePicker.getMonth(), startDatePicker.getYear(),
                    endDatePicker.getDayOfMonth(), endDatePicker.getMonth(), endDatePicker.getYear());
    }

    public interface OnDateRangeSelectedListener {
        void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear);
    }

    public boolean is24HourMode() {
        return is24HourMode;
    }
}
