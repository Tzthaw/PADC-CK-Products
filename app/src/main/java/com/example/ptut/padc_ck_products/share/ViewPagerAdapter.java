package com.example.ptut.padc_ck_products.share;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ptut.padc_ck_products.R;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> images;

    public ViewPagerAdapter(Context context) {
        this.context = context;
        images=new ArrayList<>();
    }

    @Override
    public int getCount() {
        return images.size();

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager_item_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setRotation(-90);

        Glide.with(context)
                .load(images.get(position))
                .into(imageView);


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }



    public void setImages(List<String> imageList){
        this.images=imageList;
        notifyDataSetChanged();
    }

}