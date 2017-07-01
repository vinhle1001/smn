package com.vinhle.smn.fragment;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vinhle.searchablespinner.BaseSearchableModel;
import com.vinhle.searchablespinner.ItemSearchableSelected;
import com.vinhle.searchablespinner.SearchableSpinner;
import com.vinhle.smn.R;
import com.vinhle.smn.adapter.ListProductOfBillAdapter;
import com.vinhle.smn.annotation.RequestPropertyChecker;
import com.vinhle.smn.annotation.RequestPropertyException;
import com.vinhle.smn.common.Converter;
import com.vinhle.smn.common.JacksonHelper;
import com.vinhle.smn.common.SharedPreferenceHelper;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillPriceChanged;
import com.vinhle.smn.handler.OnMqttServiceCallback;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.BillDetailRequest;
import com.vinhle.smn.model.request.BillRequest;
import com.vinhle.smn.model.request.CompositedIdCustomerBillRequest;
import com.vinhle.smn.model.response.BillDetailResponse;
import com.vinhle.smn.model.response.BillResponse;
import com.vinhle.smn.model.response.BillStepResponse;
import com.vinhle.smn.model.response.DistrictResponse;
import com.vinhle.smn.model.response.ProductOfAgencyResponse;
import com.vinhle.smn.model.response.UpdateBillDetailResponse;
import com.vinhle.smn.model.response.UpdateBillResponse;
import com.vinhle.smn.model.response.WardResponse;
import com.vinhle.smn.model.shared.ResourceModel;
import com.vinhle.smn.mqtt.Connection;
import com.vinhle.smn.service.MqttService;
import com.vinhle.smn.service.MqttServiceContants;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.MqttSetting;
import com.vinhle.smn.setting.UrlEntity;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by VinhLe on 5/12/2017.
 */
public class BillInformationFragment extends BaseFragment {

    private TextView mTVCancel;
    private TextView mTVDone;
    private TextView mTVAddProduct;
    private TextView mTVBillPrice;
    private EditText mEDTBillCode;
    private EditText mEDTCustomerName;
    private EditText mEDTCustomerPhone;
    private EditText mEDTAddress;
    private TextInputLayout mTILAddress;
    private SearchableSpinner mSSProvince;
    private SearchableSpinner mSSDistrict;
    private SearchableSpinner mSSWard;
    private SearchableSpinner mSSAgency;
    private RecyclerView mRCVListProduct;
    private EditText mEDTBillNote;
    private SegmentedGroup mSGBillStep;
    private Button mBTReturned;

    private ListProductOfBillAdapter productOfBillAdapter;

    private BillStepResponse[] billSteps;
//    private ProgressDialog mPGDWaiting;

    private Connection connection;

