package com.intellio.tesa.ui;

import android.view.LayoutInflater;

import com.intellio.tesa.R;
import com.intellio.tesa.core.Product;
import com.intellio.tesa.util.DateUtils;

import java.util.List;

public class ProductListAdapter extends AlternatingColorListAdapter<Product> {
    /**
     * @param inflater
     * @param items
     * @param selectable
     */
    public ProductListAdapter(final LayoutInflater inflater, final List<Product> items,
                              final boolean selectable) {
        super(R.layout.product_list_item, inflater, items, selectable);
    }

    /**
     * @param inflater
     * @param items
     */
    public ProductListAdapter(final LayoutInflater inflater, final List<Product> items) {
        super(R.layout.product_list_item, inflater, items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_title, R.id.tv_summary,
                R.id.tv_date};
    }

    @Override
    protected void update(final int position, final Product item) {
        super.update(position, item);

        setText(0, item.getTitle());
        setText(1, item.getContent());
        setText(2, DateUtils.formatShortDate(item.getDate_warranty()));
    }
}
