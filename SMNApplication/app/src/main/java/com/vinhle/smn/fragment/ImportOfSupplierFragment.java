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
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.adapter.ListBillOfCustomerAdapter;
import com.vinhle.smn.adapter.ListImportOfSupplierAdapter;
import com.vinhle.smn.common.SharedPreferenceHelper;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillOfCustomerSelectionListener;
import com.vinhle.smn.handler.OnImportSelectionListener;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.CompositedIdCustomerBillRequest;
import com.vinhle.smn.model.request.CompositedIdSupplierImportRequest;
import com.vinhle.smn.model.request.CustomerIdRequest;
import com.vinhle.smn.model.request.SupplierIdRequest;
import com.vinhle.smn.model.response.BillSearchResponse;
import com.vinhle.smn.model.response.BillStepResponse;
import com.vinhle.smn.model.response.ImportSearchResponse;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

/**
 * Created by VinhLe on 5/11/2017.
 */
public class ImportOfSupplierFragment extends BaseFragment {

    private TextView mTVContent;
    private RecyclerView mRCVListItem;
    private FloatingActionButton mFABAddItem;
    private ListImportOfSupplierAdapter importOfSupplierAdapter;
    private ProgressDialog mPGDWaiting;

    private Integer supplierId;

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
            supplierId = bundle.getInt("supplier_id", 0);
        }

        if (savedInstanceState != null) {
            supplierId = savedInstanceState.getInt("supplier_id", 0);
        }

        mFABAddItem.setOnClickListener(fabAddItemClickListener);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (supplierId < 1) return;
        mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<ImportSearchResponse[]>() {
            @Override
            public void OnResult(boolean success, ImportSearchResponse[] result) {
                if (success) {
                    mTVContent.setText(String.format("Found %d items", result.length));
                    importOfSupplierAdapter = new ListImportOfSupplierAdapter(result, importSelectionListener);
                    mRCVListItem.setAdapter(importOfSupplierAdapter);
                } else mTVContent.setText("Not found items!");
                if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
            }
        });
        SupplierIdRequest request = new SupplierIdRequest();
        request.setSupplierId(supplierId);
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        handler.execute(UrlEntity.E_SUPPLIER + UrlEntity.A_GET_IMPORT_BY_SUPPLIER_ID, ImportSearchResponse[].class, request);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt("supplier_id", supplierId);
        }
        super.onSaveInstanceState(outState);
    }

    /*----------------------------------- Method Action Listener BillOfCustomerFragment ----------------------------------------------------*/
    private OnImportSelectionListener importSelectionListener = new OnImportSelectionListener() {
        @Override
        public void OnImportSelection(Integer importId, Integer supplierId) {
            if (onFragmentSelectionChanged != null) {
                CompositedIdSupplierImportRequest model = new CompositedIdSupplierImportRequest();
                model.setSupplierId(supplierId);
                model.setImportId(importId);
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.IMPORT_INFORMATION, model);
            }
        }
    };

    private View.OnClickListener fabAddItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.IMPORT_INFORMATION, supplierId);
        }
    };
}
