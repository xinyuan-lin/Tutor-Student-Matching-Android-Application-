package com.example.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<CardBean> mDatas;
    private Context context;

    public CardAdapter(Context context, List<CardBean> users) {
        this.mDatas = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name.setText(mDatas.get(position).getName());
        holder.tv_1.setText(mDatas.get(position).getDate());
        holder.tv_2.setText(mDatas.get(position).getDes());
        holder.tv_score.setText("" + mDatas.get(position).getScore());
        holder.pic.setImageResource(mDatas.get(position).getPic());

        // 为carditem设置点击事件，传递tutor_id
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取tutor_id
                int tutorId = mDatas.get(position).getTutorid();
                //创建intent，跳转到tutor详情界面，并传递tutor_id
                Intent intent = new Intent(context, tutorhomepage.class);
                intent.putExtra("tutor_id", tutorId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_score, tv_1, tv_2;
        private ImageView pic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_score = itemView.findViewById(R.id.tv_score);
            tv_1 = itemView.findViewById(R.id.tv_1);
            tv_2 = itemView.findViewById(R.id.tv_2);
            pic = itemView.findViewById(R.id.pic);

        }
    }
}
