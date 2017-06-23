package com.vinhle.smn.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnBillOfCustomerSelectionListener;
import com.vinhle.smn.model.response.BillSearchResponse;
import com.vinhle.smn.model.response.BillStepResponse;

import java.util.Date;

/**
 * Created by VinhLe on 5/18/2017.
 */

public class ListBillOfCustomerAdapter extends RecyclerView.Adapter<ListBillOfCustomerAdapter.ViewHolder> {

    private BillSearchResponse[] bills;
    private BillStepResponse[] billSteps;
    private OnBillOfCustomerSelectionListener billOfCustomerSelectionListener;

    public ListBillOfCustomerAdapter(BillSearchResponse[] bills, BillStepResponse[] billSteps, OnBillOfCustomerSelectionListener billOfCustomerSelectionListener) {
        this.bills = bills;
        this.billSteps = billSteps;
        this.billOfCustomerSelectionListener = billOfCustomerSelectionListener;
    }

    @Override
    public int getItemCount() {
        return bills != null ? bills.length : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill_of_customer, parent, Boolean.FALSE);
        // set the view's size, margins, paddings and layout parameters
        ListBillOfCustomerAdapter.ViewHolder vh = new ListBillOfCustomerAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BillSearchResponse itemResponse = bills[position];

        holder.mTVCustomerName.setText(itemResponse.getCustomerName());
        holder.mTVBillPrice.setText(StringHelper.ConvertToVN(itemResponse.getBillPrice(), "VND"));
        holder.mTVCreateDate.setText(StringHelper.Convert(new Date(itemResponse.getCreateDate().getTime())));
        if (billSteps != null) {
            for (BillStepResponse item : billSteps) {
                if (!itemResponse.getBillStepId().equals(item.getBillStepId())) continue;
                holder.mTVBillStep.setText(item.getBillStepText());
                GradientDrawable drawable = (GradientDrawable) holder.mTVBillStep.getBackground();
                drawable.setColor(Color.parseColor(item.getBillStepColor()));
                break;
            }
        }
//        if (!holder.mIBEdit.hasOnClickListeners()) {
        holder.mIBEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (billOfCustomerSelectionListener != null)
                    billOfCustomerSelectionListener.onBillOfCustomerSelection(itemResponse.getCustomerId(), itemResponse.getBillId());
            }
        });
//        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton mIBEdit;
        TextView mTVCustomerName;
        TextView mTVBillPrice;
        TextView mTVBillStep;
        TextView mTVCreateDate;

        public ViewHolder(View itemView) {
            super(itemView);

            mIBEdit = (ImageButton) itemView.findViewById(R.id.item_edit_bill);
            mTVCustomerName = (TextView) itemView.findViewById(R.id.item_bill_customer);
            mTVBillPrice = (TextView) itemView.findViewById(R.id.item_bill_price);
            mTVBillStep = (TextView) itemView.findViewById(R.id.item_bill_step_name);
            mTVCreateDate = (TextView) itemView.findViewById(R.id.item_bill_date);
        }
    }
}
