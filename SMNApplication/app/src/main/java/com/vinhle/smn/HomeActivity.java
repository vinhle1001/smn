package com.vinhle.smn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.vinhle.smn.common.DirectionUtils;
import com.vinhle.smn.fragment.BaseFragment;
import com.vinhle.smn.fragment.BillInformationFragment;
import com.vinhle.smn.fragment.BillOfCustomerFragment;
import com.vinhle.smn.fragment.BillReturnedInformationFragment;
import com.vinhle.smn.fragment.CustomerInformationFragment;
import com.vinhle.smn.fragment.ImportInformationFragment;
import com.vinhle.smn.fragment.ImportOfSupplierFragment;
import com.vinhle.smn.fragment.ProductInformationFragment;
import com.vinhle.smn.fragment.BillFragment;
import com.vinhle.smn.fragment.SearchCustomerFragment;
import com.vinhle.smn.fragment.SearchProductFragment;
import com.vinhle.smn.fragment.SearchSupplierFragment;
import com.vinhle.smn.fragment.SupplierInformationFragment;
import com.vinhle.smn.handler.OnFragmentSelectionChanged;
import com.vinhle.smn.model.request.CompositedIdCustomerBillRequest;
import com.vinhle.smn.model.request.CompositedIdSupplierImportRequest;
import com.vinhle.smn.model.response.CustomerSearchResponse;
import com.vinhle.smn.model.response.ProductSearchResponse;
import com.vinhle.smn.model.response.SupplierSearchResponse;
import com.vinhle.smn.setting.AppSetting;

import java.util.List;
import java.util.UUID;

/**
 * Created by VinhLe on 5/8/2017.
 */
