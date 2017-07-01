package com.vinhle.smn.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
import com.vinhle.searchablespinner.BaseSearchableModel;
import com.vinhle.searchablespinner.SearchableSpinner;
import com.vinhle.smn.LoginActivity;
import com.vinhle.smn.R;
import com.vinhle.smn.adapter.ListAgencyChoiceAdapter;
import com.vinhle.smn.common.Converter;
import com.vinhle.smn.common.SharedPreferenceHelper;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnAgencySelectionListener;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.CompositedIdProductAgencyRequest;
import com.vinhle.smn.model.request.ProductIdRequest;
import com.vinhle.smn.model.request.ProductOfAgencyRequest;
import com.vinhle.smn.model.request.ProductRequest;
import com.vinhle.smn.model.response.AgencyResponse;
import com.vinhle.smn.model.response.ApiResponse;
import com.vinhle.smn.model.response.ProductResponse;
import com.vinhle.smn.model.response.ProductTypeResponse;
import com.vinhle.smn.model.response.UpdateProductResponse;
import com.vinhle.smn.model.shared.ProductOfAgencyModel;
import com.vinhle.smn.model.shared.ResourceModel;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

import java.util.EnumMap;
import java.util.Map;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by VinhLe on 5/11/2017.
 */

public class ProductInformationFragment extends BaseFragment {

    private static final int COLUMNS = 3;

    private TextView mTVCancel;
    private TextView mTVDone;
    private ImageView mIVBarCode;
    private EditText mEDTProductCode;
    private EditText mEDTProductName;
    private EditText mEDTProductSize;
    private EditText mEDTProductPrice;
    private EditText mEDTProductCostImport;
    private EditText mEDTProductCodeOrder;
    private EditText mEDTProductDescription;
    private SegmentedGroup mSGProductActive;
    private SearchableSpinner mSSProductType;
    private RecyclerView mRVAgencies;
    private ProgressDialog mPGDWaiting;

    private ListAgencyChoiceAdapter listAgencyChoiceAdapter;

