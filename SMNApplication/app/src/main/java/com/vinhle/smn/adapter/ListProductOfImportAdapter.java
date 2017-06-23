package com.vinhle.smn.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vinhle.searchablespinner.BaseSearchableModel;
import com.vinhle.searchablespinner.ItemSearchableSelected;
import com.vinhle.searchablespinner.SearchableSpinner;
import com.vinhle.smn.R;
import com.vinhle.smn.common.Converter;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillPriceChanged;
import com.vinhle.smn.model.response.ImportDetailResponse;
import com.vinhle.smn.model.response.ProductOfAgencyResponse;
import com.vinhle.smn.model.shared.ResourceModel;
import com.vinhle.smn.setting.AppSetting;

import java.util.Arrays;
import java.util.List;

/**
 * Created by VinhLe on 6/10/2017.
 */

public class ListProductOfImportAdapter extends RecyclerView.Adapter<ListProductOfImportAdapter.ViewHolder> {

    private ProductOfAgencyResponse[] products;
    private List<ImportDetailResponse> importDetails;
    private OnBillPriceChanged billPriceChanged;

    public ListProductOfImportAdapter(List<ImportDetailResponse> importDetails, OnBillPriceChanged billPriceChanged) {
        this.importDetails = importDetails;
        this.billPriceChanged = billPriceChanged;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_of_import, parent, Boolean.FALSE);
        // set the view's size, margins, paddings and layout parameters
        ListProductOfImportAdapter.ViewHolder vh = new ListProductOfImportAdapter.ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ImportDetailResponse itemResponse = GetItemActive(position);

        int indexOfBillDetail = importDetails.indexOf(itemResponse);

        holder.edtQuantityListener.updatePosition(indexOfBillDetail);
        holder.edtCostUnitListener.updatePosition(indexOfBillDetail);
        holder.edtNoteListener.updatePosition(indexOfBillDetail);

        // TODO: Set data
        holder.mSSProduct.setEditable(Boolean.TRUE);
        if (products != null) holder.mSSProduct.setItemsSource(Arrays.asList(products));
        if (itemResponse.getImportDetailId() != null) holder.mSSProduct.setEditable(Boolean.FALSE);

        holder.mSSProduct.setItemSelected(itemResponse.getProduct());
        holder.mEDTCostUnit.setText(String.valueOf(itemResponse.getProductCost()));
        holder.mEDTQuantity.setText(String.valueOf(itemResponse.getProductQuantity()));
        holder.mEDTCost.setText(StringHelper.ConvertToVN((itemResponse.getProductCost() * itemResponse.getProductQuantity()), "VND"));
        holder.mEDTNote.setText(itemResponse.getDescription());

        holder.mSSProduct.setItemSearchableSelected(new ItemSearchableSelected() {
            @Override
            public void OnItemSelected(BaseSearchableModel model) throws Exception {
                if (model instanceof ProductOfAgencyResponse) {
                    holder.mEDTCostUnit.setText(String.valueOf(((ProductOfAgencyResponse) model).getProductCost()));
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
                if (itemResponse.getImportDetailId() != null) {
                    itemResponse.setProductQuantity(0);
                    itemResponse.setIsActive(AppSetting.DEACTIVATE);
                } else importDetails.remove(itemResponse);
                notifyItemRemoved(position);
                if (billPriceChanged != null) billPriceChanged.OnBillItemPriceChange();
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (importDetails != null) {
            for (ImportDetailResponse detail : importDetails) {
                count = detail.getIsActive() > 0 ? count + 1 : count;
            }
        }
        return count;
    }

    public void setProducts(ProductOfAgencyResponse[] products) {
        this.products = products;
        notifyDataSetChanged();
    }

    private ImportDetailResponse GetItemActive(int position) {
        int counter = 0;
        for (int index = 0, max = importDetails.size(); index < max; index++) {
            ImportDetailResponse itemResponse = importDetails.get(index);
            if (itemResponse.getIsActive() < 1) continue;
            if (counter == position) return itemResponse;
            counter++;
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SearchableSpinner mSSProduct;
        EditText mEDTQuantity;
        EditText mEDTCostUnit;
        EditText mEDTCost;
        TextView mTVRemove;
        EditText mEDTNote;

        // Listener of EditText
        public EDTQuantityListener edtQuantityListener;
        public EDTCostUnitListener edtCostUnitListener;
        public EDTNoteListener edtNoteListener;

        public ViewHolder(View itemView) {
            super(itemView);

            mSSProduct = (SearchableSpinner) itemView.findViewById(R.id.item_product);
            mEDTQuantity = (EditText) itemView.findViewById(R.id.item_quantity);
            mEDTCostUnit = (EditText) itemView.findViewById(R.id.item_cost_unit);
            mEDTCost = (EditText) itemView.findViewById(R.id.item_cost);
            mEDTNote = (EditText) itemView.findViewById(R.id.item_note);
            mTVRemove = (TextView) itemView.findViewById(R.id.btn_remove);

            edtQuantityListener = new EDTQuantityListener();
            edtCostUnitListener = new EDTCostUnitListener();
            edtNoteListener = new EDTNoteListener();

            edtQuantityListener.edtPreference = mEDTCost;
            edtCostUnitListener.edtPreference = mEDTCost;

            mEDTQuantity.addTextChangedListener(edtQuantityListener);
            mEDTCostUnit.addTextChangedListener(edtCostUnitListener);
            mEDTNote.addTextChangedListener(edtNoteListener);
        }
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
            if (position < 0 || position > importDetails.size()) return;
            ImportDetailResponse item = importDetails.get(position);
            item.setProductQuantity(Converter.ConvertToInt(s.toString()));
            edtPreference.setText(StringHelper.ConvertToVN((item.getProductCost() * item.getProductQuantity()), "VND"));
            if (billPriceChanged != null) billPriceChanged.OnBillItemPriceChange();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    private class EDTCostUnitListener implements TextWatcher {

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
            if (position < 0 || position > importDetails.size()) return;
            ImportDetailResponse item = importDetails.get(position);
            item.setProductCost(Converter.ConvertToLong(s.toString()));
            edtPreference.setText(StringHelper.ConvertToVN((item.getProductCost() * item.getProductQuantity()), "VND"));
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
            if (position < 0 || position > importDetails.size()) return;
            ImportDetailResponse item = importDetails.get(position);
            item.setDescription(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
