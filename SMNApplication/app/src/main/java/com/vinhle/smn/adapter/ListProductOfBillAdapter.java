package com.vinhle.smn.adapter;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.vinhle.searchablespinner.BaseSearchableModel;
import com.vinhle.searchablespinner.ItemSearchableSelected;
import com.vinhle.searchablespinner.SearchableSpinner;
import com.vinhle.smn.R;
import com.vinhle.smn.common.Converter;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillPriceChanged;
import com.vinhle.smn.model.response.BillDetailResponse;
import com.vinhle.smn.model.response.ProductOfAgencyResponse;
import com.vinhle.smn.model.shared.ResourceModel;
import com.vinhle.smn.setting.AppSetting;

import java.util.Arrays;
import java.util.List;

/**
 * Created by VinhLe on 5/12/2017.
 */
public class ListProductOfBillAdapter extends RecyclerView.Adapter<ListProductOfBillAdapter.ViewHolder> {

    private List<BillDetailResponse> billDetails;
    private ProductOfAgencyResponse[] products;

    private OnBillPriceChanged billPriceChanged;

    public ListProductOfBillAdapter(List<BillDetailResponse> billDetails, OnBillPriceChanged billPriceChanged) {
        this.billDetails = billDetails;
        this.billPriceChanged = billPriceChanged;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (billDetails != null) {
            for (BillDetailResponse detail : billDetails) {
                count = detail.getIsActive() > 0 ? count + 1 : count;
            }
        }
        return count;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_of_bill, parent, Boolean.FALSE);
        // set the view's size, margins, paddings and layout parameters
        ListProductOfBillAdapter.ViewHolder vh = new ListProductOfBillAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BillDetailResponse itemResponse = GetItemActive(position);

        int indexOfBillDetail = billDetails.indexOf(itemResponse);
//        holder.setIsRecyclable(Boolean.FALSE);

        holder.edtQuantityListener.updatePosition(indexOfBillDetail);
        holder.edtPriceUnitListener.updatePosition(indexOfBillDetail);
        holder.edtNoteListener.updatePosition(indexOfBillDetail);
        holder.btnBillDetailReturned.updatePosition(indexOfBillDetail);

        // TODO: Set data
        holder.mSSProduct.setEditable(Boolean.TRUE);
        if (products != null) holder.mSSProduct.setItemsSource(Arrays.asList(products));
        if (itemResponse.getBillDetailId() != null) holder.mSSProduct.setEditable(Boolean.FALSE);
        holder.mSSProduct.setItemSelected(itemResponse.getProduct());
        holder.mEDTPriceUnit.setText(String.valueOf(itemResponse.getProductPrice()));
        holder.mEDTQuantity.setText(String.valueOf(itemResponse.getProductQuantity()));
        holder.mEDTTotalPrice.setText(StringHelper.ConvertToVN((itemResponse.getProductPrice() * itemResponse.getProductQuantity()), "VND"));
        holder.mEDTNote.setText(itemResponse.getDescription());

        holder.mEDTQuantityReturned.setText(String.valueOf(itemResponse.getProductReturnedQuantity()));
        holder.mEDTReturnedCostUnit.setText(String.valueOf(itemResponse.getRefundCost()));
        holder.mEDTReturnedCost.setText(StringHelper.ConvertToVN((itemResponse.getProductReturnedQuantity() * itemResponse.getRefundCost()), "VND"));

        holder.mEPLBillReturnedDetail.setExpanded(itemResponse.getBtnBillReturnedDetail());

//        holder.mRLBillReturnedDetail.setRotation(itemResponse.getBtnBillReturnedDetail() ? 180f : 0f);

