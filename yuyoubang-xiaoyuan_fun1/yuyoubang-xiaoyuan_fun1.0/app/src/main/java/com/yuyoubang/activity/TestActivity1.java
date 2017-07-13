package com.yuyoubang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.yuyoubang.R;
import com.yuyoubang.bean.NewsModel;

import java.util.ArrayList;

/**
 * Created by xiaoyuan on 16/11/10.
 */
public class TestActivity1 extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView mRecyclerView;


    public static void start(Context context) {
        Intent intent = new Intent(context, TestActivity1.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.act_info_1);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
//        mCollapsingToolbarLayout.setTitle("D_clock爱吃葱花");
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);






    }

    public ArrayList<NewsModel> getData() {
        ArrayList<NewsModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            NewsModel model = new NewsModel();
            model.setItem("相关" + i);
            list.add(model);
        }
        return list;
    }
}
