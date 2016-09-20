package com.zhuyuxi.beicai.lenovo.swiperefushdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private SwipeRefreshLayout mLayout;
    private RecyclerView mRecyclerView;
    private List<String> datas=new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout= (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        initDatas();
        initRefreshLayout();
        initRecyclerView();

    }

    private void initRecyclerView() {
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        myAdapter=new MyAdapter(datas);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, String city) {
                Toast.makeText(MainActivity.this,"city:"+city+"position:"+position,Toast.LENGTH_SHORT).show();



            }
        });
    }

    private void initRefreshLayout(){
        //设置条条颜色
    mLayout.setColorSchemeResources(android.R.color.holo_red_light
    ,android.R.color.holo_blue_light
    ,android.R.color.holo_orange_light
    ,android.R.color.holo_green_light);
        //设置时间
    mLayout.setDistanceToTriggerSync(100);
        //设置圈圈颜色
    mLayout.setProgressBackgroundColorSchemeColor(
            getResources().getColor(R.color.white));
        //设置圈圈大小
    mLayout.setSize(SwipeRefreshLayout.LARGE);
        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<10;i++){
                            myAdapter.addData(i,"new City:"+i);
                        }
                        myAdapter.notifyItemRangeChanged(0,10);
                        mRecyclerView.scrollToPosition(0);
                        mLayout.setRefreshing(false);
                    }
                },3000);
            }
        });


}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_action_add:
                myAdapter.addData(0,"new city");
            break;
            case R.id.id_action_delete:
                myAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_staview:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        return true;
    }

    private void initDatas(){
        datas.add("周润发");
        datas.add("刘德华");
        datas.add("大卫");
        datas.add("小明");
        datas.add("新华");
        datas.add("北京");
        datas.add("河南");
        datas.add("皇家");
        datas.add("巴塞");
        datas.add("细微");
        datas.add("新德里");
        datas.add("西班牙");
        datas.add("美国");
        datas.add("印度");
        datas.add("迪拜");
    }
}
