package com.example.ptut.padc_ck_products.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.example.ptut.padc_ck_products.R;
import com.example.ptut.padc_ck_products.adapters.base.BaseRecyclerAdapter;
import com.example.ptut.padc_ck_products.delegates.NewProductDelegate;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;
import com.example.ptut.padc_ck_products.viewHolders.NewProductViewHolder;

public class NewProductAdapter extends BaseRecyclerAdapter<NewProductViewHolder,NewProductVO>{

    private NewProductDelegate nTapListener;

    public NewProductAdapter(Context context,NewProductDelegate nTapListener) {
        super(context);
        this.nTapListener=nTapListener;
    }

    @NonNull
    @Override
    public NewProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view=mLayoutInflator.inflate(R.layout.newin_items_view,viewGroup,false);
        return new NewProductViewHolder(view,nTapListener);
    }
}
