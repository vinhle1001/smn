package com.vinhle.smn.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.vinhle.smn.adapter.ListProductOfImportAdapter;
import com.vinhle.smn.annotation.RequestPropertyChecker;
import com.vinhle.smn.annotation.RequestPropertyException;
import com.vinhle.smn.common.Converter;
import com.vinhle.smn.common.SharedPreferenceHelper;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillPriceChanged;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.AgencyIdRequest;
import com.vinhle.smn.model.request.CompositedIdSupplierImportRequest;
import com.vinhle.smn.model.request.ImportDetailRequest;
import com.vinhle.smn.model.request.ImportRequest;
import com.vinhle.smn.model.response.ImportDetailResponse;
import com.vinhle.smn.model.response.ImportResponse;
import com.vinhle.smn.model.response.ProductOfAgencyResponse;
import com.vinhle.smn.model.response.UpdateBillResponse;
import com.vinhle.smn.model.response.UpdateImportDetailResponse;
import com.vinhle.smn.model.response.UpdateImportResponse;
import com.vinhle.smn.model.shared.ResourceModel;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhLe on 6/11/2017.
 */
public class ImportInformationFragment extends BaseFragment {

    private TextView mTVCancel;
    private TextView mTVDone;
    private TextView mTVAddProduct;
    private TextView mTVImportCost;
    private EditText mEDTSupplierName;
    private EditText mEDTSupplierPhone;
    private SearchableSpinner mSSAgency;
    private RecyclerView mRCVListProduct;
    private EditText mEDImportTNote;

    private ListProductOfImportAdapter productOfImportAdapter;
    private ImportResponse importResponse;

