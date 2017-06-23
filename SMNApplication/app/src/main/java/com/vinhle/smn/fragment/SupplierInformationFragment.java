package com.vinhle.smn.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vinhle.searchablespinner.BaseSearchableModel;
import com.vinhle.searchablespinner.ItemSearchableSelected;
import com.vinhle.searchablespinner.SearchableSpinner;
import com.vinhle.smn.R;
import com.vinhle.smn.annotation.RequestPropertyChecker;
import com.vinhle.smn.annotation.RequestPropertyException;
import com.vinhle.smn.common.Converter;
import com.vinhle.smn.common.SharedPreferenceHelper;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.SupplierIdRequest;
import com.vinhle.smn.model.request.SupplierRequest;
import com.vinhle.smn.model.response.DistrictResponse;
import com.vinhle.smn.model.response.SupplierResponse;
import com.vinhle.smn.model.response.UpdateSupplierResponse;
import com.vinhle.smn.model.response.WardResponse;
import com.vinhle.smn.model.shared.ResourceModel;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by VinhLe on 5/29/2017.
 */
public class SupplierInformationFragment extends BaseFragment {

    private TextView mTVCancel;
    private TextView mTVDone;
    private EditText mEDTName;
    private EditText mEDTPhoneNumber;
    private EditText mEDTEmail;
    private EditText mEDTAddress;
    private TextInputLayout mTILName;
    private TextInputLayout mTILPhoneNumber;
    private TextInputLayout mTILEmail;
    private TextInputLayout mTILAddress;
    private SearchableSpinner mSSProvince;
    private SearchableSpinner mSSDistrict;
    private SearchableSpinner mSSWard;
    private EditText mEDTSupplierNote;
    private SegmentedGroup mSGStatus;

