package com.example.qctmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.qctmanagement.R;
import com.example.qctmanagement.model.WizardModel;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Nguyen Quoc Cuong on 7/28/2020.
 */
public class WizardAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<WizardModel> list;

    public WizardAdapter(Context context, List<WizardModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = layoutInflater.from(context);
        WizardModel item = list.get(position);
        View view = layoutInflater.inflate(R.layout.item_card_wizard, container, false);
        ((TextView) view.findViewById(R.id.title)).setText(item.getTitle());
        ((TextView) view.findViewById(R.id.description)).setText(item.getDescription());
        ((ImageView) view.findViewById(R.id.image)).setImageResource(item.getImage());

        container.addView(view);
        return view;
    }
}
