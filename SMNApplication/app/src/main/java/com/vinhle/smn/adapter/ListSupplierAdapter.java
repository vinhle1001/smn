package com.vinhle.smn.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.handler.OnSupplierSelectionListener;
import com.vinhle.smn.model.response.SupplierSearchResponse;

/**
 * Created by VinhLe on 5/29/2017.
 */

public class ListSupplierAdapter extends RecyclerView.Adapter<ListSupplierAdapter.ViewHolder> {

    private SupplierSearchResponse[] suppliers;
    private OnSupplierSelectionListener itemCustomerSelectionListener;

    public ListSupplierAdapter(SupplierSearchResponse[] suppliers, OnSupplierSelectionListener itemCustomerSelectionListener) {
        this.suppliers = suppliers;
        this.itemCustomerSelectionListener = itemCustomerSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_supplier, parent, Boolean.FALSE);
        // set the view's size, margins, paddings and layout parameters
        ListSupplierAdapter.ViewHolder vh = new ListSupplierAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SupplierSearchResponse itemResponse = suppliers[position];
        // TODO: Set name and phoneNumber for item
        holder.mTVSupplierName.setText(itemResponse.getSupplierName());
        holder.mTVSupplierPhone.setText(itemResponse.getSupplierPhoneNumber());

        // TODO: Set itemClickListener for item
//        if (!holder.mBTNEditSupplier.hasOnClickListeners()) {
        holder.mBTNEditSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCustomerSelectionListener != null)
                    itemCustomerSelectionListener.OnEditItem(itemResponse);
            }
        });
//        }
//        if (!holder.mBTNCreateImport.hasOnClickListeners()) {
        holder.mBTNCreateImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCustomerSelectionListener != null)
                    itemCustomerSelectionListener.OnCreateImport(itemResponse);
            }
        });

//        }
        //        if (!holder.mBTNShowListInvoices.hasOnClickListeners()) {
        holder.mBTNShowListInvoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCustomerSelectionListener != null)
                    itemCustomerSelectionListener.OnShowInvoices(itemResponse);
            }
        });
    }

    @Override
    public int getItemCount() {
        return suppliers != null ? suppliers.length : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTVSupplierName;
        TextView mTVSupplierPhone;

        ImageButton mBTNEditSupplier;
        ImageButton mBTNCreateImport;
        ImageButton mBTNShowListInvoices;

        public ViewHolder(View itemView) {
            super(itemView);

            mTVSupplierName = (TextView) itemView.findViewById(R.id.item_supplier_name);
            mTVSupplierPhone = (TextView) itemView.findViewById(R.id.item_supplier_phone_number);

            mBTNEditSupplier = (ImageButton) itemView.findViewById(R.id.item_edit_supplier);
            mBTNCreateImport = (ImageButton) itemView.findViewById(R.id.item_create_import);
            mBTNShowListInvoices = (ImageButton) itemView.findViewById(R.id.item_show_invoices);
        }
    }

}
