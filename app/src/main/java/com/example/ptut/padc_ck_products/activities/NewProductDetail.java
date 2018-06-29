package com.example.ptut.padc_ck_products.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ptut.padc_ck_products.R;
import com.example.ptut.padc_ck_products.activities.base.BaseActivity;
import com.example.ptut.padc_ck_products.mvp.presenters.ProductDetailPresenter;
import com.example.ptut.padc_ck_products.mvp.views.ProductDetailView;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;
import com.example.ptut.padc_ck_products.share.AppConstant;
import com.example.ptut.padc_ck_products.share.ViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewProductDetail extends BaseActivity implements ProductDetailView {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.SliderDots)
    LinearLayout sliderDotspanel;

    @BindView(R.id.product_detail_title)
    TextView productDetailTitle;

    private int dotscount;
    private ImageView[] dots;
    private ProductDetailPresenter pDetailPresenter;
    private List<String> productImage;


    public static Intent newIntent(Context context, int newsId) {
        Intent intent = new Intent(context, NewProductDetail.class);
        intent.putExtra(AppConstant.PRODUCT_ID, newsId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newproduct_detail);
        ButterKnife.bind(this,this);

        pDetailPresenter= ViewModelProviders.of(this).get(ProductDetailPresenter.class);
        pDetailPresenter.initPresenter(this);
        pDetailPresenter.getErrorLD().observe(this,this);

        int productId = getIntent().getIntExtra(AppConstant.PRODUCT_ID,0);
        productImage=new ArrayList<>();

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);


        pDetailPresenter.getProductById(productId).observe(this, new Observer<NewProductVO>() {
            @Override
            public void onChanged(@Nullable NewProductVO newProductVO) {
                productDetailTitle.setText(newProductVO.getProductTitle());
                productImage.add(newProductVO.getProductImage());
                viewPagerAdapter.setImages(productImage);

                if(viewPagerAdapter.getCount()!=0) {
                    dotscount = viewPagerAdapter.getCount();
                    dots = new ImageView[dotscount];

                    for (int i = 0; i < dotscount; i++) {
                        dots[i] = new ImageView(getApplicationContext());
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, 8, 0, 8);
                        sliderDotspanel.addView(dots[i], params);

                    }
                    dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            for (int i = 0; i < dotscount; i++) {
                                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                            }
                            dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                }
            }
        });

    }

    @Override
    public void displayErrorMessage(String s) {
        super.displayErrorMessage(s);
    }

    @OnClick(R.id.back_btn)
    public void onBackPressed(){
        super.onBackPressed();
    }
}
