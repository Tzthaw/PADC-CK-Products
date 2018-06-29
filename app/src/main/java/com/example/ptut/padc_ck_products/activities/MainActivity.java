package com.example.ptut.padc_ck_products.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptut.padc_ck_products.R;
import com.example.ptut.padc_ck_products.activities.base.BaseActivity;
import com.example.ptut.padc_ck_products.adapters.NewProductAdapter;
import com.example.ptut.padc_ck_products.models.NewProductModel;
import com.example.ptut.padc_ck_products.mvp.presenters.NewProductPresenter;
import com.example.ptut.padc_ck_products.mvp.views.NewProductView;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,NewProductView {

    @BindView(R.id.items_recycler) RecyclerView iRecycler;
    @BindView(R.id.product_item_count) TextView productCount;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    private GridLayoutManager gridLayoutManager;
    private NewProductPresenter nPresenter;
    private NewProductAdapter nAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this,this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //declare presenter which extend viewmodel
        nPresenter= ViewModelProviders.of(this).get(NewProductPresenter.class);
        nPresenter.initPresenter(this);
        nPresenter.getErrorLD().observe(this,this);

        //set grid one column or two column
        nAdapter=new NewProductAdapter(this,nPresenter);
        if(gridLayoutManager==null){
            gridLayoutManager=new GridLayoutManager(this,2);
            iRecycler.setLayoutManager(gridLayoutManager);
        }else{
            iRecycler.setLayoutManager(gridLayoutManager);
        }
        iRecycler.setAdapter(nAdapter);

        //call presenter to get data from room db
        nPresenter.getNewProductsLD().observe(this, new Observer<List<NewProductVO>>() {
            @Override
            public void onChanged(@Nullable List<NewProductVO> newProductVOS) {
                productCount.setText(newProductVOS.size()+" ITEMS");
                nAdapter.setNewData(newProductVOS);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.grid_one)
    public void onTapChangeGridOne(){
        gridLayoutManager=new GridLayoutManager(this, 1);
        iRecycler.setLayoutManager(gridLayoutManager);
    }

    @OnClick(R.id.grid_two)
    public void onTapChangeGridTwo(){
        gridLayoutManager=new GridLayoutManager(this, 2);
        iRecycler.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void displayErrorMessage(String s) {
        super.displayErrorMessage(s);
    }

    @Override
    public void launchProductDetail(int id) {
        Intent intent=NewProductDetail.newIntent(this,id);
        startActivity(intent);
    }
}