    private Integer importId;
    private Integer supplierId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_information_import, container, Boolean.FALSE);

        mTVCancel = (TextView) root.findViewById(R.id.btn_cancel);
        mTVDone = (TextView) root.findViewById(R.id.btn_done);
        mTVAddProduct = (TextView) root.findViewById(R.id.btn_add_product);
        mTVImportCost = (TextView) root.findViewById(R.id.import_cost);
        mEDTSupplierName = (EditText) root.findViewById(R.id.item_supplier);
        mEDTSupplierPhone = (EditText) root.findViewById(R.id.item_supplier_phone);
        mSSAgency = (SearchableSpinner) root.findViewById(R.id.spinner_store);
        mRCVListProduct = (RecyclerView) root.findViewById(R.id.list_product);
        mEDImportTNote = (EditText) root.findViewById(R.id.item_import_note);

        mRCVListProduct.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            mSSAgency.setItemsSource(Converter.Convert(SharedPreferenceHelper.getAgencies(getContext())));
            mSSAgency.setItemSearchableSelected(agencySearchableSelected);
        } catch (Exception e) {
            Log.e("ImportInformation", e.getMessage(), e);
        }

        if (savedInstanceState != null && (importResponse = (ImportResponse) savedInstanceState.getSerializable("import_information")) != null) {
            Log.i("HomeActivity", "Has savedInstanceState!" + importResponse);
            savedInstanceState.remove("import_information");
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            supplierId = bundle.getInt("supplier_id", 0);
            importId = bundle.getInt("import_id", 0);
        }

        mTVDone.setOnClickListener(doneClickListener);
        mTVCancel.setOnClickListener(cancelClickListener);
        mTVAddProduct.setOnClickListener(addProductClickListener);

        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        clearErrorView();
        if (outState != null) {
            RestoreImportResponse();
            outState.putSerializable("import_information", importResponse);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (supplierId < 1 || importResponse != null) {
            if (importResponse != null)
                InitForm(importResponse);
            return;
        }

        final ProgressDialog mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<ImportResponse>() {
            @Override
            public void OnResult(boolean success, ImportResponse obj) {
                if (success && obj.code == AppSetting.SUCCESS_CODE) {
                    InitForm(obj);
                }
                if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
            }
        });
        CompositedIdSupplierImportRequest request = new CompositedIdSupplierImportRequest();
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        request.setImportId(importId);
        request.setSupplierId(supplierId);
        handler.execute(UrlEntity.E_SUPPLIER + UrlEntity.A_GET_IMPORT_BY_COMPOSITED_ID, ImportResponse.class, request);
    }

    private void LoadProductOfAgency(Integer agencyId) {
        if (agencyId == null || agencyId.equals(0)) return;

        final ProgressDialog mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<ProductOfAgencyResponse[]>() {
            @Override
            public void OnResult(boolean success, ProductOfAgencyResponse[] obj) {
                if (success) {
                    productOfImportAdapter.setProducts(obj);
                } else {
                    CreateAlertDialog(R.string.error_wrong_data_response);
                }
                if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
            }
        });
        AgencyIdRequest request = new AgencyIdRequest();
        request.setAgencyId(agencyId);
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        handler.execute(UrlEntity.E_PRODUCT + UrlEntity.A_GET_ALL_PRODUCT_OF_AGENCY, ProductOfAgencyResponse[].class, request);
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

    private void RestoreImportResponse() {
        importResponse.setAgency(mSSAgency.getItemSelected() != null ? new ResourceModel(mSSAgency.getItemSelected().getId(), mSSAgency.getItemSelected().getDisplayText()) : null);
        importResponse.setDescription(mEDImportTNote.getText().toString());
        importResponse.setIsActive(AppSetting.ACTIVE);
    }

    private void InitForm(ImportResponse obj) {
        importResponse = obj;

        mEDTSupplierName.setText(obj.getSupplierName());
        mEDTSupplierPhone.setText(obj.getSupplierPhone());
        mSSAgency.setItemSelected(obj.getAgency());

        if (importResponse.getImportDetails() == null)
            importResponse.setImportDetails(new ArrayList<ImportDetailResponse>());
        productOfImportAdapter = new ListProductOfImportAdapter(importResponse.getImportDetails(), billPriceChanged);
        mRCVListProduct.setAdapter(productOfImportAdapter);

        mTVImportCost.setText(StringHelper.ConvertToVN(obj.getImportCost(), "VND"));
        mEDImportTNote.setText(obj.getDescription());
        mSSAgency.setEditable(obj.getImportId() == null);

        LoadProductOfAgency(obj.getAgency() != null ? obj.getAgency().getId() : null);
    }

    private ImportRequest InitRequest() {
        RestoreImportResponse();

        ImportRequest importRequest = new ImportRequest();
        importRequest.setDeviceId(StringHelper.GetDeviceId(getContext()));
        importRequest.setImportId(importResponse.getImportId());
        importRequest.setSupplierId(importResponse.getSupplierId());
        importRequest.setAgencyId(importResponse.getAgency() != null ? importResponse.getAgency().getId() : null);
        importRequest.setImportCost(importResponse.getImportCost());
        importRequest.setImportDebt(importResponse.getImportDebt());
        importRequest.setDescription(importResponse.getDescription());
        List<ImportDetailRequest> importDetails = new ArrayList<>();
        for (ImportDetailResponse item : importResponse.getImportDetails()) {
            if (item.getProduct() == null) continue;
            ImportDetailRequest detailRequest = new ImportDetailRequest();
            detailRequest.setImportDetailId(item.getImportDetailId());
            detailRequest.setProductId(item.getProduct().getId());
            detailRequest.setProductCost(item.getProductCost());
            detailRequest.setProductQuantity(item.getProductQuantity());
            detailRequest.setDescription(item.getDescription());
            detailRequest.setIsActive(item.getIsActive());
            detailRequest.setUid(item.getUid());

            importDetails.add(detailRequest);
        }
        importRequest.setImportDetails(importDetails);
        importRequest.setIsActive(importResponse.getIsActive());
        return importRequest;
    }

    private void clearErrorView() {
        mSSAgency.setError(null);
    }

    private boolean checkOnPreExecuteRequest(Object requestObj) {
        List<RequestPropertyException> exceptions = RequestPropertyChecker.GetErrorRequestProperty(requestObj);
        if (exceptions.size() == 0) return Boolean.TRUE;

        for (RequestPropertyException exception : exceptions) {
            if (exception.field != null && exception.field.startsWith("agency")) {
                mSSAgency.setErrorEnabled(Boolean.TRUE);
                mSSAgency.setError(exception.message);
            }
        }
        return Boolean.FALSE;
    }


    /*----------------------------------- Method Action Listener ImportInformationFragment ----------------------------------------------------*/
    private OnBillPriceChanged billPriceChanged = new OnBillPriceChanged() {
        @Override
        public void OnBillItemPriceChange() {
            Long amount = 0L;
            for (ImportDetailResponse item : importResponse.getImportDetails()) {
                amount += item.getProductCost() * item.getProductQuantity();
            }
            importResponse.setImportCost(amount);
            mTVImportCost.setText(StringHelper.ConvertToVN(amount, "VND"));
        }
    };

    private View.OnClickListener doneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImportRequest request = InitRequest();
            if (!checkOnPreExecuteRequest(request)) return;

            clearErrorView();
            final ProgressDialog mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.update), Boolean.FALSE);
            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<UpdateImportResponse>() {
                @Override
                public void OnResult(boolean success, UpdateImportResponse obj) {
                    if (success && obj.code == AppSetting.SUCCESS_CODE) {
                        importResponse.setImportId(obj.getImportId());
//                        billResponse.setBillCode(obj.getBillCode());
//                        mEDTBillCode.setText(billResponse.getBillCode());
                        for (ImportDetailResponse item : importResponse.getImportDetails()) {
                            for (UpdateImportDetailResponse response : obj.getImportDetails()) {
                                if (item.getUid().equals(item.getUid())) {
                                    item.setImportDetailId(response.getImportDetailId());
                                    break;
                                }
                            }
                        }
                    } else {
                        CreateAlertDialog(R.string.error_wrong_data_response);
                    }
                    productOfImportAdapter.notifyDataSetChanged();
                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                }
            });
            handler.execute(UrlEntity.E_SUPPLIER + UrlEntity.A_SAVE_IMPORT, UpdateImportResponse.class, request);
        }
    };

    private View.OnClickListener cancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.POP_BACK_STACK, null);
        }
    };

    private View.OnClickListener addProductClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mSSAgency.getItemSelected() == null) {
                Snackbar.make(v, "Please choice agency before add product!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return;
            }
            if (importResponse.getImportDetails() == null)
                importResponse.setImportDetails(new ArrayList<ImportDetailResponse>());
            ImportDetailResponse itemImportDetail = new ImportDetailResponse();
            itemImportDetail.setIsActive(AppSetting.ACTIVE);
            importResponse.getImportDetails().add(itemImportDetail);
            productOfImportAdapter.notifyDataSetChanged(/*billResponse.getBillDetails().size()*/);
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
