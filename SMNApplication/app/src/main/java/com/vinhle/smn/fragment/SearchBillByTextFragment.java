package com.vinhle.smn.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.adapter.ListBillOfCustomerAdapter;
import com.vinhle.smn.common.SharedPreferenceHelper;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillOfCustomerSelectionListener;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.CompositedIdCustomerBillRequest;
import com.vinhle.smn.model.request.TextRequest;
import com.vinhle.smn.model.response.BillSearchResponse;
import com.vinhle.smn.model.response.BillStepResponse;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

/**
 * Created by VinhLe on 5/23/2017.
 */
public class SearchBillByTextFragment extends BaseFragment {

    private RecyclerView mRCVListBill;
    private TextView mTVContentSearch;
    private TextView mTVSearch;
    private EditText mEDTSearchBox;
    private FloatingActionButton mFABAddItem;

    private BillStepResponse[] billSteps;

    private ListBillOfCustomerAdapter billOfCustomerAdapter;
    private ProgressDialog mPGDWaiting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_text, container, Boolean.FALSE);

        mRCVListBill = (RecyclerView) root.findViewById(R.id.list_item);
        mEDTSearchBox = (EditText) root.findViewById(R.id.search_box);
        mTVSearch = (TextView) root.findViewById(R.id.btn_search);
        mTVContentSearch = (TextView) root.findViewById(R.id.content_search);
        mFABAddItem = (FloatingActionButton) root.findViewById(R.id.fab_add_item);
//        mIMBTopDown = (ImageButton) root.findViewById(R.id.arrow_top_down);

//        mFABAddItem.setOnClickListener(btnAddItemClickListener);
        mRCVListBill.setLayoutManager(new LinearLayoutManager(getContext()));
        mTVSearch.setOnClickListener(btnSearchClickListener);
        mFABAddItem.setVisibility(View.GONE);

        try {
            billSteps = SharedPreferenceHelper.getBillSteps(getContext());
        } catch (Exception e) {
            Log.e("SearchBillByText", e.getMessage(), e);
        }

        return root;
    }

    /*----------------------------------- Method Action Listener SearchBillByTextFragment ----------------------------------------------------*/
    private View.OnClickListener btnSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mEDTSearchBox.getText().toString().isEmpty() || mEDTSearchBox.getText().toString().equals(mTVContentSearch.getText()))
                return;

            mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<BillSearchResponse[]>() {
                @Override
                public void OnResult(boolean success, BillSearchResponse[] result) {
                    if (success) {
                        mTVContentSearch.setText(mEDTSearchBox.getText().toString());
                        billOfCustomerAdapter = new ListBillOfCustomerAdapter(result, billSteps, billOfCustomerSelectionListener);
                        mRCVListBill.setAdapter(billOfCustomerAdapter);
                    }
                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                }
            });
            TextRequest request = new TextRequest();
            request.setText(mEDTSearchBox.getText().toString());
            request.setDeviceId(StringHelper.GetDeviceId(getContext()));
            handler.execute(UrlEntity.E_BILL + UrlEntity.A_SEARCH_BILL_TEXT, BillSearchResponse[].class, request);
        }
    };

    private OnBillOfCustomerSelectionListener billOfCustomerSelectionListener = new OnBillOfCustomerSelectionListener() {
        @Override
        public void onBillOfCustomerSelection(Integer customerId, Integer billId) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.BILL_INFORMATION, new CompositedIdCustomerBillRequest(customerId, billId));
        }
    };
}
