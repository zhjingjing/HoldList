package com.zj.holdlist;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.zj.holdlist.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CoinAdapter nameAdapter;
    private CoinAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil. setContentView(this,R.layout.activity_main);
        binding.setPresenter(this);

        initList();
        initData();
    }

    public void initList(){
        binding.recycleCoinName.setHasFixedSize(true);
        binding.recycleCoinName.setLayoutManager(new LinearLayoutManager(this));
        nameAdapter=new CoinAdapter(this);

        binding.recycleCoinName.setAdapter(nameAdapter);


        binding.recycleCoinData.setHasFixedSize(true);
        binding.recycleCoinData.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter=new CoinAdapter(this);

        binding.recycleCoinData.setAdapter(dataAdapter);

        binding.recycleCoinName.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (isNameScroll){
                    binding.recycleCoinData.scrollBy(0,dy);
                }


            }
        });

        binding.recycleCoinData.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isNameScroll){
                    binding.recycleCoinName.scrollBy(0,dy);
                }

            }
        });

        binding.recycleCoinData.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isNameScroll=false;
                        break;
                }
                return false;
            }
        });


        binding.recycleCoinName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isNameScroll=true;
                        break;
                }
                return false;
            }
        });
    }

    public void initData(){
        List<CoinBean> nameList=new ArrayList<>();
        for (int i=0;i<20;i++){
            CoinBean bean=new CoinBean();
            bean.coinName="BTC"+i;
            bean.pairName="pairName"+i;
            bean.pairUnit="pairUnit"+i;
            bean.current="current"+i;
            bean.currentCny="currentCny"+i;
            bean.change="change"+i;
            bean.changeRate="changeRate"+i;
            bean.viewType=0;
            nameList.add(bean);
        }
        nameAdapter.setData(nameList);

        List<CoinBean> dataList=new ArrayList<>();
        for (int i=0;i<20;i++){
            CoinBean bean=new CoinBean();
            bean.coinName="BTC"+i;
            bean.pairName="pairName"+i;
            bean.pairUnit="pairUnit"+i;
            bean.current="current"+i;
            bean.currentCny="currentCny"+i;
            bean.change="change"+i;
            bean.changeRate="changeRate"+i;
            bean.viewType=1;
            dataList.add(bean);
        }
        dataAdapter.setData(dataList);
    }

    private boolean isNameScroll=true;

}
