package com.vinhle.smn.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vinhle.searchablespinner.SearchableSpinner;
import com.vinhle.smn.R;
import com.vinhle.smn.adapter.ListProductReturnedOfBillAdapter;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillPriceChanged;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.BillReturnedDetailRequest;
import com.vinhle.smn.model.request.BillReturnedRequest;
import com.vinhle.smn.model.request.CompositedIdCustomerBillRequest;
import com.vinhle.smn.model.response.BillReturnedDetailResponse;
import com.vinhle.smn.model.response.BillReturnedResponse;
import com.vinhle.smn.model.response.UpdateBillReturnedDetailResponse;
import com.vinhle.smn.model.response.UpdateBillReturnedResponse;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhLe on 6/9/2017.
 */
public class BillReturnedInformationFragment extends BaseFragment {

    private TextView mTVCancel;
    private TextView mTVDone;
    private TextView mTVBillRefundCost;
    private EditText mEDTBillCode;
    private EditText mEDTCustomerName;
    private EditText mEDTCustomerPhone;
    private EditText mEDTAddress;
    private SearchableSpinner mSSProvince;
    private SearchableSpinner mSSDistrict;
    private SearchableSpinner mSSWard;
    private SearchableSpinner mSSAgency;
    private RecyclerView mRCVListProduct;

    private BillReturnedResponse returnedResponse;
    private ListProductReturnedOfBillAdapter productReturnedOfBillAdapter;

    private int customerId;
    private int billId;
//    private EditText mEDTBillNote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_information_bill_returned, container, Boolean.FALSE);

        mTVCancel = (TextView) root.findViewById(R.id.btn_cancel);
        mTVDone = (TextView) root.findViewById(R.id.btn_done);
        mTVBillRefundCost = (TextView) root.findViewById(R.id.bill_refund_cost);
        mEDTBillCode = (EditText) root.findViewById(R.id.item_bill_code);
        mEDTCustomerName = (EditText) root.findViewById(R.id.item_customer_name);
        mEDTCustomerPhone = (EditText) root.findViewById(R.id.item_customer_phone);
        mEDTAddress = (EditText) root.findViewById(R.id.item_customer_address);
        mSSProvince = (SearchableSpinner) root.findViewById(R.id.spinner_province);
        mSSDistrict = (SearchableSpinner) root.findViewById(R.id.spinner_district);
        mSSWard = (SearchableSpinner) root.findViewById(R.id.spinner_ward);
        mSSAgency = (SearchableSpinner) root.findViewById(R.id.spinner_store);
        mRCVListProduct = (RecyclerView) root.findViewById(R.id.list_product);

        mRCVListProduct.setLayoutManager(new LinearLayoutManager(getContext()));

        /**Not Edit with Spinner*/
        mSSProvince.setEditable(Boolean.FALSE);
        mSSDistrict.setEditable(Boolean.FALSE);
        mSSWard.setEditable(Boolean.FALSE);
        mSSAgency.setEditable(Boolean.FALSE);

        if (savedInstanceState != null && (returnedResponse = (BillReturnedResponse) savedInstanceState.getSerializable("bill_returned_information")) != null) {
            Log.i("HomeActivity", "Has savedInstanceState!" + returnedResponse);
            savedInstanceState.remove("bill_returned_information");
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            customerId = bundle.getInt("customer_id", 0);
            billId = bundle.getInt("bill_id", 0);
        }

        mTVDone.setOnClickListener(doneClickListener);
        mTVCancel.setOnClickListener(cancelClickListener);

        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putSerializable("bill_returned_information", returnedResponse);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (customerId < 0 || billId < 0 || returnedResponse != null) {
            if (returnedResponse != null) {
                InitForm(returnedResponse);
            }
            return;
        }