    private int productId;
    private ProductResponse productResponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_information_product, container, Boolean.FALSE);

        mTVCancel = (TextView) root.findViewById(R.id.btn_cancel);
        mTVDone = (TextView) root.findViewById(R.id.btn_done);
        mIVBarCode = (ImageView) root.findViewById(R.id.item_barcode);
        mEDTProductCode = (EditText) root.findViewById(R.id.item_product_code);
        mEDTProductName = (EditText) root.findViewById(R.id.item_product_name);
        mEDTProductSize = (EditText) root.findViewById(R.id.item_product_size);
        mEDTProductPrice = (EditText) root.findViewById(R.id.item_product_price);
        mEDTProductCostImport = (EditText) root.findViewById(R.id.item_product_cost_import);
        mEDTProductCodeOrder = (EditText) root.findViewById(R.id.item_product_cost_order);
        mEDTProductDescription = (EditText) root.findViewById(R.id.item_product_description);
        mSSProductType = (SearchableSpinner) root.findViewById(R.id.spinner_product_type);
        mSGProductActive = (SegmentedGroup) root.findViewById(R.id.item_product_active);
        mRVAgencies = (RecyclerView) root.findViewById(R.id.list_agency);
        mRVAgencies.setLayoutManager(new GridLayoutManager(getContext(), COLUMNS));

        try {
            listAgencyChoiceAdapter = new ListAgencyChoiceAdapter(SharedPreferenceHelper.getAgencies(getContext()), agencySelectionListener);
            mRVAgencies.setAdapter(listAgencyChoiceAdapter);
            mSSProductType.setItemsSource(Converter.Convert(SharedPreferenceHelper.getProductTypes(getContext())));
        } catch (Exception e) {
            Log.e("ProductInformation", e.getMessage(), e);
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            productId = bundle.getInt("product_id", 0);
        }

        if (savedInstanceState != null && (productResponse = (ProductResponse) savedInstanceState.getSerializable("product_information")) != null) {
            Log.i("HomeActivity", "Has savedInstanceState!");

        }

        mTVDone.setOnClickListener(doneClickListener);
        mTVCancel.setOnClickListener(cancelClickListener);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (productId < 1 || productResponse != null) {
            if (productResponse != null) {
                InitForm(productResponse);
                listAgencyChoiceAdapter.setAgencyIds(productResponse.getAgencyIds());
            }
            return;
        }
        mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
        OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<ProductResponse>() {
            @Override
            public void OnResult(boolean success, ProductResponse obj) {
                if (success && obj.code == AppSetting.SUCCESS_CODE) {
                    InitForm(obj);
                }
                if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
            }
        });
        ProductIdRequest request = new ProductIdRequest();
        request.setProductId(productId);
        request.setDeviceId(StringHelper.GetDeviceId(getContext()));
        handler.execute(UrlEntity.E_PRODUCT + UrlEntity.A_GET_PRODUCT_BY_ID, ProductResponse.class, productId);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            RestoreProductResponse();
            outState.putSerializable("product_information", productResponse);
        }
        super.onSaveInstanceState(outState);
    }

    private void InitForm(ProductResponse obj) {
//        mIVBarCode.setImageBitmap(generateBarCode("123", mIVBarCode.getMeasuredWidth(), mIVBarCode.getMeasuredHeight()));
        mEDTProductCode.setText(obj.getProductCode());
        mEDTProductName.setText(obj.getProductName());
        mEDTProductSize.setText(obj.getProductSize());
        mEDTProductPrice.setText(StringHelper.Convert(obj.getProductPrice()));
        mEDTProductCostImport.setText(StringHelper.Convert(obj.getCostOfImport()));
        mEDTProductCodeOrder.setText(StringHelper.Convert(obj.getCostOfOrder()));
        mEDTProductDescription.setText(obj.getDescription());
        mSSProductType.setItemSelected(obj.getProductType());
        mSGProductActive.check(obj.getIsActive() == AppSetting.ACTIVE ? R.id.active : R.id.deactivate);

        productResponse = obj;
        listAgencyChoiceAdapter.setAgencyIds(productResponse.getAgencyIds());
    }

    private void RestoreProductResponse() {
        if (productResponse == null)
            productResponse = new ProductResponse();
        productResponse.setProductCode(mEDTProductCode.getText().toString());
        productResponse.setProductName(mEDTProductName.getText().toString());
        productResponse.setProductSize(mEDTProductSize.getText().toString());
        productResponse.setProductPrice(Converter.ConvertToLong(mEDTProductPrice.getText().toString()));
        productResponse.setCostOfImport(Converter.ConvertToLong(mEDTProductPrice.getText().toString()));
        productResponse.setCostOfOrder(Converter.ConvertToLong(mEDTProductPrice.getText().toString()));
        productResponse.setDescription(mEDTProductDescription.getText().toString());
//               productRequest.gender = new ResourceModel(mSGGender.getCheckedRadioButtonId() == R.id.customer_gender_male ? AppSetting.GENDER_MALE_ID : AppSetting.GENDER_FEMALE_ID, (String) null);
        productResponse.setIsActive(mSGProductActive.getCheckedRadioButtonId() == R.id.active ? AppSetting.ACTIVE : AppSetting.DEACTIVATE);
        // TODO: init product type
        BaseSearchableModel productType = mSSProductType.getItemSelected();
        if (productType != null) {
            if (productType instanceof ResourceModel)
                productResponse.setProductType((ResourceModel) productType);
            else if (productType instanceof ProductTypeResponse)
                productResponse.setProductType(new ResourceModel(((ProductTypeResponse) productType).getProductTypeId(), ((ProductTypeResponse) productType).getProductTypeName(), (String) null, ((ProductTypeResponse) productType).getProductTypeNotation()));
        }
    }

    private ProductRequest InitProductRequest() {
        RestoreProductResponse();

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId(productResponse.getProductId());
        productRequest.setProductCode(productResponse.getProductCode());
        productRequest.setProductName(productResponse.getProductName());
        productRequest.setProductSize(productResponse.getProductSize());
        productRequest.setProductType(productResponse.getProductType());
        productRequest.setProductPrice(productResponse.getProductPrice());
        productRequest.setCostOfImport(productResponse.getCostOfImport());
        productRequest.setCostOfOrder(productResponse.getCostOfOrder());
        productRequest.setDescription(productResponse.getDescription());
//               productRequest.gender = new ResourceModel(mSGGender.getCheckedRadioButtonId() == R.id.customer_gender_male ? AppSetting.GENDER_MALE_ID : AppSetting.GENDER_FEMALE_ID, (String) null);
        productRequest.setIsActive(mSGProductActive.getCheckedRadioButtonId() == R.id.active ? AppSetting.ACTIVE : AppSetting.DEACTIVATE);

        return productRequest;
    }

    private void ShowAgencyProduct(final ProductOfAgencyModel model, final AgencyResponse agency) {
        final LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final View dialogContentView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_product_of_agnecy, linearLayout, Boolean.FALSE);
        final EditText edProductBeginningQuantity = (EditText) dialogContentView.findViewById(R.id.item_product_beginning_quantity);
        final EditText edProductQuantity = (EditText) dialogContentView.findViewById(R.id.item_product_quantity);
        final SegmentedGroup ssActive = (SegmentedGroup) dialogContentView.findViewById(R.id.item_product_active);

        if (model != null) {
            edProductBeginningQuantity.setFocusable(Boolean.TRUE);
            edProductQuantity.setText(String.valueOf(model.getProductQuantity()));
            edProductBeginningQuantity.setText(String.valueOf(model.getProductBeginningQuantity()));
            ssActive.check(model.getIsActive() == AppSetting.ACTIVE ? R.id.active : R.id.deactivate);
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        linearLayout.addView(dialogContentView);
        builder.setView(linearLayout);
        builder.setTitle(agency.getAgencyName());
        builder.setCancelable(Boolean.FALSE);

        builder.setNegativeButton(R.string.done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                if (model == null && ssActive.getCheckedRadioButtonId() == R.id.deactivate) return;

                // TODO: Init request save item
                ProductOfAgencyRequest request = new ProductOfAgencyRequest();
                request.setDeviceId(StringHelper.GetDeviceId(getContext()));
                request.setAgencyProductId(model != null ? model.getAgencyProductId() : null);
                request.setProductId(productResponse.getProductId());
                request.setAgencyId(agency.getAgencyId());
                request.setProductQuantity(Converter.ConvertToInt(edProductQuantity.getText().toString()));
                request.setProductBeginningQuantity(Converter.ConvertToInt(edProductBeginningQuantity.getText().toString()));
                request.setIsActive(ssActive.getCheckedRadioButtonId() == R.id.active ? AppSetting.ACTIVE : AppSetting.DEACTIVATE);

                // TODO: Execute request
                mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
                OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<ApiResponse>() {
                    @Override
                    public void OnResult(boolean success, final ApiResponse result) {
                        if (success) {
                            if (ssActive.getCheckedRadioButtonId() == R.id.active && !productResponse.getAgencyIds().contains(agency.getAgencyId()))
                                productResponse.getAgencyIds().add(agency.getAgencyId());
                            else if (ssActive.getCheckedRadioButtonId() == R.id.deactivate)
                                productResponse.getAgencyIds().remove(agency.getAgencyId());
                            listAgencyChoiceAdapter.notifyDataSetChanged();
                        }
                        if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                        dialog.dismiss();
                    }
                });
                handler.execute(UrlEntity.E_PRODUCT + UrlEntity.A_SAVE_PRODUCT_OF_AGENCY, ApiResponse.class, request);
            }
        });
        builder.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
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

    /*----------------------------------- Method Action Listener ProductInformationFragment ----------------------------------------------------*/
    private OnAgencySelectionListener agencySelectionListener = new OnAgencySelectionListener() {
        @Override
        public void OnAgencySelected(final AgencyResponse agency) {
            mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<ProductOfAgencyModel>() {
                @Override
                public void OnResult(boolean success, final ProductOfAgencyModel result) {
                    if (result != null && result.getAgencyProductId() != null)
                        ShowAgencyProduct(result, agency);
                    else ShowAgencyProduct(null, agency);

                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                }
            });
            CompositedIdProductAgencyRequest request = new CompositedIdProductAgencyRequest(productResponse.getProductId(), agency.getAgencyId());
            handler.execute(UrlEntity.E_PRODUCT + UrlEntity.A_GET_PRODUCT_OF_AGENCY, ProductOfAgencyModel.class, request);
        }
    };

    private View.OnClickListener doneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPGDWaiting = ProgressDialog.show(getContext(), getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);
            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<UpdateProductResponse>() {
                @Override
                public void OnResult(boolean success, UpdateProductResponse result) {
                    if (success && result.code == AppSetting.SUCCESS_CODE) {
                        // Update UI barcode
                        productResponse.setProductId(result.getProductId());
                        productResponse.setProductCode(result.getProductCode());

                        mEDTProductCode.setText(result.getProductCode());
                    } else {
                        CreateAlertDialog(R.string.error_wrong_data_response);
                    }
                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                }
            });
            ProductRequest productRequest = InitProductRequest();
            handler.execute(UrlEntity.E_PRODUCT + UrlEntity.A_SAVE_PRODUCT, UpdateProductResponse.class, productRequest);
        }
    };

    private View.OnClickListener cancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onFragmentSelectionChanged != null)
                onFragmentSelectionChanged.OnFragment(AppSetting.Fragment.POP_BACK_STACK, null);
        }
    };

    /**************************************************************
     * getting from com.google.zxing.client.android.encode.QRCodeEncoder
     * <p>
     * See the sites below
     * http://code.google.com/p/zxing/
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/EncodeActivity.java
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/QRCodeEncoder.java
     */