public class HomeActivity extends AppCompatActivity {

//    private FrameLayout mFRLContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            AppSetting.Fragment fragmentFlag = AppSetting.Fragment.SEARCH_CUSTOMER;
            if (getIntent().hasExtra("fragment_index")) {
                fragmentFlag = AppSetting.Fragment.values()[getIntent().getIntExtra("fragment_index", 0)];
            }
            onFragmentSelectionChanged.OnFragment(fragmentFlag, null);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) return;
        // Add onFragmentSelectionChanged after lost session app
        for (Fragment f : fragments) {
            if (f != null && f instanceof BaseFragment)
                ((BaseFragment) f).setOnFragmentSelectionChanged(onFragmentSelectionChanged);
        }
    }

    /*----------------------------------- Method Action Listener HomeActivity ----------------------------------------------------*/
    @Override
    public void onBackPressed() {
        if (PopBackStackFragment()) return;
        else {
            BackToDashboard();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            if (!PopBackStackFragment())
                BackToDashboard();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean PopBackStackFragment() {
        if (getSupportFragmentManager().popBackStackImmediate()) {
            Fragment mFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (mFragment != null) {
                getSupportFragmentManager().beginTransaction().show(mFragment).commit();
                if (mFragment instanceof BaseFragment)
                    ((BaseFragment) mFragment).setOnFragmentSelectionChanged(onFragmentSelectionChanged);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private void BackToDashboard() {
        Intent mIntent = new Intent(HomeActivity.this, DashboardActivity.class);
        DirectionUtils.changeActivity(HomeActivity.this, R.anim.slide_in_from_left, R.anim.slide_out_to_right, true, mIntent);
    }

    /*----------------------------------- Method Action Listener HomeActivity ----------------------------------------------------*/
    private OnFragmentSelectionChanged onFragmentSelectionChanged = new OnFragmentSelectionChanged() {
        @Override
        public void OnFragment(AppSetting.Fragment fragmentFlag, Object... params) {
            BaseFragment fragment = null;
            switch (fragmentFlag) {
                case SEARCH_CUSTOMER:
                    fragment = new SearchCustomerFragment();
                    break;
                case CUSTOMER_INFORMATION:
                    fragment = new CustomerInformationFragment();
                    if (params[0] != null && params[0] instanceof CustomerSearchResponse) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("customer_id", ((CustomerSearchResponse) params[0]).customerId);
                        fragment.setArguments(bundle);
                    }
                    break;
                case SEARCH_SUPPLIER:
                    fragment = new SearchSupplierFragment();
                    break;
                case SUPPLIER_INFORMATION:
                    fragment = new SupplierInformationFragment();
                    if (params[0] != null && params[0] instanceof SupplierSearchResponse) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("supplier_id", ((SupplierSearchResponse) params[0]).getSupplierId());
                        fragment.setArguments(bundle);
                    }
                    break;
                case SEARCH_PRODUCT:
                    fragment = new SearchProductFragment();
                    break;
                case PRODUCT_INFORMATION:
                    fragment = new ProductInformationFragment();
                    if (params[0] != null && params[0] instanceof ProductSearchResponse) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("product_id", ((ProductSearchResponse) params[0]).getProductId());
                        fragment.setArguments(bundle);
                    }
                    break;
                case BILL_INFORMATION:
                    fragment = new BillInformationFragment();
                    if (params[0] != null && params[0] instanceof CustomerSearchResponse) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("customer_id", ((CustomerSearchResponse) params[0]).customerId);
                        fragment.setArguments(bundle);
                    } else if (params[0] != null && params[0] instanceof Integer) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("customer_id", (Integer) params[0]);
                        fragment.setArguments(bundle);
                    } else if (params[0] != null && params[0] instanceof CompositedIdCustomerBillRequest) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("customer_id", ((CompositedIdCustomerBillRequest) params[0]).getCustomerId());
                        bundle.putInt("bill_id", ((CompositedIdCustomerBillRequest) params[0]).getBillId());
                        fragment.setArguments(bundle);
                    }
                    break;
                case BILLS_OF_CUSTOMER:
                    fragment = new BillOfCustomerFragment();
                    if (params[0] != null && params[0] instanceof CustomerSearchResponse) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("customer_id", ((CustomerSearchResponse) params[0]).customerId);
                        fragment.setArguments(bundle);
                    }
                    break;
                case BILL_RETURNED_INFORMATION:
                    fragment = new BillReturnedInformationFragment();
                    if (params[0] != null && params[1] != null) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("customer_id", (Integer) params[0]);
                        bundle.putInt("bill_id", (Integer) params[1]);
                        fragment.setArguments(bundle);
                    }
                    break;
                case IMPORT_INFORMATION:
                    fragment = new ImportInformationFragment();
                    if (params[0] != null && params[0] instanceof SupplierSearchResponse) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("supplier_id", ((SupplierSearchResponse) params[0]).getSupplierId());
                        fragment.setArguments(bundle);
                    } else if (params[0] != null && params[0] instanceof CompositedIdSupplierImportRequest) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("supplier_id", ((CompositedIdSupplierImportRequest) params[0]).getSupplierId());
                        bundle.putInt("import_id", ((CompositedIdSupplierImportRequest) params[0]).getImportId());
                        fragment.setArguments(bundle);
                    }
                    break;
                case IMPORTS_OF_SUPPLIER:
                    fragment = new ImportOfSupplierFragment();
                    if (params[0] != null && params[0] instanceof SupplierSearchResponse) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("supplier_id", ((SupplierSearchResponse) params[0]).getSupplierId());
                        fragment.setArguments(bundle);
                    }
                    break;
                case SEARCH_BILL:
                    fragment = new BillFragment();
                    break;
                case POP_BACK_STACK:
                    PopBackStackFragment();
                    break;
            }
            if (fragment != null) {
                fragment.setOnFragmentSelectionChanged(onFragmentSelectionChanged);
                Fragment mFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

                if (mFragment == null) {
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(UUID.randomUUID().toString()).commit();
                } else /*if (mFragment instanceof SearchCustomerFragment || mFragment instanceof SearchProductFragment) */ {
                    getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(UUID.randomUUID().toString()).commit();
                } /*else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("SECOND").commit();
                }*/
            }
        }
    };

}
