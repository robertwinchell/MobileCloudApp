package com.intellio.tesa.ui;

import android.accounts.AccountsException;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.github.kevinsawicki.wishlist.Toaster;
import com.intellio.tesa.R;
import com.intellio.tesa.TesaServiceProvider;
import com.intellio.tesa.core.Product;
import com.intellio.tesa.core.TesaService;
import com.intellio.tesa.util.DateUtils;
import com.intellio.tesa.util.SafeAsyncTask;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.io.IOException;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.InjectView;

import static com.intellio.tesa.core.Constants.Extra.PRODUCT_ITEM;

public class AddProductActivity extends PictureSelectActivity {

    private Product item;
    private String[] imageUrls=new String[0];

    @Inject
    protected TesaServiceProvider serviceProvider;
    @InjectView(R.id.b_submit) protected Button submit;
    @InjectView(R.id.et_name) protected EditText nameText;
    @InjectView(R.id.et_purchase_date) protected EditText purchaseDateText;
    @InjectView(R.id.et_warranty_date) protected EditText warrantyDateText;
    @InjectView(R.id.et_price) protected EditText priceText;
    @InjectView(R.id.et_description) protected EditText descriptionText;
    @InjectView(R.id.pb_loading) protected ProgressBar progressBar;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null && getIntent().getExtras() != null) {
            item = (Product) getIntent().getExtras().getSerializable(PRODUCT_ITEM);
        }
        setContentView(R.layout.add_product_activity);
        Calendar calendar=Calendar.getInstance();
        dialog = new DatePickerDialog(this,
                dateListener,calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        purchaseDateText.setOnFocusChangeListener(listener);
        warrantyDateText.setOnFocusChangeListener(listener);


        submit.setOnClickListener(onSubmitClicked);
        if(item !=null){
            nameText.setText(item.getTitle());
            purchaseDateText.setText(DateUtils.formatShortDate(item.getDate_purchase()));
            warrantyDateText.setText(DateUtils.formatShortDate(item.getDate_warranty()));
            priceText.setText(item.getPrice().toString());
            descriptionText.setText(item.getProblem_description());
        }
    }
    View.OnClickListener onSubmitClicked=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AddProductActivity.this.progressBar.setVisibility(View.VISIBLE);

                final Product product=getProduct();
                if(product == null){
                    return;
                }
                new SafeAsyncTask<Boolean>() {

                    @Override
                    public Boolean call() throws Exception {
                        if(product.getId() == null) {
                            return serviceProvider.getService(AddProductActivity.this).addProduct(
                                    product
                            );
                        }
                        return serviceProvider.getService(AddProductActivity.this).updateProduct(
                                product
                        );
                    }

                    @Override
                    protected void onSuccess(Boolean aBoolean) throws Exception {
                        super.onSuccess(aBoolean);
                        Toaster.showShort(AddProductActivity.this,product.getId()!=null?R.string.message_update_product_successful: R.string.message_add_product_successful);
                        Intent ret=new Intent();
                        ret.putExtra("successful",true);
                        ret.putExtra(PRODUCT_ITEM,product);
                        AddProductActivity.this.setResult(1,ret);
                        AddProductActivity.this.finish();
                    }

                    @Override
                    protected void onException(Exception e) throws RuntimeException {
                        AddProductActivity.this.progressBar.setVisibility(View.GONE);

                        Toaster.showShort(AddProductActivity.this,product.getId()!=null?R.string.message_update_product_failed: R.string.message_add_product_failed);

                        super.onException(e);
                    }
                }.execute();


        }
    };

    private Product getProduct() {
        try {
            Product ret = item;
            if (ret == null) {
                ret = new Product();
            }
            ret.setTitle(nameText.getText().toString());
            ret.setDate_purchase(DateUtils.fromShortDateString(purchaseDateText.getText().toString()));
            ret.setDate_warranty(DateUtils.fromShortDateString(warrantyDateText.getText().toString()));
            ret.setPrice(Double.valueOf(priceText.getText().toString()));
            ret.setProblem_description(descriptionText.getText().toString());
            return ret;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
        }


    private EditText currentDateInput;
    private View.OnFocusChangeListener listener=new View.OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){
                hideIM(v);
                currentDateInput=(EditText)v;
                dialog.show();
            }
            else{
                dialog.hide();
            }
        }
    };
    protected void hideIM(View edt){
        try {
            InputMethodManager im = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            IBinder windowToken = edt.getWindowToken();
            if(windowToken != null) {
                im.hideSoftInputFromWindow(windowToken, 0);
            }
        } catch (Exception e) {

        }
    }
    private DatePickerDialog dialog;
    private DatePickerDialog.OnDateSetListener dateListener=new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            currentDateInput.setText(year+"-"+monthOfYear+"-"+dayOfMonth);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
     //   getMenuInflater().inflate(R.menu.productmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_scan){
            SelectImage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
