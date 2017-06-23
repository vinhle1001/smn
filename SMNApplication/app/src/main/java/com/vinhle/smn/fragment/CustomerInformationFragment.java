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
import com.vinhle.smn.model.request.CustomerIdRequest;
import com.vinhle.smn.model.request.CustomerRequest;
import com.vinhle.smn.model.response.CustomerResponse;
import com.vinhle.smn.model.response.DistrictResponse;
import com.vinhle.smn.model.response.UpdateCustomerResponse;
import com.vinhle.smn.model.response.WardResponse;
import com.vinhle.smn.model.shared.ResourceModel;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by VinhLe on 5/8/2017.
 */
public class CustomerInformationFragment extends BaseFragment {

    private TextView mTVCancel;
    private TextView mTVDone;
    private EditText mEDTFirstName;
    private EditText mEDTLastName;
    private EditText mEDTFullName;
    private EditText mEDTPhoneNumber;
    private EditText mEDTBirthday;
    private EditText mEDTEmail;
    private EditText mEDTFacebook;
    private EditText mEDTAddress;
    private TextInputLayout mTILFirstName;
    private TextInputLayout mTILLastName;
    private TextInputLayout mTILFullName;
    private TextInputLayout mTILPhoneNumber;
    private TextInputLayout mTILEmail;
    private TextInputLayout mTILAddress;
    private SearchableSpinner mSSProvince;
    private SearchableSpinner mSSDistrict;
    private SearchableSpinner mSSWard;
    private EditText mEDTCustomerNote;
    private SegmentedGroup mSGGender;

    private ProgressDialog mPGDWaiting;

