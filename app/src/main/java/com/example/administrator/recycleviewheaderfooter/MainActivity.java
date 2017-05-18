package com.example.administrator.recycleviewheaderfooter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FreeRecyclerView recyclerView= (FreeRecyclerView) findViewById(R.id.recyclerview);
        //设置LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new FreeItemDecoration());

        final MyAdapter myAdapter = new MyAdapter();
        for(int i=0;i<15;i++){
            myAdapter.data.add("每天进步一点点 "+i);
        }
        recyclerView.setAdapter(myAdapter);
        recyclerView.setFreeRecyclerViewListener(new FreeRecyclerView.FreeRecyclerViewListener() {
            @Override
            public void onRefresh() {
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setRefreshComplete();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      for(int i=0;i<5;i++){
                          myAdapter.data.add(i+"做到了");
                      }
                        myAdapter.notifyDataSetChanged();
                        recyclerView.setLoadMoreComplete();
                    }
                },1000);
            }
        });
    }



    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        public ArrayList<String> data=new ArrayList<>();

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item, null);
            return new MyViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
