package com.example.database;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class card3Adapter extends RecyclerView.Adapter<card3Adapter.ViewHolder>{
    private List<card3Bean> mDatas;
    private  Context context;

    public card3Adapter(Context context, List<card3Bean> comments){
        this.mDatas = comments;
        this.context = context;
    }
    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itm_crd, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name.setText(mDatas.get(position).getName());
        holder.tv_date.setText(mDatas.get(position).getDate());
        holder.tv_com.setText(mDatas.get(position).getComment());
        holder.tv_pic.setImageResource(mDatas.get(position).getPic());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_com, tv_date;
        private ImageView tv_pic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_com = itemView.findViewById(R.id.tv_com);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_pic = itemView.findViewById(R.id.tv_pic);

        }
    }
}
