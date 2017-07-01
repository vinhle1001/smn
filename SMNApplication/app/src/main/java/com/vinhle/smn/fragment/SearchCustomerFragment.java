package com.vinhle.smn.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.adapter.ListCustomerAdapter;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnItemSelectionListener;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.TextRequest;
import com.vinhle.smn.model.response.CustomerSearchResponse;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

/**
 * Created by VinhLe on 5/8/2017.
 */

public class SearchCustomerFragment extends BaseFragment {

    private RecyclerView mRCVListCustomer;
    private TextView mTVContentSearch;
    private TextView mTVSearch;
    private EditText mEDTSearchBox;
//    private ImageButton mIMBTopDown;
    private ProgressDialog mPGDWaiting;
    private FloatingActionButton mFABAddItem;

    private ListCustomerAdapter listCustomerAdapter;

    private CustomerSearchResponse[] customers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_text, container, Boolean.FALSE);

        mRCVListCustomer = (RecyclerView) root.findViewById(R.id.list_item);
        mEDTSearchBox = (EditText) root.findViewById(R.id.search_box);
        mTVSearch = (TextView) root.findViewById(R.id.btn_search);
        mTVContentSearch = (TextView) root.findViewById(R.id.content_search);
        mFABAddItem = (FloatingActionButton) root.findViewById(R.id.fab_add_item);
//        mIMBTopDown = (ImageButton) root.findViewById(R.id.arrow_top_down);

        mFABAddItem.setOnClickListener(btnAddItemClickListener);
        mRCVListCustomer.setLayoutManager(new LinearLayoutManager(getContext()));
        mTVSearch.setOnClickListener(btnSearch);
        mEDTSearchBox.addTextChangedListener(searchBoxTextWatcher);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /*----------------------------------- Method Action Listener SearchCustomerFragment ----------------------------------------------------*/
    private View.OnClickListener arrowTopDownClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private TextWatcher searchBoxTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            mTVContentSearch.setText(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private OnItemSelectionListener itemCustomerSelectionListener = new OnItemSelectionListener() {
        @Override
        public void OnEditItem(Object customer) {
            Snackbar.make(mRCVListCustomer, "OnEditItem with customerId: " + customer, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.CUSTOMER_INFORMATION, customer);
        }

        @Override
        public void OnAddBillForCustomer(Object customer) {
            Snackbar.make(mRCVListCustomer, "OnAddBillForCustomer with customerId: " + customer, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.BILL_INFORMATION, customer);
        }

        @Override
        public void OnShowListBillOfCustomer(Object customer) {
            Snackbar.make(mRCVListCustomer, "OnShowListBillOfCustomer with customerId: " + customer, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.BILLS_OF_CUSTOMER, customer);
        }
    };

    private View.OnClickListener btnSearch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mEDTSearchBox.getText().toString().isEmpty() || mEDTSearchBox.getText().toString().equals(mTVContentSearch.getText()))
                return;

            mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest() {
                @Override
                public void OnResult(boolean success, Object result) {
                    if (success) {
                        mTVContentSearch.setText(mEDTSearchBox.getText().toString());
                        customers = (CustomerSearchResponse[]) result;
                        listCustomerAdapter = new ListCustomerAdapter(customers, itemCustomerSelectionListener);
                        mRCVListCustomer.setAdapter(listCustomerAdapter);
                    }
                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                }
            });
            TextRequest request = new TextRequest();
            request.setText(mEDTSearchBox.getText().toString());
            request.setDeviceId(StringHelper.GetDeviceId(getContext()));
            handler.execute(UrlEntity.E_CUSTOMER + UrlEntity.A_SEARCH_CUSTOMER, CustomerSearchResponse[].class, request);
        }
    };

    private View.OnClickListener btnAddItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.CUSTOMER_INFORMATION, (Object) null);
        }
    };
}