    private BillResponse billResponse;
    private int customerId;
    private int billId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_information_bill, container, Boolean.FALSE);

        mTVCancel = (TextView) root.findViewById(R.id.btn_cancel);
        mTVDone = (TextView) root.findViewById(R.id.btn_done);
        mTVAddProduct = (TextView) root.findViewById(R.id.btn_add_product);
        mTVBillPrice = (TextView) root.findViewById(R.id.bill_price);
        mEDTBillCode = (EditText) root.findViewById(R.id.item_bill_code);
        mEDTCustomerName = (EditText) root.findViewById(R.id.item_customer_name);
        mEDTCustomerPhone = (EditText) root.findViewById(R.id.item_customer_phone);
        mEDTAddress = (EditText) root.findViewById(R.id.item_customer_address);
        mTILAddress = (TextInputLayout) root.findViewById(R.id.input_layout_customer_address);
        mSSProvince = (SearchableSpinner) root.findViewById(R.id.spinner_province);
        mSSDistrict = (SearchableSpinner) root.findViewById(R.id.spinner_district);
        mSSWard = (SearchableSpinner) root.findViewById(R.id.spinner_ward);
        mSSAgency = (SearchableSpinner) root.findViewById(R.id.spinner_store);
        mRCVListProduct = (RecyclerView) root.findViewById(R.id.list_product);
        mEDTBillNote = (EditText) root.findViewById(R.id.item_bill_note);
        mSGBillStep = (SegmentedGroup) root.findViewById(R.id.item_bill_step);
        mBTReturned = (Button) root.findViewById(R.id.btn_returned);
        mRCVListProduct.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            billSteps = SharedPreferenceHelper.getBillSteps(getContext());
            mSSAgency.setItemsSource(Converter.Convert(SharedPreferenceHelper.getAgencies(getContext())));
            mSSProvince.setItemsSource(Converter.Convert(SharedPreferenceHelper.getProvinces(getContext())));
            mSSAgency.setItemSearchableSelected(agencySearchableSelected);
            mSSProvince.setItemSearchableSelected(provinceSearchableSelected);
            mSSDistrict.setItemSearchableSelected(districtSearchableSelected);
        } catch (Exception e) {
            Log.e("BillInformation", e.getMessage(), e);
        }

        if (savedInstanceState != null && (billResponse = (BillResponse) savedInstanceState.getSerializable("bill_information")) != null) {
            Log.i("HomeActivity", "Has savedInstanceState!" + billResponse);
            savedInstanceState.remove("customer_information");
        }

        mTVDone.setOnClickListener(doneClickListener);
        mTVCancel.setOnClickListener(cancelClickListener);
        mTVAddProduct.setOnClickListener(addProductClickListener);
        mSGBillStep.setOnCheckedChangeListener(billStepCheckedChangeListener);
        mBTReturned.setOnClickListener(btnBillReturnedClickListener);

        Bundle bundle = getArguments();
        if (bundle != null) {
            customerId = bundle.getInt("customer_id", 0);
            billId = bundle.getInt("bill_id", 0);
        }

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (customerId < 1 || billResponse != null) {
            if (billResponse != null)
                InitForm(billResponse);
            return;
        }

        // bind service to fragment
        Intent intent = new Intent(getContext(), MqttService.class);
        getContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        // load data from api
        final ProgressDialog mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<BillResponse>() {
            @Override
            public void OnResult(boolean success, BillResponse obj) {
                if (success && obj.code == AppSetting.SUCCESS_CODE) {
                    InitForm(obj);
                }
                if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
            }
        });
        CompositedIdCustomerBillRequest request = new CompositedIdCustomerBillRequest();
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        request.setCustomerId(customerId);
        request.setBillId(billId);
        handler.execute(UrlEntity.E_BILL + UrlEntity.A_GET_BILL_BY_COMPOSITED_ID, BillResponse.class, request);
    }

    @Override
    public void onStop() {
        super.onStop();

        getContext().unbindService(serviceConnection);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        clearErrorView();
        if (outState != null) {
            RestoreCustomerResponse();
            outState.putSerializable("bill_information", billResponse);
        }
        super.onSaveInstanceState(outState);
    }

    private void RestoreCustomerResponse() {
        billResponse.setBillPrice(Converter.ConvertToLong(mTVBillPrice.getText().toString()));
        billResponse.setAgency(mSSAgency.getItemSelected() != null ? new ResourceModel(mSSAgency.getItemSelected().getId(), mSSAgency.getItemSelected().getDisplayText()) : null);
        billResponse.setAddress(mEDTAddress.getText().toString());
        billResponse.setProvince(mSSProvince.getItemSelected() != null ? new ResourceModel(mSSProvince.getItemSelected().getId(), mSSProvince.getItemSelected().getDisplayText()) : null);
        billResponse.setDistrict(mSSDistrict.getItemSelected() != null ? new ResourceModel(mSSDistrict.getItemSelected().getId(), mSSDistrict.getItemSelected().getDisplayText()) : null);
        billResponse.setWard(mSSWard.getItemSelected() != null ? new ResourceModel(mSSWard.getItemSelected().getId(), mSSWard.getItemSelected().getDisplayText()) : null);
        billResponse.setDescription(mEDTBillNote.getText().toString());
        billResponse.setBillStepId(mSGBillStep.getCheckedRadioButtonId() == R.id.bill_step_propose ? AppSetting.BILL_STEP_PROPOSE_ID
                : mSGBillStep.getCheckedRadioButtonId() == R.id.bill_step_delivery ? AppSetting.BILL_STEP_DELIVERY_ID
                : mSGBillStep.getCheckedRadioButtonId() == R.id.bill_step_received ? AppSetting.BILL_STEP_RECEIVED_ID : AppSetting.BILL_STEP_PROPOSE_ID);
        billResponse.setIsActive(AppSetting.ACTIVE);
    }

    private void InitForm(BillResponse obj) {
        billResponse = obj;

        mEDTBillCode.setText(obj.getBillCode());
        mEDTCustomerName.setText(obj.getCustomerName());
        mEDTCustomerPhone.setText(obj.getCustomerPhone());
        mEDTAddress.setText(obj.getAddress());
        mSSProvince.setItemSelected(obj.getProvince());
        mSSDistrict.setItemSelected(obj.getDistrict());
        mSSWard.setItemSelected(obj.getWard());
        mSSAgency.setItemSelected(obj.getAgency());

        if (billResponse.getBillDetails() == null)
            billResponse.setBillDetails(new ArrayList<BillDetailResponse>());
        productOfBillAdapter = new ListProductOfBillAdapter(billResponse.getBillDetails(), billPriceChanged);
        mRCVListProduct.setAdapter(productOfBillAdapter);

        mTVBillPrice.setText(StringHelper.ConvertToVN(obj.getBillPrice(), "VND"));
        mEDTBillNote.setText(obj.getDescription());
        mSSAgency.setEditable(obj.getBillCode() == null || obj.getBillCode().isEmpty());
        if (obj.getBillStepId() != null)
            mSGBillStep.check(obj.getBillStepId().equals(AppSetting.BILL_STEP_PROPOSE_ID) ? R.id.bill_step_propose
                    : obj.getBillStepId().equals(AppSetting.BILL_STEP_DELIVERY_ID) ? R.id.bill_step_delivery
                    : R.id.bill_step_received);

        if (billResponse.getBillId() != null)
            mBTReturned.setVisibility(View.VISIBLE);

        LoadProductOfAgency(obj.getAgency() != null ? obj.getAgency().getId() : null);
    }

    private BillRequest InitRequest() {
        RestoreCustomerResponse();

        BillRequest billRequest = new BillRequest();
        billRequest.setDeviceId(StringHelper.GetDeviceId(getContext()));
        billRequest.setBillId(billResponse.getBillId());
        billRequest.setBillCode(billResponse.getBillCode());
        billRequest.setCustomerId(billResponse.getCustomerId());
        billRequest.setAgencyId(billResponse.getAgency() != null ? billResponse.getAgency().getId() : null);
        billRequest.setBillPrice(billResponse.getBillPrice());
        billRequest.setAddress(billResponse.getAddress());
        billRequest.setBillStepId(billResponse.getBillStepId());
        billRequest.setProvinceId(billResponse.getProvince() != null ? billResponse.getProvince().getId() : null);
        billRequest.setDistrictId(billResponse.getDistrict() != null ? billResponse.getDistrict().getId() : null);
        billRequest.setWardId(billResponse.getWard() != null ? billResponse.getWard().getId() : null);
        billRequest.setDescription(billResponse.getDescription());
        List<BillDetailRequest> billDetails = new ArrayList<>();
        for (BillDetailResponse item : billResponse.getBillDetails()) {
            if (item.getProduct() == null) continue;
            billDetails.add(new BillDetailRequest(item.getBillDetailId(), item.getProduct().getId(), item.getDescription(), item.getProductPrice(), item.getProductQuantity(), item.getIsActive()/*, item.getIsModified()*/, item.getUid()));
        }
        billRequest.setBillDetails(billDetails);
        billRequest.setIsActive(billResponse.getIsActive());
        return billRequest;
    }

    private void LoadProductOfAgency(Integer agencyId) {
        if (agencyId == null || agencyId.equals(0)) return;

        /*final ProgressDialog mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<ProductOfAgencyResponse[]>() {
            @Override
            public void OnResult(boolean success, ProductOfAgencyResponse[] obj) {
                if (success) {
                    productOfBillAdapter.setProducts(obj);
                } else {
                    CreateAlertDialog(R.string.error_wrong_data_response);
                }
                if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
            }
        });
        AgencyIdRequest request = new AgencyIdRequest();
        request.setAgencyId(agencyId);
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        handler.execute(UrlEntity.E_PRODUCT + UrlEntity.A_GET_ALL_PRODUCT_OF_AGENCY, ProductOfAgencyResponse[].class, request);*/
//        connection.subscribe(StringHelper.ConcatWithSplit(MqttSetting.SPLIT_CHAR, MqttSetting.TOPIC_PRODUCT_OF_AGENCY, String.valueOf(agencyId)));
        Intent intent = new Intent(MqttSetting.ACTION_CALL_MQTT_SERVICE);
        intent.putExtra(MqttServiceContants.action, MqttServiceContants.subscribe);
        intent.putExtra(MqttServiceContants.topic, StringHelper.ConcatWithSplit(MqttSetting.SPLIT_CHAR, MqttSetting.TOPIC_PRODUCT_OF_AGENCY, String.valueOf(agencyId)));
        getContext().sendBroadcast(intent);

    }

    private boolean checkOnPreExecuteRequest(Object requestObj) {
        List<RequestPropertyException> exceptions = RequestPropertyChecker.GetErrorRequestProperty(requestObj);
        if (exceptions.size() == 0) return Boolean.TRUE;

        for (RequestPropertyException exception : exceptions) {
            if (exception.field != null && exception.field.startsWith("agency")) {
                mSSAgency.setErrorEnabled(Boolean.TRUE);
                mSSAgency.setError(exception.message);
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
        mTILAddress.setError(null);
        mSSAgency.setError(null);
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

    /*----------------------------------- Method Action Listener BillInformationFragment ----------------------------------------------------*/
    private ServiceConnection serviceConnection = new ServiceConnection() {

        MqttService.MqttServiceBinder binder;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("ServiceConnection", "OnServiceConnected");
            this.binder = (MqttService.MqttServiceBinder) service;
            binder.registerCallback(mqttCallback);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("ServiceConnection", "OnServiceDisconnected");
            binder.unregisterCallback(mqttCallback);
        }
    };

    private OnMqttServiceCallback mqttCallback = new OnMqttServiceCallback() {
        @Override
        public void OnMessageArrived(String topic, byte[] payload) throws Exception {
            System.out.println("Topic: " + topic);
            if (topic != null && topic.startsWith(MqttSetting.TOPIC_PRODUCT_OF_AGENCY)) {
                ProductOfAgencyResponse[] products = JacksonHelper.getInstance().getObjectMapper().readValue(payload, ProductOfAgencyResponse[].class);
                productOfBillAdapter.setProducts(products);
            }
        }

        @Override
        public void OnConnectionLost() {

        }
    };

    private View.OnClickListener btnBillReturnedClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.BILL_RETURNED_INFORMATION, billResponse.getCustomerId(), billResponse.getBillId());
        }
    };

    private RadioGroup.OnCheckedChangeListener billStepCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int stepId = 0;
            switch (checkedId) {
                case R.id.bill_step_propose:
                    stepId = AppSetting.BILL_STEP_PROPOSE_ID;
                    break;
                case R.id.bill_step_delivery:
                    stepId = AppSetting.BILL_STEP_DELIVERY_ID;
                    break;
                case R.id.bill_step_received:
                    stepId = AppSetting.BILL_STEP_RECEIVED_ID;
                    break;
            }
            for (BillStepResponse item : billSteps) {
                if (item.getBillStepId().equals(stepId)) {
                    mSGBillStep.setTintColor(Color.parseColor(item.getBillStepColor()));
                    break;
                }
            }
        }
    };

    private OnBillPriceChanged billPriceChanged = new OnBillPriceChanged() {
        @Override
        public void OnBillItemPriceChange() {
            Long amount = 0L;
            for (BillDetailResponse item : billResponse.getBillDetails()) {
                amount += item.getProductPrice() * item.getProductQuantity();
            }
            billResponse.setBillPrice(amount);

            amount = amount - billResponse.getBillRefundCost();
            mTVBillPrice.setText(StringHelper.ConvertToVN(amount, "VND"));
        }
    };

    private View.OnClickListener doneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BillRequest request = InitRequest();
            if (!checkOnPreExecuteRequest(request)) return;

            clearErrorView();
            final ProgressDialog mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.update), Boolean.FALSE);

            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<UpdateBillResponse>() {
                @Override
                public void OnResult(boolean success, UpdateBillResponse obj) {
                    if (success && obj.code == AppSetting.SUCCESS_CODE) {
                        billResponse.setBillId(obj.getBillId());
                        billResponse.setBillCode(obj.getBillCode());
                        mEDTBillCode.setText(billResponse.getBillCode());

                        for (BillDetailResponse item : billResponse.getBillDetails()) {
                            for (UpdateBillDetailResponse response : obj.getBillDetails()) {
                                if (item.getUid().equals(item.getUid())) {
                                    item.setBillDetailId(response.getBillDetailId());
                                    break;
                                }
                            }
                        }
                    } else {
                        CreateAlertDialog(R.string.error_wrong_data_response);
                    }
                    productOfBillAdapter.notifyDataSetChanged();
                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                }
            });
            handler.execute(UrlEntity.E_BILL + UrlEntity.A_SAVE_BILL, UpdateBillResponse.class, request);
        }
    };


    private View.OnClickListener cancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.POP_BACK_STACK, (Object) null);
        }
    };

    private View.OnClickListener addProductClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mSSAgency.getItemSelected() == null) {
                Snackbar.make(v, "Please choice agency before add product!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return;
            }
            if (billResponse.getBillDetails() == null)
                billResponse.setBillDetails(new ArrayList<BillDetailResponse>());
            BillDetailResponse itemBillDetail = new BillDetailResponse();
            itemBillDetail.setIsActive(AppSetting.ACTIVE);
            billResponse.getBillDetails().add(itemBillDetail);
            productOfBillAdapter.notifyDataSetChanged(/*billResponse.getBillDetails().size()*/);
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
            for (WardResponse ward : wardSet) {
                if (ward.getDistrictId().equals(model.getId()))
                    wards.add(ward);
            }
            mSSWard.setItemsSource(wards);
            mSSWard.setItemSelected(null);
        }
    };


    private ItemSearchableSelected agencySearchableSelected = new ItemSearchableSelected() {
        @Override
        public void OnItemSelected(BaseSearchableModel model) throws Exception {
            // TODO: Clear and reload product of agency
            if (model == null) return;


            LoadProductOfAgency(model.getId());
        }
    };
}
