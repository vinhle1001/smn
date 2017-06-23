package com.vinhle.searchablespinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.vinhle.searchablespinner.common.StringHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhLe on 6/3/2017.
 */
public class SearchableSpinnerAdapter extends BaseAdapter implements Filterable {

    private List<BaseSearchableModel> model;
    private List<BaseSearchableModel> mOriginalValues; // Original Values
    private Context context;

    public SearchableSpinnerAdapter(Context context, List<BaseSearchableModel> model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model != null ? model.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return model != null ? model.get(position) : new Object();
    }

    @Override
    public long getItemId(int position) {
        return model != null ? model.get(position).hashCode() : 0L;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseSearchableModel item = model.get(position);
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_searchable, parent, Boolean.FALSE);
            holder.mTVTittle = (TextView) convertView.findViewById(R.id.title);
            holder.mTVSubTittle = (TextView) convertView.findViewById(R.id.subtitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (item.getSubTitle() == null || item.getSubTitle().isEmpty())
            holder.mTVSubTittle.setVisibility(View.GONE);
        else {
            holder.mTVSubTittle.setVisibility(View.VISIBLE);
            holder.mTVSubTittle.setText(item.getSubTitle());
        }
        holder.mTVTittle.setText(item.getDisplayText());

        return convertView;
    }

    private class ViewHolder {
        TextView mTVTittle;
        TextView mTVSubTittle;
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                List<BaseSearchableModel> FilteredArrList = new ArrayList<>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<>(model); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/

                if (constraint == null || constraint.length() == 0) {
                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        BaseSearchableModel data = mOriginalValues.get(i);
                        String text1 = StringHelper.getString(data.getDisplayText());
                        String text2 = StringHelper.getString(constraint.toString());
                        if (text1.contains(text2)) {
                            FilteredArrList.add(data);
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                model = (List<BaseSearchableModel>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }
        };

        return filter;
    }
}
