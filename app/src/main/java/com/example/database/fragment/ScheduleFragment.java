package com.example.database.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.CardAdapterSch;
import com.example.database.CardBean;
import com.example.database.CardBeanSch;
import com.example.database.MYsqliteopenhelper;
import com.example.database.R;
import com.example.database.javabean.Student;
import com.example.database.javabean.Tutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleFragment extends Fragment {
    private CardAdapterSch cardAdapterSch;
    private RecyclerView recyclerView;
    private List<CardBeanSch> cardBeansch = new ArrayList<>();
    private MYsqliteopenhelper mYsqliteopenhelper1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.mRecyclerView);
        mYsqliteopenhelper1 = new MYsqliteopenhelper(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int userID = sharedPreferences.getInt("id", 0);
        int type = sharedPreferences.getInt("type", 0);
        String userName = sharedPreferences.getString("name","");
        String userEmail = sharedPreferences.getString("userEmail","");

        int studentId = 0;
        int tutorId = 0;

        switch (type){
            case 0:
                Student student = mYsqliteopenhelper1.getStudentById(userID);
                studentId = student.getStudent_id();
                cardBeansch.addAll(mYsqliteopenhelper1.getStudentSchedules(studentId));
                Collections.sort(cardBeansch, new CardBeanSchComparator());
                break;
            case 1:
                Tutor tutor = mYsqliteopenhelper1.getTutorById(userID);
                tutorId = tutor.getTutor_id();
                cardBeansch.addAll(mYsqliteopenhelper1.getTutorSchedules(tutorId));
                Collections.sort(cardBeansch, new CardBeanSchComparator());
                break;
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // 如果滚动到了底部，就刷新数据源
                if (!recyclerView.canScrollVertically(1)) {
                    refreshData();
                }
            }
        });


        cardAdapterSch = new CardAdapterSch(getActivity(), cardBeansch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cardAdapterSch);
    }

    private void refreshData() {
        cardBeansch.clear();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int userID = sharedPreferences.getInt("id", 0);
        int type = sharedPreferences.getInt("type", 0);
        String userName = sharedPreferences.getString("name","");
        String userEmail = sharedPreferences.getString("userEmail","");

        int studentId = 0;
        int tutorId = 0;

        switch (type){
            case 0:
                Student student = mYsqliteopenhelper1.getStudentById(userID);
                studentId = student.getStudent_id();
                cardBeansch.addAll(mYsqliteopenhelper1.getStudentSchedules(studentId));
                Collections.sort(cardBeansch, new CardBeanSchComparator());
                break;
            case 1:
                Tutor tutor = mYsqliteopenhelper1.getTutorById(userID);
                tutorId = tutor.getTutor_id();
                cardBeansch.addAll(mYsqliteopenhelper1.getTutorSchedules(tutorId));
                Collections.sort(cardBeansch, new CardBeanSchComparator());
                break;
        }

        cardAdapterSch.notifyDataSetChanged();
    }

    private class CardBeanSchComparator implements Comparator<CardBeanSch> {
        @Override
        public int compare(CardBeanSch cardBeansch1, CardBeanSch cardBeansch2) {
            String dateTime1 = cardBeansch1.getDate() + cardBeansch1.getTime();
            String dateTime2 = cardBeansch2.getDate() + cardBeansch2.getTime();
            return dateTime1.compareTo(dateTime2);
        }
    }





}
