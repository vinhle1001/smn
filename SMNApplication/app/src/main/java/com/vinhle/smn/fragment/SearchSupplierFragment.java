package com.vinhle.smn.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.adapter.ListCustomerAdapter;
import com.vinhle.smn.adapter.ListSupplierAdapter;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnSupplierSelectionListener;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.TextRequest;
import com.vinhle.smn.model.response.SupplierSearchResponse;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

/**
 * Created by VinhLe on 5/29/2017.
 */

public class SearchSupplierFragment extends BaseFragment {

    private RecyclerView mRCVListSupplier;
    private TextView mTVContentSearch;
    private TextView mTVSearch;
    private EditText mEDTSearchBox;
    private FloatingActionButton mFABAddItem;
    private ProgressDialog mPGDWaiting;

    private ListSupplierAdapter listSupplierAdapter;

    private SupplierSearchResponse[] suppliers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_text, container, Boolean.FALSE);

        mRCVListSupplier = (RecyclerView) root.findViewById(R.id.list_item);
        mEDTSearchBox = (EditText) root.findViewById(R.id.search_box);
        mTVSearch = (TextView) root.findViewById(R.id.btn_search);
        mTVContentSearch = (TextView) root.findViewById(R.id.content_search);
        mFABAddItem = (FloatingActionButton) root.findViewById(R.id.fab_add_item);

        mRCVListSupplier.setLayoutManager(new LinearLayoutManager(getContext()));
        mFABAddItem.setOnClickListener(btnAddItemClickListener);
        mTVSearch.setOnClickListener(btnSearch);

        return root;
    }

    /*----------------------------------- Method Action Listener SearchSupplierFragment ----------------------------------------------------*/
    private OnSupplierSelectionListener supplierSelectionListener = new OnSupplierSelectionListener() {
        @Override
        public void OnEditItem(SupplierSearchResponse item) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.SUPPLIER_INFORMATION, item);
        }

        @Override
        public void OnCreateImport(SupplierSearchResponse item) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.IMPORT_INFORMATION, item);
        }

        @Override
        public void OnShowInvoices(SupplierSearchResponse item) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.IMPORTS_OF_SUPPLIER, item);
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
                        suppliers = (SupplierSearchResponse[]) result;
                        listSupplierAdapter = new ListSupplierAdapter(suppliers, supplierSelectionListener);
                        mRCVListSupplier.setAdapter(listSupplierAdapter);
                    }
                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                }
            });
            TextRequest request = new TextRequest();
            request.setText(mEDTSearchBox.getText().toString());
            request.setDeviceId(StringHelper.GetDeviceId(getContext()));
            handler.execute(UrlEntity.E_SUPPLIER + UrlEntity.A_SEARCH_SUPPLIER, SupplierSearchResponse[].class, request);
        }
    };

    private View.OnClickListener btnAddItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.SUPPLIER_INFORMATION, null);
        }
    };
}