//    private static final int WHITE = 0xFFFFFFFF;
//    private static final int BLACK = 0xFF000000;
//
//    public Bitmap generateBarCode(String contents, int img_width, int img_height) {
//        com.google.zxing.Writer c9 = new QRCodeWriter();
//        try {
//            BitMatrix bm = c9.encode(contents, BarcodeFormat.QR_CODE, img_width, img_height);
//            Bitmap bitmap = Bitmap.createBitmap(img_width, img_height, Bitmap.Config.ARGB_8888);
//
//            for (int i = 0; i < img_width; i++) {
//                for (int j = 0; j < img_height; j++) {
//                    bitmap.setPixel(i, j, bm.get(i, j) ? BLACK : WHITE);
//                }
//            }
//
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) {
//        try {
//            String contentsToEncode = contents;
//            if (contentsToEncode == null) {
//                return null;
//            }
//            Map<EncodeHintType, Object> hints = null;
//            String encoding = guessAppropriateEncoding(contentsToEncode);
//            if (encoding != null) {
//                hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
//                hints.put(EncodeHintType.CHARACTER_SET, encoding);
//            }
//            MultiFormatWriter writer = new MultiFormatWriter();
//            BitMatrix result;
//            try {
//                result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
//            } catch (IllegalArgumentException iae) {
//                // Unsupported format
//                return null;
//            }
//            int width = result.getWidth();
//            int height = result.getHeight();
//            int[] pixels = new int[width * height];
//            for (int y = 0; y < height; y++) {
//                int offset = y * width;
//                for (int x = 0; x < width; x++) {
//                    pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
//                }
//            }
//
//            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//            return bitmap;
//        } catch (WriterException e) {
//            return null;
//        }
//    }
//
//    private static String guessAppropriateEncoding(CharSequence contents) {
//        // Very crude at the moment
//        for (int i = 0; i < contents.length(); i++) {
//            if (contents.charAt(i) > 0xFF) {
//                return "UTF-8";
//            }
//        }
//        return null;
//    }
}
