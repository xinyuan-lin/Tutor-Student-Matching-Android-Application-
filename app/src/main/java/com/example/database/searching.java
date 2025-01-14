package com.example.database;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.database.javabean.Tutor;

import java.util.ArrayList;
import java.util.List;

public class searching extends AppCompatActivity {
    private MYsqliteopenhelper mYsqliteopenhelper1;
    private CardAdapter cardAdapter;
    private RecyclerView recyclerView;
    private List<CardBean> cardBeans = new ArrayList<>();
    private EditText searchcon;
    private ImageView searchimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
        searchcon = findViewById(R.id.searchcontent);
        searchimg = findViewById(R.id.searchimg);
        recyclerView = findViewById(R.id.mRecyclerView);
        String content = searchcon.getText().toString().trim();

        searchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(content)){
                    List<CardBean> cardBeans = mYsqliteopenhelper1.searchTutorsByName(content);
                }
            }
        });

        cardAdapter = new CardAdapter(this, cardBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardAdapter);

    }

}