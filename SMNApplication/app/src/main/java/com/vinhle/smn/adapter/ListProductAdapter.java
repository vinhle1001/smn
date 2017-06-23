package com.vinhle.smn.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vinhle.smn.R;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnItemSelectionListener;
import com.vinhle.smn.model.response.ProductSearchResponse;

/**
 * Created by VinhLe on 5/8/2017.
 */
public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ViewHolder> {

    private ProductSearchResponse[] products;
    private OnItemSelectionListener itemCustomerSelectionListener;

    public ListProductAdapter(ProductSearchResponse[] products, OnItemSelectionListener itemCustomerSelectionListener) {
        this.products = products;
        this.itemCustomerSelectionListener = itemCustomerSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_product, parent, Boolean.FALSE);
        // set the view's size, margins, paddings and layout parameters
        ListProductAdapter.ViewHolder vh = new ListProductAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return products != null ? products.length : 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ProductSearchResponse itemResponse = products[position];

        // TODO: Set name and price for item
        holder.mTVProductName.setText(itemResponse.getProductName());
        holder.mTVProductPrice.setText(StringHelper.ConvertToVN(itemResponse.getProductPrice(), "VND"));
        holder.mTVProductTypeName.setText(itemResponse.getProductTypeName());

        // TODO: Set status for item
        if (itemResponse.getIsActive() == 0) {
            holder.mVProductStatus.setBackgroundResource(R.drawable.offline_bg);
        } else {
            holder.mVProductStatus.setBackgroundResource(R.drawable.online_bg);
        }

        // TODO: Set itemClickListener for item
//        if (!holder.mBTNEditProduct.hasOnClickListeners()) {
            holder.mBTNEditProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemCustomerSelectionListener != null)
                        itemCustomerSelectionListener.OnEditItem(itemResponse);
                }
            });
//        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTVProductName;
        TextView mTVProductPrice;

        ImageButton mBTNEditProduct;
        View mVProductStatus;
        TextView mTVProductTypeName;

        public ViewHolder(View itemView) {
            super(itemView);

            mTVProductName = (TextView) itemView.findViewById(R.id.item_product_name);
            mTVProductPrice = (TextView) itemView.findViewById(R.id.item_product_price);

            mBTNEditProduct = (ImageButton) itemView.findViewById(R.id.item_edit_product);
            mVProductStatus = (View) itemView.findViewById(R.id.item_product_status);
            mTVProductTypeName = (TextView) itemView.findViewById(R.id.item_product_type_name);
        }
    }


}
