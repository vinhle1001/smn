package com.vinhle.smn.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnImportSelectionListener;
import com.vinhle.smn.model.response.ImportSearchResponse;

import java.util.Date;

/**
 * Created by VinhLe on 6/12/2017.
 */
public class ListImportOfSupplierAdapter extends RecyclerView.Adapter<ListImportOfSupplierAdapter.ViewHolder> {

    private ImportSearchResponse[] imports;
    private OnImportSelectionListener importSelectionListener;

    public ListImportOfSupplierAdapter(ImportSearchResponse[] imports, OnImportSelectionListener importSelectionListener) {
        this.imports = imports;
        this.importSelectionListener = importSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_import_of_supplier, parent, Boolean.FALSE);
        // set the view's size, margins, paddings and layout parameters
        ListImportOfSupplierAdapter.ViewHolder vh = new ListImportOfSupplierAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ImportSearchResponse itemResponse = imports[position];

        holder.mTVSupplier.setText(itemResponse.getSupplierName());
        holder.mTVImportCost.setText(StringHelper.ConvertToVN(itemResponse.getImportCost(), "VND"));
        holder.mTVAgency.setText(itemResponse.getAgencyName());
        holder.mTVCreateDate.setText(StringHelper.Convert(new Date(itemResponse.getCreateDate().getTime())));
        holder.mIBEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (importSelectionListener != null)
                    importSelectionListener.OnImportSelection(itemResponse.getImportId(), itemResponse.getSupplierId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return imports != null ? imports.length : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton mIBEdit;
        TextView mTVSupplier;
        TextView mTVImportCost;
        TextView mTVAgency;
        TextView mTVCreateDate;

        public ViewHolder(View itemView) {
            super(itemView);

            mIBEdit = (ImageButton) itemView.findViewById(R.id.item_edit_import);
            mTVSupplier = (TextView) itemView.findViewById(R.id.item_import_supplier);
            mTVImportCost = (TextView) itemView.findViewById(R.id.item_import_cost);
            mTVAgency = (TextView) itemView.findViewById(R.id.item_import_agency);
            mTVCreateDate = (TextView) itemView.findViewById(R.id.item_import_date);
        }
    }

}