        final ProgressDialog pgdWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<BillReturnedResponse>() {
            @Override
            public void OnResult(boolean success, BillReturnedResponse result) {
                if (success && result.code == AppSetting.SUCCESS_CODE) {
                    InitForm(result);
                } else {
                    CreateAlertDialog(R.string.error_wrong_data_response);
                }
                if (pgdWaiting.isShowing()) pgdWaiting.dismiss();
            }
        });
        CompositedIdCustomerBillRequest request = new CompositedIdCustomerBillRequest();
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        request.setBillId(billId);
        request.setCustomerId(customerId);
        handler.execute(UrlEntity.E_BILL + UrlEntity.A_GET_BILL_RETURNED, BillReturnedResponse.class, request);
    }

    private void InitForm(BillReturnedResponse response) {
        returnedResponse = response;

        mEDTBillCode.setText(response.getBillCode());
        mEDTCustomerName.setText(response.getCustomerName());
        mEDTCustomerPhone.setText(response.getCustomerPhone());
        mEDTAddress.setText(response.getAddress());

        mSSAgency.setItemSelected(returnedResponse.getAgency());
        mSSProvince.setItemSelected(response.getProvince());
        mSSDistrict.setItemSelected(response.getDistrict());
        mSSWard.setItemSelected(response.getWard());

        productReturnedOfBillAdapter = new ListProductReturnedOfBillAdapter(returnedResponse.getBillReturnedDetails(), billPriceChanged);
        mRCVListProduct.setAdapter(productReturnedOfBillAdapter);
        mTVBillRefundCost.setText(StringHelper.ConvertToVN(response.getBillRefundCost(), "VND"));
    }

    private BillReturnedRequest InitRequest() {
//        RestoreCustomerResponse();

        BillReturnedRequest billRequest = new BillReturnedRequest();
        billRequest.setDeviceId(StringHelper.GetDeviceId(getContext()));
        billRequest.setBillId(returnedResponse.getBillId());
        billRequest.setBillRefundCost(returnedResponse.getBillRefundCost());

        List<BillReturnedDetailRequest> billDetails = new ArrayList<>();
        for (BillReturnedDetailResponse item : returnedResponse.getBillReturnedDetails()) {
            if (item.getBillReturnedDetailId() == null && item.getProductReturnedQuantity() < 1)
                continue;
            billDetails.add(new BillReturnedDetailRequest(item.getBillReturnedDetailId(), item.getBillDetailId(), item.getProductId(), item.getProductReturnedQuantity(), item.getDescription(), item.getRefundCost()));
        }
        billRequest.setBillReturnedDetails(billDetails);
        return billRequest;
    }

    private void CreateAlertDialog(@StringRes int messageId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.app_name);
        builder.setMessage(messageId);
        builder.setCancelable(Boolean.FALSE);
        builder.setNegativeButton(R.string.done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /*----------------------------------- Method Action Listener BillReturnedInformationFragment ----------------------------------------------------*/
    private View.OnClickListener cancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.POP_BACK_STACK, null);
        }
    };

    private View.OnClickListener doneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final ProgressDialog pgdWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.update), Boolean.FALSE);
            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<UpdateBillReturnedResponse>() {
                @Override
                public void OnResult(boolean success, UpdateBillReturnedResponse result) {
                    if (success && result.code == AppSetting.SUCCESS_CODE) {
                        for (BillReturnedDetailResponse item : returnedResponse.getBillReturnedDetails()) {
                            for (UpdateBillReturnedDetailResponse response : result.getBillReturnedDetailResponses()) {
                                if (item.getBillDetailId().equals(item.getBillDetailId())) {
                                    item.setBillDetailId(response.getBillDetailId());
                                    break;
                                }
                            }
                        }
                    } else if (success && result.code == AppSetting.BILL_PRODUCT_EXCEED_QUANTITY_USED_CODE) {

                    }
                    if (pgdWaiting.isShowing()) pgdWaiting.dismiss();
                }
            });
            BillReturnedRequest request = InitRequest();
            handler.execute(UrlEntity.E_BILL + UrlEntity.A_SAVE_BILL_RETURNED, UpdateBillReturnedResponse.class, request);
        }
    };

    private OnBillPriceChanged billPriceChanged = new OnBillPriceChanged() {
        @Override
        public void OnBillItemPriceChange() {
            Long amount = 0L;
            for (BillReturnedDetailResponse item : returnedResponse.getBillReturnedDetails()) {
                amount += item.getRefundCost() * item.getProductReturnedQuantity();
            }
            returnedResponse.setBillRefundCost(amount);

            mTVBillRefundCost.setText(StringHelper.ConvertToVN(returnedResponse.getBillRefundCost(), "VND"));
        }
    };
}
