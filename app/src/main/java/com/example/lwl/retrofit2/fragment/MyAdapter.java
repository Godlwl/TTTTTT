package com.example.lwl.retrofit2.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lwl.retrofit2.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by LWL on 2017/3/2.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<DataResponse> data=new ArrayList<>();
    public MyAdapter(ArrayList<DataResponse> data){
        this.data=data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1= (MyViewHolder) holder;
        holder1.mTvName.setText(data.get(position).getName());
        holder1.mTvBir.setText(data.get(position).getBir());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvName;
        private TextView mTvBir;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvName= (TextView) itemView.findViewById(R.id.tv_name);
            mTvBir= (TextView) itemView.findViewById(R.id.tv_bir);

        }
    }
}
