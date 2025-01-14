package com.example.database.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.CardAdapter;
import com.example.database.CardBean;
import com.example.database.MYsqliteopenhelper;
import com.example.database.R;
import com.example.database.tutorhomepage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchingFragment extends Fragment {
    private MYsqliteopenhelper mYsqliteopenhelper1;
    private CardAdapter cardAdapter;
    private RecyclerView recyclerView;
    private List<CardBean> cardBeans = new ArrayList<>();
    private EditText searchcon;
    private ImageView searchimg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_searching, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mYsqliteopenhelper1 = new MYsqliteopenhelper(getActivity());

        searchcon = view.findViewById(R.id.searchcontent);
        searchimg = view.findViewById(R.id.searchimg);
        recyclerView = view.findViewById(R.id.mRecyclerView);


        /*
        * recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), tutorhomepage.class);
                startActivity(intent);
            }
        });

        */

        searchimg.setOnClickListener(view1 -> {
            String content = searchcon.getText().toString().trim();
            if (!TextUtils.isEmpty(content)) {
                List<CardBean> list = mYsqliteopenhelper1.searchTutorsByName(content);
                if (list.isEmpty()) {
                    Toast.makeText(getActivity(), "There's no match", Toast.LENGTH_SHORT).show();
                } else {
                    cardBeans.clear();
                    cardBeans.addAll(list);
                    Collections.sort(cardBeans, new CardBeanComparator()); // 对列表进行排序
                    cardAdapter.notifyDataSetChanged();
                }
            } else {
                cardAdapter.notifyDataSetChanged();
            }
        });







        cardAdapter = new CardAdapter(getActivity(), cardBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cardAdapter);

    }

    private class CardBeanComparator implements Comparator<CardBean> {
        @Override
        public int compare(CardBean cardBean1, CardBean cardBean2) {
            return cardBean2.getScore() - cardBean1.getScore();
        }
    }


}
