package com.vinhle.smn.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.handler.OnAgencySelectionListener;
import com.vinhle.smn.model.response.AgencyResponse;

import java.util.List;

/**
 * Created by VinhLe on 5/13/2017.
 */

public class ListAgencyChoiceAdapter extends RecyclerView.Adapter<ListAgencyChoiceAdapter.ViewHolder> {

    private AgencyResponse[] agencies;
    private List<Integer> agencyIds;
    private OnAgencySelectionListener agencySelectionListener;

    public ListAgencyChoiceAdapter(AgencyResponse[] agencies, OnAgencySelectionListener agencySelectionListener) {
        this.agencies = agencies;
        this.agencySelectionListener = agencySelectionListener;
    }

    @Override
    public ListAgencyChoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_view, parent, Boolean.FALSE);
        // set the view's size, margins, paddings and layout parameters
        ListAgencyChoiceAdapter.ViewHolder vh = new ListAgencyChoiceAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListAgencyChoiceAdapter.ViewHolder holder, int position) {
        final AgencyResponse itemResponse = agencies[position];
        holder.mTVName.setText(itemResponse.getAgencyName());

        holder.mTVName.setTextColor(Color.BLACK);
        holder.mTVName.setBackgroundResource(R.drawable.agency_bg);
        if (agencyIds != null && agencyIds.contains(itemResponse.getAgencyId())) {
            holder.mTVName.setTextColor(Color.WHITE);
            holder.mTVName.setBackgroundResource(R.drawable.agency_selected_bg);
        }

        holder.mTVName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agencySelectionListener.OnAgencySelected(itemResponse);
            }
        });
    }

    public void setAgencyIds(List<Integer> agencyIds) {
        if (agencyIds == null) return;

        this.agencyIds = agencyIds;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return agencies != null ? agencies.length : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTVName;

        public ViewHolder(View itemView) {
            super(itemView);
            mTVName = (TextView) itemView.findViewById(R.id.text1);
            mTVName.setBackgroundResource(R.drawable.agency_bg);
        }
    }
}