        holder.mSSProduct.setItemSearchableSelected(new ItemSearchableSelected() {
            @Override
            public void OnItemSelected(BaseSearchableModel model) throws Exception {
                if (model instanceof ProductOfAgencyResponse) {
                    holder.mEDTPriceUnit.setText(String.valueOf(((ProductOfAgencyResponse) model).getProductPrice()));
                }

                if (itemResponse.getProduct() == null) {
                    itemResponse.setProduct(new ResourceModel());
                }
                // Update model of product itemResponse
                itemResponse.getProduct().setId(model.getId());
                itemResponse.getProduct().setName(model.getDisplayText());
            }
        });
        holder.mTVRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemResponse.getBillDetailId() != null) {
                    itemResponse.setProductQuantity(0);
                    itemResponse.setIsActive(AppSetting.DEACTIVATE);
                } else billDetails.remove(itemResponse);
                notifyItemRemoved(position);
                if (billPriceChanged != null) billPriceChanged.OnBillItemPriceChange();
            }
        });
    }

    public void setProducts(ProductOfAgencyResponse[] products) {
        this.products = products;
        notifyDataSetChanged();
    }

    private BillDetailResponse GetItemActive(int position) {
        int counter = 0;
        for (int index = 0, max = billDetails.size(); index < max; index++) {
            BillDetailResponse itemResponse = billDetails.get(index);
            if (itemResponse.getIsActive() < 1) continue;
            if (counter == position) return itemResponse;
            counter++;
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SearchableSpinner mSSProduct;
        EditText mEDTQuantity;
        EditText mEDTPriceUnit;
        EditText mEDTTotalPrice;
        TextView mTVRemove;
        EditText mEDTNote;
        EditText mEDTQuantityReturned;
        EditText mEDTReturnedCostUnit;
        EditText mEDTReturnedCost;
        ExpandableLayout mEPLBillReturnedDetail;
        TextView mTVBillReturnedDetail;
        RelativeLayout mRLBillReturnedDetail;

        // Listener of EditText
        public EDTQuantityListener edtQuantityListener;
        public EDTPriceUnitListener edtPriceUnitListener;
        public EDTNoteListener edtNoteListener;
        public BTNBillDetailReturned btnBillDetailReturned;

        public ViewHolder(View itemView) {
            super(itemView);
            mSSProduct = (SearchableSpinner) itemView.findViewById(R.id.item_product);
            mEDTQuantity = (EditText) itemView.findViewById(R.id.item_quantity);
            mEDTPriceUnit = (EditText) itemView.findViewById(R.id.item_price_unit);
            mEDTTotalPrice = (EditText) itemView.findViewById(R.id.item_total_price);
            mEDTNote = (EditText) itemView.findViewById(R.id.item_note);
            mTVRemove = (TextView) itemView.findViewById(R.id.btn_remove);
            mEPLBillReturnedDetail = (ExpandableLayout) itemView.findViewById(R.id.expand_bill_returned);
            mTVBillReturnedDetail = (TextView) itemView.findViewById(R.id.btn_returned_detail);
            mRLBillReturnedDetail = (RelativeLayout) itemView.findViewById(R.id.btn_arrow_returned_detail);
            mEDTQuantityReturned = (EditText) itemView.findViewById(R.id.item_returned_quantity);
            mEDTReturnedCostUnit = (EditText) itemView.findViewById(R.id.item_refund_cost_unit);
            mEDTReturnedCost = (EditText) itemView.findViewById(R.id.item_refund_cost);

//            mEPLBillReturnedDetail.setInRecyclerView(Boolean.TRUE);

            edtQuantityListener = new EDTQuantityListener();
            edtPriceUnitListener = new EDTPriceUnitListener();
            edtNoteListener = new EDTNoteListener();
            btnBillDetailReturned = new BTNBillDetailReturned();

            edtQuantityListener.edtPreference = mEDTTotalPrice;
            edtPriceUnitListener.edtPreference = mEDTTotalPrice;
            btnBillDetailReturned.relativeLayout = mRLBillReturnedDetail;
            btnBillDetailReturned.expandableLinearLayout = mEPLBillReturnedDetail;

            mEDTQuantity.addTextChangedListener(edtQuantityListener);
            mEDTPriceUnit.addTextChangedListener(edtPriceUnitListener);
            mEDTNote.addTextChangedListener(edtNoteListener);
            mTVBillReturnedDetail.setOnClickListener(btnBillDetailReturned);
            mRLBillReturnedDetail.setOnClickListener(btnBillDetailReturned);
            mEPLBillReturnedDetail.setListener(new ExpandableLayoutListenerAdapter() {
                @Override
                public void onPreOpen() {
                    createRotateAnimator(mRLBillReturnedDetail, 0f, 180f).start();
                }

                @Override
                public void onPreClose() {
                    createRotateAnimator(mRLBillReturnedDetail, 180f, 0f).start();
                }
            });

//            mEPLBillReturnedDetail.initLayout();
        }
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    // we make TextWatcher to be aware of the position it currently works with
    // this way, once a new item is attached in onBindViewHolder, it will
    // update current position EDTQuantityListener, EDTPriceUnitListener, EDTNoteListener
    // reference to which is kept by ViewHolder
    private class EDTQuantityListener implements TextWatcher {

        private int position;
        private EditText edtPreference;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (position < 0 || position > billDetails.size()) return;
            BillDetailResponse item = billDetails.get(position);
            item.setProductQuantity(Converter.ConvertToInt(s.toString()));
            edtPreference.setText(StringHelper.ConvertToVN((item.getProductPrice() * item.getProductQuantity()), "VND"));
            if (billPriceChanged != null) billPriceChanged.OnBillItemPriceChange();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    private class EDTPriceUnitListener implements TextWatcher {

        private int position;
        private EditText edtPreference;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (position < 0 || position > billDetails.size()) return;
            BillDetailResponse item = billDetails.get(position);
            item.setProductPrice(Converter.ConvertToLong(s.toString()));
            edtPreference.setText(StringHelper.ConvertToVN((item.getProductPrice() * item.getProductQuantity()), "VND"));
            if (billPriceChanged != null) billPriceChanged.OnBillItemPriceChange();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    private class EDTNoteListener implements TextWatcher {

        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (position < 0 || position > billDetails.size()) return;
            BillDetailResponse item = billDetails.get(position);
            item.setDescription(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class BTNBillDetailReturned implements View.OnClickListener {

        private int position;
        private ExpandableLayout expandableLinearLayout;
        private RelativeLayout relativeLayout;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (position < 0 || position > billDetails.size()) return;

            BillDetailResponse item = billDetails.get(position);
            item.setBtnBillReturnedDetail(!item.getBtnBillReturnedDetail());

            expandableLinearLayout.toggle();
        }
    }
}
