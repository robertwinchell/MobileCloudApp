package com.intellio.tesa.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.intellio.tesa.R;
import com.intellio.tesa.core.Constants;
import com.intellio.tesa.core.Product;
import com.intellio.tesa.util.DateUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.InjectView;

import static com.intellio.tesa.core.Constants.Extra.PRODUCT_ITEM;

public class ProductActivity extends PictureSelectActivity {

    private Product item;
    private String[] imageUrls=new String[0];
    DisplayImageOptions options;
    @InjectView(R.id.tv_title) protected TextView title;
    @InjectView(R.id.tv_content) protected TextView content;
    @InjectView(R.id.tv_purchasedAt) protected TextView purchasedAt;
    @InjectView(R.id.tv_createdAt) protected TextView createdAt;
    @InjectView(R.id.tv_price) protected TextView price;
    @InjectView(R.id.gallery) protected Gallery gallery;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product);

        if (getIntent() != null && getIntent().getExtras() != null) {
            item = (Product) getIntent().getExtras().getSerializable(PRODUCT_ITEM);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initView();

    }
    protected void initView(){

        setTitle(item.getTitle());

        title.setText(item.getTitle());
        content.setText(item.getContent());
        purchasedAt.setText(DateUtils.formatShortDate(item.getDate_purchase()));
        createdAt.setText(DateUtils.formatShortDate(item.getDate_warranty()));
        price.setText(item.getPrice().toString());
        if(item.getReceipts()!=null){
            imageUrls=item.getReceipts();
        }
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        gallery.setAdapter(new ImageGalleryAdapter());
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startImagePagerActivity(position);
            }
        });
    }
    /**
     * Open activities to show all images
     * */
    private void startImagePagerActivity(int position) {
        Intent intent = new Intent(this, ImagePagerActivity.class);
        intent.putExtra(Constants.Extra.IMAGES, imageUrls);
        intent.putExtra(Constants.Extra.IMAGE_POSITION, position);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.productmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_scan){
            SelectImage();
            return true;
        }
        else if(item.getItemId() == R.id.update_product){
            showUpdateActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showUpdateActivity() {
        Intent intent=new Intent(this,AddProductActivity.class);
        intent.putExtra(PRODUCT_ITEM,item);
        startActivityForResult(intent,1001);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1001){
            if(data != null &&data.getBooleanExtra("successful",false)){
                item=(Product) data.getSerializableExtra(PRODUCT_ITEM);
                initView();
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onImageSelected(Uri uri) {
        ArrayList<String> list=new ArrayList<String>();
        list.add(uri.toString());

        Collections.addAll(list,imageUrls);
        imageUrls=list.toArray(new String[list.size()]);
        gallery.setAdapter(new ImageGalleryAdapter());
    }

    /**
     * Adapter for Image gallery
     * */
    private class ImageGalleryAdapter extends BaseAdapter {
        protected ImageLoader imageLoader = ImageLoader.getInstance();
        @Override
        public int getCount() {
            return imageUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = (ImageView) convertView;
            if (imageView == null) {
                imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_gallery_image, parent, false);
            }
            imageLoader.displayImage(imageUrls[position], imageView, options);
            return imageView;
        }
    }
}
