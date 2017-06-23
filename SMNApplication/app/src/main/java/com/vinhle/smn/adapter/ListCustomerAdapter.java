package com.vinhle.smn.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.handler.OnItemSelectionListener;
import com.vinhle.smn.model.response.CustomerSearchResponse;

/**
 * Created by VinhLe on 5/8/2017.
 */
public class ListCustomerAdapter extends RecyclerView.Adapter<ListCustomerAdapter.ViewHolder> {

    private CustomerSearchResponse[] customers;
    private OnItemSelectionListener itemCustomerSelectionListener;

    public ListCustomerAdapter(CustomerSearchResponse[] customers, OnItemSelectionListener itemCustomerSelectionListener) {
        this.customers = customers;
        this.itemCustomerSelectionListener = itemCustomerSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_customer, parent, Boolean.FALSE);
        // set the view's size, margins, paddings and layout parameters
        ListCustomerAdapter.ViewHolder vh = new ListCustomerAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return customers != null ? customers.length : 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CustomerSearchResponse itemResponse = customers[position];

        // TODO: Set full_name and customer_name for item
        holder.mTVCustomerName.setText(itemResponse.fullName);
        holder.mTVPhoneNumber.setText(itemResponse.phoneNumber);

        // TODO: Set itemClickListener for item
//        if (!holder.mBTNEditCustomer.hasOnClickListeners()) {
        holder.mBTNEditCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCustomerSelectionListener != null)
                    itemCustomerSelectionListener.OnEditItem(itemResponse);
            }
        });
//        }
//        if (!holder.mBTNCreateBill.hasOnClickListeners()) {
        holder.mBTNCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCustomerSelectionListener != null)
                    itemCustomerSelectionListener.OnAddBillForCustomer(itemResponse);
            }
        });
//        }
//        if (!holder.mBTNShowListInvoices.hasOnClickListeners()) {
        holder.mBTNShowListInvoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCustomerSelectionListener != null)
                    itemCustomerSelectionListener.OnShowListBillOfCustomer(itemResponse);
            }
        });
//        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTVCustomerName;
        TextView mTVPhoneNumber;

        ImageButton mBTNEditCustomer;
        ImageButton mBTNCreateBill;
        ImageButton mBTNShowListInvoices;

        public ViewHolder(View itemView) {
            super(itemView);

            mTVCustomerName = (TextView) itemView.findViewById(R.id.item_customer_full_name);
            mTVPhoneNumber = (TextView) itemView.findViewById(R.id.item_customer_phone_number);

            mBTNEditCustomer = (ImageButton) itemView.findViewById(R.id.item_edit_customer);
            mBTNCreateBill = (ImageButton) itemView.findViewById(R.id.item_create_bill);
            mBTNShowListInvoices = (ImageButton) itemView.findViewById(R.id.item_show_invoices);
        }
    }


}
