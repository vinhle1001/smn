package com.vinhle.searchablespinner;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by VinhLe on 5/8/2017.
 */

public class SearchableSpinner extends RelativeLayout implements View.OnTouchListener,
        SearchableListDialog.SearchableItem {

    private Context context;

    private SearchableListDialog searchableListDialog;
    private TextInputLayout mTILDisplay;
    private EditText mEDTDisplay;
    private ItemSearchableSelected itemSearchableSelected;

    private List itemsSource;
    private BaseSearchableModel itemSelected;
    private String hintText;
    private Boolean editable = Boolean.TRUE;

    public SearchableSpinner(Context context) {
        super(context);
        init(context, null);
    }

    public SearchableSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SearchableSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public List getItemsSource() {
        return itemsSource;
    }

    public void setItemsSource(List itemsSource) {
        this.itemsSource = itemsSource;

        if (this.itemsSource != null) {
            searchableListDialog = SearchableListDialog.newInstance(itemsSource);
            searchableListDialog.setOnSearchableItemClickListener(this);
        }
    }

    public BaseSearchableModel getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(BaseSearchableModel itemSelected) {
        Log.i("SearchableSpinner", itemSelected != null ? itemSelected.toString() : "null?");
        this.itemSelected = itemSelected;
        if (itemSelected != null)
            mEDTDisplay.setText(itemSelected.getDisplayText());
        else mEDTDisplay.setText("");
    }

    public void setItemSearchableSelected(ItemSearchableSelected itemSearchableSelected) {
        this.itemSearchableSelected = itemSearchableSelected;
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        View root = LayoutInflater.from(this.context).inflate(R.layout.searchable_spinner, this);

        mEDTDisplay = (EditText) root.findViewById(R.id.content);
        mTILDisplay = (TextInputLayout) root.findViewById(R.id.layout_display);
        mEDTDisplay.setOnTouchListener(this);

        if (itemsSource != null) {
            searchableListDialog = SearchableListDialog.newInstance(itemsSource);
            searchableListDialog.setOnSearchableItemClickListener(this);
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchableSpinner);
        hintText = typedArray.getString(R.styleable.SearchableSpinner_hintText);
        typedArray.recycle();

        mTILDisplay.setHint(hintText);

    }


    @Override
    public void onSearchableItemClicked(BaseSearchableModel item, int position) {
        Log.i("SearchableSpinner", item.getDisplayText());
        try {
            itemSelected = item;
            if (itemSearchableSelected != null)
                itemSearchableSelected.OnItemSelected(itemSelected);
            mEDTDisplay.setText(itemSelected.getDisplayText());
        } catch (Exception e) {
            Log.e("SearchableSpinner", e.getMessage(), e);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (editable && event.getAction() == MotionEvent.ACTION_UP && searchableListDialog != null) {
            searchableListDialog.show(scanForActivity(context).getFragmentManager(), "TAG");
        }
        return Boolean.FALSE;
    }

    private Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());
        return null;
    }

    public void setErrorEnabled(boolean enabled) {
        if (mTILDisplay != null)
            mTILDisplay.setErrorEnabled(enabled);
    }

    public void setError(String content) {
        if (mTILDisplay != null)
            mTILDisplay.setError(content);
    }

//    @Override
//    public Parcelable onSaveInstanceState() {
//        Parcelable superState = super.onSaveInstanceState();
//        SavedState ss = new SavedState(superState);
//
//        ss.itemSelected = itemSelected;
//
//        return ss;
//    }
//
//    @Override
//    public void onRestoreInstanceState(Parcelable state) {
//        super.onRestoreInstanceState(state);
//        SavedState ss = (SavedState) state;
//        super.onRestoreInstanceState(ss.getSuperState());
//
//        itemSelected = ss.itemSelected;
//        mEDTDisplay.setText(itemSelected.getDisplayText());
//    }
//
//    class SavedState extends BaseSavedState {
//
//        BaseSearchableModel itemSelected;
//
//        public SavedState(Parcel source) {
//            super(source);
//        }
//
//        public SavedState(Parcelable superState) {
//            super(superState);
//        }
//
//        @Override
//        public void writeToParcel(Parcel out, int flags) {
//            super.writeToParcel(out, flags);
//            if (itemSelected != null)
//                out.writeSerializable(itemSelected);
//        }
//    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }
}
