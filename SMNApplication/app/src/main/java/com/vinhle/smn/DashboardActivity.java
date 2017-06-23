package com.vinhle.smn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.vinhle.smn.common.DirectionUtils;
import com.vinhle.smn.setting.AppSetting;

public class DashboardActivity extends AppCompatActivity {

    private Button mBTNCustomer;
    private Button mBTNSupplier;
    private Button mBTNProduct;
    private Button mBTNBill;
    private Button mBTNFinance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mBTNCustomer = (Button) findViewById(R.id.btn_customer);
        mBTNSupplier = (Button) findViewById(R.id.btn_supplier);
        mBTNProduct = (Button) findViewById(R.id.btn_product);
        mBTNBill = (Button) findViewById(R.id.btn_bill);
        mBTNFinance = (Button) findViewById(R.id.btn_finance);

        mBTNCustomer.setOnClickListener(customerClickListener);
        mBTNSupplier.setOnClickListener(supplierClickListener);
        mBTNProduct.setOnClickListener(productClickListener);
        mBTNBill.setOnClickListener(billClickListener);
        mBTNFinance.setOnClickListener(financeClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }


    /*----------------------------------- Method Action Listener DashboardActivity ----------------------------------------------------*/
    private View.OnClickListener customerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Board customer clicked!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Intent mIntent = new Intent(DashboardActivity.this, HomeActivity.class);
            mIntent.putExtra("fragment_index", AppSetting.Fragment.SEARCH_CUSTOMER.ordinal());
            DirectionUtils.changeActivity(DashboardActivity.this, R.anim.slide_in_from_right, R.anim.slide_out_to_left, true, mIntent);
        }
    };

    private View.OnClickListener supplierClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Board supplier clicked!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Intent mIntent = new Intent(DashboardActivity.this, HomeActivity.class);
            mIntent.putExtra("fragment_index", AppSetting.Fragment.SEARCH_SUPPLIER.ordinal());
            DirectionUtils.changeActivity(DashboardActivity.this, R.anim.slide_in_from_right, R.anim.slide_out_to_left, true, mIntent);
        }
    };

    private View.OnClickListener productClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Board product clicked!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Intent mIntent = new Intent(DashboardActivity.this, HomeActivity.class);
            mIntent.putExtra("fragment_index", AppSetting.Fragment.SEARCH_PRODUCT.ordinal());
            DirectionUtils.changeActivity(DashboardActivity.this, R.anim.slide_in_from_right, R.anim.slide_out_to_left, true, mIntent);
        }
    };

    private View.OnClickListener billClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Board bill clicked!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Intent mIntent = new Intent(DashboardActivity.this, HomeActivity.class);
            mIntent.putExtra("fragment_index", AppSetting.Fragment.SEARCH_BILL.ordinal());
            DirectionUtils.changeActivity(DashboardActivity.this, R.anim.slide_in_from_right, R.anim.slide_out_to_left, true, mIntent);
        }
    };

    private View.OnClickListener financeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Board finance clicked!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };
}