    private ProgressDialog mPGDWaiting;
    private int supplierId;
    private SupplierResponse supplierResponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_information_supplier, container, Boolean.FALSE);

        mTVCancel = (TextView) root.findViewById(R.id.btn_cancel);
        mTVDone = (TextView) root.findViewById(R.id.btn_done);
        mEDTName = (EditText) root.findViewById(R.id.item_supplier_name);
        mEDTPhoneNumber = (EditText) root.findViewById(R.id.item_supplier_phone);
        mEDTEmail = (EditText) root.findViewById(R.id.item_supplier_email);
        mEDTAddress = (EditText) root.findViewById(R.id.item_supplier_address);
        mTILName = (TextInputLayout) root.findViewById(R.id.input_layout_supplier_name);
        mTILPhoneNumber = (TextInputLayout) root.findViewById(R.id.input_layout_supplier_phone);
        mTILEmail = (TextInputLayout) root.findViewById(R.id.input_layout_supplier_email);
        mTILAddress = (TextInputLayout) root.findViewById(R.id.input_layout_supplier_address);
        mEDTSupplierNote = (EditText) root.findViewById(R.id.item_supplier_note);
        mSSProvince = (SearchableSpinner) root.findViewById(R.id.spinner_province);
        mSSDistrict = (SearchableSpinner) root.findViewById(R.id.spinner_district);
        mSSWard = (SearchableSpinner) root.findViewById(R.id.spinner_ward);
        mSGStatus = (SegmentedGroup) root.findViewById(R.id.supplier_status);

        try {
            mSSProvince.setItemsSource(Converter.Convert(SharedPreferenceHelper.getProvinces(getContext())));
            mSSProvince.setItemSearchableSelected(provinceSearchableSelected);
            mSSDistrict.setItemSearchableSelected(districtSearchableSelected);
        } catch (Exception e) {
            Log.e("SupplierInformation", e.getMessage(), e);
        }

        if (savedInstanceState != null && (supplierResponse = (SupplierResponse) savedInstanceState.getSerializable("supplier_information")) != null) {
            Log.i("HomeActivity", "Has savedInstanceState!" + supplierResponse);
            savedInstanceState.remove("supplier_information");
        }

        mTVDone.setOnClickListener(doneClickListener);
        mTVCancel.setOnClickListener(cancelClickListener);

        Bundle bundle = getArguments();
        if (bundle != null) {
            supplierId = bundle.getInt("supplier_id", 0);
        }

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (supplierId < 1 || supplierResponse != null) {
            if (supplierResponse != null)
                InitForm(supplierResponse);
            return;
        }
        mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<SupplierResponse>() {
            @Override
            public void OnResult(boolean success, SupplierResponse obj) {
                if (success && obj.code == AppSetting.SUCCESS_CODE) {
                    InitForm(obj);
                }
                if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
            }
        });
        SupplierIdRequest request = new SupplierIdRequest();
        request.setSupplierId(supplierId);
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        handler.execute(UrlEntity.E_SUPPLIER + UrlEntity.A_GET_SUPPLIER_BY_ID, SupplierResponse.class, request);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        clearErrorView();
        if (outState != null) {
            RestoreSupplierResponse();
            outState.putSerializable("supplier_information", supplierResponse);
        }
        super.onSaveInstanceState(outState);
    }

    private void InitForm(SupplierResponse obj) {
        supplierResponse = obj;

        mEDTName.setText(obj.getName());
        mEDTPhoneNumber.setText(obj.getPhoneNumber());
        mEDTAddress.setText(obj.getAddress());
        mEDTEmail.setText(obj.getEmail());
        mSSProvince.setItemSelected(obj.getProvince());
        mSSDistrict.setItemSelected(obj.getDistrict());
        mSSWard.setItemSelected(obj.getWard());
        mSGStatus.check(obj.getIsActive() == AppSetting.ACTIVE ? R.id.active : R.id.deactivate);
        mEDTSupplierNote.setText(obj.getDescription());
    }

    private void RestoreSupplierResponse() {
        if (supplierResponse == null)
            supplierResponse = new SupplierResponse();
        supplierResponse.setName(mEDTName.getText().toString());
        supplierResponse.setPhoneNumber(mEDTPhoneNumber.getText().toString());
        supplierResponse.setEmail(mEDTEmail.getText().toString());
        supplierResponse.setAddress(mEDTAddress.getText().toString());
        supplierResponse.setProvince(mSSProvince.getItemSelected() != null ? new ResourceModel(mSSProvince.getItemSelected().getId(), mSSProvince.getItemSelected().getDisplayText()) : null);
        supplierResponse.setDistrict(mSSDistrict.getItemSelected() != null ? new ResourceModel(mSSDistrict.getItemSelected().getId(), mSSDistrict.getItemSelected().getDisplayText()) : null);
        supplierResponse.setWard(mSSWard.getItemSelected() != null ? new ResourceModel(mSSWard.getItemSelected().getId(), mSSWard.getItemSelected().getDisplayText()) : null);
        supplierResponse.setDescription(mEDTSupplierNote.getText().toString());
        supplierResponse.setIsActive(mSGStatus.getCheckedRadioButtonId() == R.id.active ? AppSetting.ACTIVE : AppSetting.DEACTIVATE);
    }

    private SupplierRequest InitSupplierRequest() {
        RestoreSupplierResponse();
        SupplierRequest supplierRequest = new SupplierRequest();
        supplierRequest.setDeviceId(StringHelper.GetDeviceId(getContext()));
        supplierRequest.setSupplierId(supplierResponse.getSupplierId());
        supplierRequest.setName(supplierResponse.getName());
        supplierRequest.setPhoneNumber(supplierResponse.getPhoneNumber());
        supplierRequest.setEmail(supplierResponse.getEmail());
        supplierRequest.setAddress(supplierResponse.getAddress());
        supplierRequest.setProvinceId(supplierResponse.getProvince() != null ? supplierResponse.getProvince().getId() : null);
        supplierRequest.setDistrictId(supplierResponse.getDistrict() != null ? supplierResponse.getDistrict().getId() : null);
        supplierRequest.setWardId(supplierResponse.getWard() != null ? supplierResponse.getWard().getId() : null);
        supplierRequest.setDescription(supplierResponse.getDescription());
        supplierRequest.setIsActive(supplierResponse.getIsActive());

        return supplierRequest;
    }

    private boolean checkOnPreExecuteRequest(Object requestObj) {
        List<RequestPropertyException> exceptions = RequestPropertyChecker.GetErrorRequestProperty(requestObj);
        if (exceptions.size() == 0) return Boolean.TRUE;

        for (RequestPropertyException exception : exceptions) {
            if (exception.field != null && exception.field.startsWith("name")) {
                mTILName.setErrorEnabled(Boolean.TRUE);
                mTILName.setError(exception.message);
            } else if (exception.field != null && exception.field.startsWith("email")) {
                mTILEmail.setErrorEnabled(Boolean.TRUE);
                mTILEmail.setError(exception.message);
            } else if (exception.field != null && exception.field.startsWith("phone_number")) {
                mTILPhoneNumber.setErrorEnabled(Boolean.TRUE);
                mTILPhoneNumber.setError(exception.message);
            } else if (exception.field != null && exception.field.startsWith("address")) {
                mTILAddress.setErrorEnabled(Boolean.TRUE);
                mTILAddress.setError(exception.message);
            } else if (exception.field != null && exception.field.startsWith("province")) {
                mSSProvince.setErrorEnabled(Boolean.TRUE);
                mSSProvince.setError(exception.message);
            } else if (exception.field != null && exception.field.startsWith("district")) {
                mSSDistrict.setErrorEnabled(Boolean.TRUE);
                mSSDistrict.setError(exception.message);
            }
        }
        return Boolean.FALSE;
    }

    private void clearErrorView() {
        mTILName.setError(null);
        mTILEmail.setError(null);
        mTILPhoneNumber.setError(null);
        mTILAddress.setError(null);

        mSSProvince.setError(null);
        mSSDistrict.setError(null);
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

    /*----------------------------------- Method Action Listener SupplierInformationFragment ----------------------------------------------------*/
    private View.OnClickListener doneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clearErrorView();
            SupplierRequest supplierRequest = InitSupplierRequest();
            if (!checkOnPreExecuteRequest(supplierRequest)) return;

            mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<UpdateSupplierResponse>() {
                @Override
                public void OnResult(boolean success, UpdateSupplierResponse result) {
                    if (success && result.code == AppSetting.SUCCESS_CODE) {
                        supplierResponse.setSupplierId(result.getSupplierId());
                    } else {
                        CreateAlertDialog(R.string.error_wrong_data_response);
                    }
                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                }
            });
            handler.execute(UrlEntity.E_SUPPLIER + UrlEntity.A_SAVE_SUPPLIER, UpdateSupplierResponse.class, supplierRequest);
        }
    };

    private View.OnClickListener cancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.POP_BACK_STACK, null);
        }
    };

    private ItemSearchableSelected provinceSearchableSelected = new ItemSearchableSelected() {
        @Override
        public void OnItemSelected(BaseSearchableModel model) throws Exception {
            List districts = new ArrayList();
            DistrictResponse[] districtSet = SharedPreferenceHelper.getDistricts(getContext());
            for (DistrictResponse district : districtSet) {
                if (district.getProvinceId().equals(model.getId()))
                    districts.add(district);
            }
            mSSDistrict.setItemsSource(districts);
            mSSWard.setItemSelected(null);
            mSSDistrict.setItemSelected(null);
        }
    };

    private ItemSearchableSelected districtSearchableSelected = new ItemSearchableSelected() {
        @Override
        public void OnItemSelected(BaseSearchableModel model) throws Exception {
            List wards = new ArrayList();
            WardResponse[] wardSet = SharedPreferenceHelper.getWards(getContext());
            for (WardResponse district : wardSet) {
                if (district.getDistrictId().equals(model.getId()))
                    wards.add(district);
            }
            mSSWard.setItemsSource(wards);
            mSSWard.setItemSelected(null);
        }
    };
}
