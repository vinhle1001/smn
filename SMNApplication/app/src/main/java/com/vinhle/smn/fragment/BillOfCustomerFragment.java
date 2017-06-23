package com.vinhle.smn.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.adapter.ListBillOfCustomerAdapter;
import com.vinhle.smn.common.SharedPreferenceHelper;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillOfCustomerSelectionListener;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.CompositedIdCustomerBillRequest;
import com.vinhle.smn.model.request.CustomerIdRequest;
import com.vinhle.smn.model.response.BillSearchResponse;
import com.vinhle.smn.model.response.BillStepResponse;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

/**
 * Created by VinhLe on 5/11/2017.
 */
public class BillOfCustomerFragment extends BaseFragment {

    private TextView mTVContent;
    private RecyclerView mRCVListItem;
    private FloatingActionButton mFABAddItem;
    private ListBillOfCustomerAdapter billOfCustomerAdapter;
    private ProgressDialog mPGDWaiting;

    private BillStepResponse[] billSteps;

    private Integer customerId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bill_of_customer, container, Boolean.FALSE);

        mTVContent = (TextView) root.findViewById(R.id.content_search);
        mRCVListItem = (RecyclerView) root.findViewById(R.id.list_item);
        mFABAddItem = (FloatingActionButton) root.findViewById(R.id.fab_add_item);

        mRCVListItem.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            customerId = bundle.getInt("customer_id", 0);
        }

        if (savedInstanceState != null) {
            customerId = savedInstanceState.getInt("customer_id", 0);
        }

        try {
            billSteps = SharedPreferenceHelper.getBillSteps(getContext());
        } catch (Exception e) {
            Log.e("BillOfCustomer", e.getMessage(), e);
        }

        mFABAddItem.setOnClickListener(fabAddItemClickListener);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (customerId < 1) return;
        mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<BillSearchResponse[]>() {
            @Override
            public void OnResult(boolean success, BillSearchResponse[] result) {
                if (success) {
                    mTVContent.setText(String.format("Found %d items", result.length));
                    billOfCustomerAdapter = new ListBillOfCustomerAdapter(result, billSteps, billOfCustomerSelectionListener);
                    mRCVListItem.setAdapter(billOfCustomerAdapter);
                } else mTVContent.setText("Not found items!");
                if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
            }
        });
        CustomerIdRequest request = new CustomerIdRequest();
        request.setCustomerId(customerId);
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        handler.execute(UrlEntity.E_BILL + UrlEntity.A_GET_BILL_BY_CUSTOMER_ID, BillSearchResponse[].class, request);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt("customer_id", customerId);
        }
        super.onSaveInstanceState(outState);
    }

    /*----------------------------------- Method Action Listener BillOfCustomerFragment ----------------------------------------------------*/
    private OnBillOfCustomerSelectionListener billOfCustomerSelectionListener = new OnBillOfCustomerSelectionListener() {
        @Override
        public void onBillOfCustomerSelection(Integer customerId, Integer billId) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.BILL_INFORMATION, new CompositedIdCustomerBillRequest(customerId, billId));
        }
    };

    private View.OnClickListener fabAddItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.BILL_INFORMATION, customerId);
        }
    };
}
