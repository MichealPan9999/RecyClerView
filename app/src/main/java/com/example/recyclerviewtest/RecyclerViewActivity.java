package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    Button btn_add;
    Button btn_del;
    Button btn_list;
    Button btn_grid;
    Button btn_flow;
    List<String> datas;
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initViews();

        initdatas();

        //设置adapter
        adapter = new MyRecyclerViewAdapter(RecyclerViewActivity.this, datas);
        recyclerView.setAdapter(adapter);

        //设置分割线
        recyclerView.addItemDecoration(new DividerListItemDecoration(RecyclerViewActivity.this, DividerListItemDecoration.HORIZONTAL_LIST));
        recyclerView.addItemDecoration(new DividerListItemDecoration(RecyclerViewActivity.this, DividerListItemDecoration.VERTICAL_LIST));


        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemclickListner() {
            @Override
            public void OnItemClick(View view, String data) {
                Toast.makeText(RecyclerViewActivity.this, "data = " + data, Toast.LENGTH_SHORT).show();

            }
        });


        //设置动画s
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initdatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("content_" + i);
        }
    }

    @SuppressLint("WrongConstant")
    private void initViews() {
        btn_add = findViewById(R.id.btn_add);
        btn_del = findViewById(R.id.btn_del);
        btn_list = findViewById(R.id.btn_list);
        btn_grid = findViewById(R.id.btn_grid);
        btn_flow = findViewById(R.id.btn_flow);
        btn_add.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this, LinearLayoutManager.VERTICAL, false));

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_add:
                adapter.addData(0, "zhangsan");
                recyclerView.scrollToPosition(0);
                break;
            case R.id.btn_del:
                adapter.removeData(0);
                break;
            case R.id.btn_list:
                //设置LayoutManager
                //最后一位代表时顺序还是倒序
                recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this, LinearLayoutManager.VERTICAL, false));
                //recyclerView.scrollToPosition(datas.size()/2);//定位到中间位置
                break;
            case R.id.btn_grid:
                //设置成3列
                //最后一位代表时顺序还是倒序
                recyclerView.setLayoutManager(new GridLayoutManager(RecyclerViewActivity.this, 3, GridLayoutManager.VERTICAL, false));
                break;
            case R.id.btn_flow:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
                break;
        }

    }
}
