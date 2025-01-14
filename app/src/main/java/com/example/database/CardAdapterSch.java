package com.example.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapterSch extends RecyclerView.Adapter<CardAdapterSch.ViewHolder> {
    private List<CardBeanSch> mDatas;
    private Context context;

    public CardAdapterSch(Context context, List<CardBeanSch> users) {
        this.mDatas = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name.setText(mDatas.get(position).getName());
        holder.tv_date.setText(mDatas.get(position).getDate());
        holder.tv_time.setText(mDatas.get(position).getTime());
        holder.tv_sub.setText("" + mDatas.get(position).getSubject());
        holder.pic.setImageResource(mDatas.get(position).getPic());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_sub, tv_date, tv_time;
        private ImageView pic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_sub = itemView.findViewById(R.id.tv_sub);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_date = itemView.findViewById(R.id.tv_date);
            pic = itemView.findViewById(R.id.pic);

        }
    }
}
