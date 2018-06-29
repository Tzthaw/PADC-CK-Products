package com.example.ptut.padc_ck_products.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ptut.padc_ck_products.R;
import com.example.ptut.padc_ck_products.delegates.NewProductDelegate;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;
import com.example.ptut.padc_ck_products.viewHolders.base.BaseViewHolder;
import butterknife.BindView;

public class NewProductViewHolder extends BaseViewHolder<NewProductVO> {

    @BindView(R.id.product_img)
    ImageView productImg;
    @BindView(R.id.product_desc)
    TextView productDesc;
    @BindView(R.id.product_name)
    TextView productName;

    private NewProductVO newProductVO;
    private NewProductDelegate nTapListener;

    public NewProductViewHolder(View itemView,NewProductDelegate nTapListener) {
        super(itemView);
        this.nTapListener=nTapListener;
    }

    @Override
    public void setData(NewProductVO data) {
        newProductVO=data;
        if(data!=null){
            Glide.with(itemView)
                    .load(data.getProductImage())
                    .into(productImg);
            productName.setText(data.getProductTitle());
        }

    }

    @Override
    public void onClick(View view) {
        nTapListener.onTapProductItem(newProductVO);
    }
}
