package com.vinhle.smn.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vinhle.smn.R;
import com.vinhle.smn.common.Converter;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillPriceChanged;
import com.vinhle.smn.model.response.BillReturnedDetailResponse;

import java.util.List;

/**
 * Created by VinhLe on 6/7/2017.
 */
public class ListProductReturnedOfBillAdapter extends RecyclerView.Adapter<ListProductReturnedOfBillAdapter.ViewHolder> {

    private List<BillReturnedDetailResponse> responses;
    private OnBillPriceChanged billPriceChanged;

    public ListProductReturnedOfBillAdapter(List<BillReturnedDetailResponse> responses, OnBillPriceChanged billPriceChanged) {
        this.responses = responses;
        this.billPriceChanged = billPriceChanged;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_returned_of_bill, parent, Boolean.FALSE);
        // set the view's size, margins, paddings and layout parameters
        ListProductReturnedOfBillAdapter.ViewHolder vh = new ListProductReturnedOfBillAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (responses != null) {
            for (BillReturnedDetailResponse detail : responses) {
                count = detail.getIsActive() > 0 ? count + 1 : count;
            }
        }
        return count;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BillReturnedDetailResponse itemResponse = GetItemActive(position);
        int indexOfBillDetail = responses.indexOf(itemResponse);

        holder.edtQuantityReturnedListener.updatePosition(indexOfBillDetail);
        holder.edtRefundUnitListener.updatePosition(indexOfBillDetail);
        holder.edtNoteListener.updatePosition(indexOfBillDetail);

        // TODO: Set data
        holder.mEDTProductName.setText(itemResponse.getProductName());
        holder.mEDTProductQuantity.setText(String.valueOf(itemResponse.getProductQuantity()));
        holder.mEDTProductQuantityReturned.setText(String.valueOf(itemResponse.getProductReturnedQuantity()));
        holder.mEDTProductRefundCostUnit.setText(StringHelper.ConvertToVN(itemResponse.getRefundCost(), "VND"));
        holder.mEDTProductReturnedNote.setText(itemResponse.getDescription());

        holder.mEDTProductRefundCost.setText(StringHelper.ConvertToVN((itemResponse.getRefundCost() * itemResponse.getProductReturnedQuantity()), "VND"));

    }

    private BillReturnedDetailResponse GetItemActive(int position) {
        int counter = 0;
        for (int index = 0, max = responses.size(); index < max; index++) {
            BillReturnedDetailResponse itemResponse = responses.get(index);
            if (itemResponse.getIsActive() < 1) continue;
            if (counter == position) return itemResponse;
            counter++;
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        EditText mEDTProductName;
        EditText mEDTProductQuantity;
        EditText mEDTProductQuantityReturned;
        EditText mEDTProductRefundCostUnit;
        EditText mEDTProductRefundCost;
        EditText mEDTProductReturnedNote;

        // Listener of EditText
        public EDTQuantityReturnedListener edtQuantityReturnedListener;
        public EDTRefundUnitListener edtRefundUnitListener;
        public EDTNoteListener edtNoteListener;

        public ViewHolder(View itemView) {
            super(itemView);

            mEDTProductName = (EditText) itemView.findViewById(R.id.item_product);
            mEDTProductQuantity = (EditText) itemView.findViewById(R.id.item_quantity);
            mEDTProductQuantityReturned = (EditText) itemView.findViewById(R.id.item_returned_quantity);
            mEDTProductRefundCostUnit = (EditText) itemView.findViewById(R.id.item_refund_unit);
            mEDTProductRefundCost = (EditText) itemView.findViewById(R.id.item_refund_cost);
            mEDTProductReturnedNote = (EditText) itemView.findViewById(R.id.item_note);

            edtQuantityReturnedListener = new EDTQuantityReturnedListener();
            edtRefundUnitListener = new EDTRefundUnitListener();
            edtNoteListener = new EDTNoteListener();

            edtQuantityReturnedListener.edtPreference = mEDTProductRefundCost;
            edtRefundUnitListener.edtPreference = mEDTProductRefundCost;

            mEDTProductQuantityReturned.addTextChangedListener(edtQuantityReturnedListener);
            mEDTProductRefundCostUnit.addTextChangedListener(edtRefundUnitListener);
            mEDTProductReturnedNote.addTextChangedListener(edtNoteListener);
        }
    }

    // we make TextWatcher to be aware of the position it currently works with
    // this way, once a new item is attached in onBindViewHolder, it will
    // update current position EDTQuantityListener, EDTPriceUnitListener, EDTNoteListener
    // reference to which is kept by ViewHolder
    private class EDTQuantityReturnedListener implements TextWatcher {

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
            if (position < 0 || position > responses.size()) return;

            BillReturnedDetailResponse item = responses.get(position);
            item.setProductReturnedQuantity(Converter.ConvertToInt(s.toString()));

            edtPreference.setText(StringHelper.ConvertToVN((item.getRefundCost() * item.getProductReturnedQuantity()), "VND"));
            if (billPriceChanged != null) billPriceChanged.OnBillItemPriceChange();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    private class EDTRefundUnitListener implements TextWatcher {

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
            if (position < 0 || position > responses.size()) return;

            BillReturnedDetailResponse item = responses.get(position);
            item.setRefundCost(Converter.ConvertToLong(s.toString()));

            edtPreference.setText(StringHelper.ConvertToVN((item.getRefundCost() * item.getProductReturnedQuantity()), "VND"));
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
            if (position < 0 || position > responses.size()) return;

            BillReturnedDetailResponse item = responses.get(position);
            item.setDescription(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
