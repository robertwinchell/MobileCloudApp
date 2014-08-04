package com.intellio.tesa.ui;

import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.intellio.tesa.TesaServiceProvider;
import com.intellio.tesa.Injector;
import com.intellio.tesa.R;
import com.intellio.tesa.authenticator.LogoutService;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.intellio.tesa.core.Product;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static com.intellio.tesa.core.Constants.Extra.NEWS_ITEM;

public class ProductListFragment extends ItemListFragment<Product> {

    @Inject protected TesaServiceProvider serviceProvider;
    @Inject protected LogoutService logoutService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(R.string.no_products);
    }

    @Override
    protected void configureList(Activity activity, ListView listView) {
        super.configureList(activity, listView);

        listView.setFastScrollEnabled(true);
        listView.setDividerHeight(0);

        getListAdapter()
                .addHeader(activity.getLayoutInflater()
                        .inflate(R.layout.product_list_item_labels, null));
    }

    @Override
    protected LogoutService getLogoutService() {
        return logoutService;
    }

    @Override
    public void onDestroyView() {
        setListAdapter(null);

        super.onDestroyView();
    }

    @Override
    public Loader<List<Product>> onCreateLoader(int id, Bundle args) {
        final List<Product> initialItems = items;
        return new ThrowableLoader<List<Product>>(getActivity(), items) {

            @Override
            public List<Product> loadData() throws Exception {
                try {
                    if (getActivity() != null) {
                        return serviceProvider.getService(getActivity()).getProducts();
                    } else {
                        return Collections.emptyList();
                    }

                } catch (OperationCanceledException e) {
                    Activity activity = getActivity();
                    if (activity != null)
                        activity.finish();
                    return initialItems;
                }
            }
        };
    }

    @Override
    protected SingleTypeAdapter<Product> createAdapter(List<Product> items) {
        return new ProductListAdapter(getActivity().getLayoutInflater(), items);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Product news = ((Product) l.getItemAtPosition(position));

        startActivity(new Intent(getActivity(), ProductActivity.class).putExtra(NEWS_ITEM, news));
    }

    @Override
    protected int getErrorMessage(Exception exception) {
        return R.string.error_loading_products;
    }
}