    private int customerId;
    private CustomerResponse customerResponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_information_customer, container, Boolean.FALSE);

        mTVCancel = (TextView) root.findViewById(R.id.btn_cancel);
        mTVDone = (TextView) root.findViewById(R.id.btn_done);
        mEDTFirstName = (EditText) root.findViewById(R.id.item_customer_firstname);
        mEDTLastName = (EditText) root.findViewById(R.id.item_customer_lastname);
        mEDTFullName = (EditText) root.findViewById(R.id.item_customer_fullname);
        mEDTPhoneNumber = (EditText) root.findViewById(R.id.item_customer_phone);
        mEDTBirthday = (EditText) root.findViewById(R.id.item_customer_birthday);
        mEDTEmail = (EditText) root.findViewById(R.id.item_customer_email);
        mEDTFacebook = (EditText) root.findViewById(R.id.item_customer_facebook);
        mEDTAddress = (EditText) root.findViewById(R.id.item_customer_address);
        mTILFirstName = (TextInputLayout) root.findViewById(R.id.item_layout_customer_firstname);
        mTILLastName = (TextInputLayout) root.findViewById(R.id.input_layout_customer_lastname);
        mTILFullName = (TextInputLayout) root.findViewById(R.id.input_layout_customer_fullname);
        mTILPhoneNumber = (TextInputLayout) root.findViewById(R.id.input_layout_customer_phone);
        mTILEmail = (TextInputLayout) root.findViewById(R.id.input_layout_customer_email);
        mTILAddress = (TextInputLayout) root.findViewById(R.id.input_layout_customer_address);
        mSSProvince = (SearchableSpinner) root.findViewById(R.id.spinner_province);
        mSSDistrict = (SearchableSpinner) root.findViewById(R.id.spinner_district);
        mSSWard = (SearchableSpinner) root.findViewById(R.id.spinner_ward);
        mSGGender = (SegmentedGroup) root.findViewById(R.id.customer_gender);
        mEDTCustomerNote = (EditText) root.findViewById(R.id.item_customer_note);

        try {
            mSSProvince.setItemsSource(Converter.Convert(SharedPreferenceHelper.getProvinces(getContext())));
            mSSProvince.setItemSearchableSelected(provinceSearchableSelected);
            mSSDistrict.setItemSearchableSelected(districtSearchableSelected);
        } catch (Exception e) {
            Log.e("CustomerInformation", e.getMessage(), e);
        }

        if (savedInstanceState != null && (customerResponse = (CustomerResponse) savedInstanceState.getSerializable("customer_information")) != null) {
            Log.i("HomeActivity", "Has savedInstanceState!" + customerResponse);
            savedInstanceState.remove("customer_information");
        }

        mTVDone.setOnClickListener(doneClickListener);
        mTVCancel.setOnClickListener(cancelClickListener);

        Bundle bundle = getArguments();
        if (bundle != null) {
            customerId = bundle.getInt("customer_id", 0);
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (customerId < 1 || customerResponse != null) {
            if (customerResponse != null)
                InitForm(customerResponse);
            return;
        }
        mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<CustomerResponse>() {
            @Override
            public void OnResult(boolean success, CustomerResponse obj) {
                if (success && obj.code == AppSetting.SUCCESS_CODE) {
                    InitForm(obj);
                }
                if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
            }
        });
        CustomerIdRequest request = new CustomerIdRequest();
        request.setCustomerId(customerId);
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        handler.execute(UrlEntity.E_CUSTOMER + UrlEntity.A_GET_CUSTOMER_BY_ID, CustomerResponse.class, request);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        clearErrorView();
        if (outState != null) {
            RestoreCustomerResponse();
            outState.putSerializable("customer_information", customerResponse);
        }
        super.onSaveInstanceState(outState);
    }

    private void InitForm(CustomerResponse obj) {
        customerResponse = obj;

        mEDTFirstName.setText(obj.firstName);
        mEDTLastName.setText(obj.lastName);
        mEDTFullName.setText(obj.fullName);
        mEDTPhoneNumber.setText(obj.phoneNumber);
        mEDTBirthday.setText(StringHelper.Convert(obj.birthday));
        mEDTEmail.setText(obj.email);
        mEDTFacebook.setText(obj.facebook);
        mEDTAddress.setText(obj.address);
        mSSProvince.setItemSelected(obj.province);
        mSSDistrict.setItemSelected(obj.district);
        mSSWard.setItemSelected(obj.ward);
        mSGGender.check(obj.gender.getId() == AppSetting.GENDER_MALE_ID ? R.id.customer_gender_male : R.id.customer_gender_female);
        mEDTCustomerNote.setText(obj.customerNote);
    }

    private void RestoreCustomerResponse() {
        if (customerResponse == null)
            customerResponse = new CustomerResponse();
        customerResponse.fullName = mEDTFullName.getText().toString();
        customerResponse.firstName = mEDTFirstName.getText().toString();
        customerResponse.lastName = mEDTLastName.getText().toString();
        customerResponse.phoneNumber = mEDTPhoneNumber.getText().toString();
        customerResponse.email = mEDTEmail.getText().toString();
        customerResponse.facebook = mEDTFacebook.getText().toString();
        customerResponse.address = mEDTAddress.getText().toString();
        customerResponse.birthday = Converter.Convert(mEDTBirthday.getText().toString());
        customerResponse.gender = new ResourceModel(mSGGender.getCheckedRadioButtonId() == R.id.customer_gender_male ? AppSetting.GENDER_MALE_ID : AppSetting.GENDER_FEMALE_ID, (String) null);
        customerResponse.province = mSSProvince.getItemSelected() != null ? new ResourceModel(mSSProvince.getItemSelected().getId(), mSSProvince.getItemSelected().getDisplayText()) : null;
        customerResponse.district = mSSDistrict.getItemSelected() != null ? new ResourceModel(mSSDistrict.getItemSelected().getId(), mSSDistrict.getItemSelected().getDisplayText()) : null;
        customerResponse.ward = mSSWard.getItemSelected() != null ? new ResourceModel(mSSWard.getItemSelected().getId(), mSSWard.getItemSelected().getDisplayText()) : null;
        customerResponse.customerNote = mEDTCustomerNote.getText().toString();
        customerResponse.isActive = AppSetting.ACTIVE;
    }

    private CustomerRequest InitCustomerRequest() {
        RestoreCustomerResponse();
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setDeviceId(StringHelper.GetDeviceId(getContext()));
        customerRequest.setCustomerId(customerResponse.customerId);
        customerRequest.setFullName(customerResponse.fullName);
        customerRequest.setFirstName(customerResponse.firstName);
        customerRequest.setLastName(customerResponse.lastName);
        customerRequest.setPhoneNumber(customerResponse.phoneNumber);
        customerRequest.setEmail(customerResponse.email);
        customerRequest.setFacebook(customerResponse.facebook);
        customerRequest.setAddress(customerResponse.address);
        customerRequest.setBirthday(customerResponse.birthday);
        customerRequest.setGenderId(customerResponse.gender != null ? customerResponse.gender.getId().byteValue() : null);
        customerRequest.setProvinceId(customerResponse.province != null ? customerResponse.province.getId() : null);
        customerRequest.setDistrictId(customerResponse.district != null ? customerResponse.district.getId() : null);
        customerRequest.setWardId(customerResponse.ward != null ? customerResponse.ward.getId() : null);
        customerRequest.setCustomerNote(customerResponse.customerNote);
        customerRequest.setIsActive(customerResponse.isActive);

        return customerRequest;
    }

    private boolean checkOnPreExecuteRequest(Object requestObj) {
        List<RequestPropertyException> exceptions = RequestPropertyChecker.GetErrorRequestProperty(requestObj);
        if (exceptions.size() == 0) return Boolean.TRUE;

        for (RequestPropertyException exception : exceptions) {
            if (exception.field != null && exception.field.startsWith("full_name")) {
                mTILFullName.setErrorEnabled(Boolean.TRUE);
                mTILFullName.setError(exception.message);
            } else if (exception.field != null && exception.field.startsWith("first_name")) {
                mTILFirstName.setErrorEnabled(Boolean.TRUE);
                mTILFirstName.setError(exception.message);
            } else if (exception.field != null && exception.field.startsWith("last_name")) {
                mTILLastName.setErrorEnabled(Boolean.TRUE);
                mTILLastName.setError(exception.message);
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
        mTILFirstName.setError(null);
        mTILLastName.setError(null);
        mTILFullName.setError(null);
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

    /*----------------------------------- Method Action Listener CustomerInformationFragment ----------------------------------------------------*/
    private View.OnClickListener doneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CustomerRequest customerRequest = InitCustomerRequest();
            if (!checkOnPreExecuteRequest(customerRequest)) return;

            mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<UpdateCustomerResponse>() {
                @Override
                public void OnResult(boolean success, UpdateCustomerResponse result) {
                    if (success && result.code == AppSetting.SUCCESS_CODE) {
                        customerResponse.customerId = result.getCustomerId();
                    } else {
                        CreateAlertDialog(R.string.error_wrong_data_response);
                    }
                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                }
            });
            handler.execute(UrlEntity.E_CUSTOMER + UrlEntity.A_SAVE_CUSTOMER, UpdateCustomerResponse.class, customerRequest);
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
                if (district.getProvinceId() == model.getId())
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
                if (district.getDistrictId() == model.getId())
                    wards.add(district);
            }
            mSSWard.setItemsSource(wards);
            mSSWard.setItemSelected(null);
        }
    };
}
