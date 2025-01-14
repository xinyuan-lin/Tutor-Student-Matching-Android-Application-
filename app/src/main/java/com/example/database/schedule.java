package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class schedule extends AppCompatActivity {

    private CardAdapterSch cardAdapterSch;
    private RecyclerView recyclerView;
    private List<CardBeanSch> cardBeansch = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        recyclerView = findViewById(R.id.mRecyclerView);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        int userID = sharedPreferences.getInt("id", -1);
        String userName = sharedPreferences.getString("name","");
        String userEmail = sharedPreferences.getString("userEmail","");

        CardBeanSch cardBeanSch = new CardBeanSch();
        cardBeanSch.setPic(R.mipmap.ttpic2);
        cardBeanSch.setName("Leslie Alexander");
        cardBeanSch.setDate("2023-4-30");
        cardBeanSch.setTime("11:00AM");
        cardBeanSch.setSubject("English");
        cardBeansch.add(cardBeanSch);

        CardBeanSch cardBeanSch1 = new CardBeanSch();
        cardBeanSch1.setPic(R.mipmap.ttpic1);
        cardBeanSch1.setName("Wade Warren");
        cardBeanSch1.setDate("2023-4-30");
        cardBeanSch1.setTime("2:00PM");
        cardBeanSch1.setSubject("Python");
        cardBeansch.add(cardBeanSch1);

        CardBeanSch cardBeanSch2 = new CardBeanSch();
        cardBeanSch2.setPic(R.mipmap.ttpic1);
        cardBeanSch2.setName("Wade Warren");
        cardBeanSch2.setDate("2023-5-2");
        cardBeanSch2.setTime("10:00AM");
        cardBeanSch2.setSubject("Python");
        cardBeansch.add(cardBeanSch2);

        cardAdapterSch = new CardAdapterSch(this, cardBeansch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardAdapterSch);
    }
}