package com.example.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import com.example.database.fragment.ScheduleFragment;
import com.example.database.fragment.SearchingFragment;
import com.example.database.fragment.SelfFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragments = new ArrayList();
    private ArrayList<String> titles = new ArrayList();
    private ArrayList<Integer> tabIcons = new ArrayList();

    private ViewPager viewPager;
    private TabLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.viewpager);
        tableLayout=findViewById(R.id.table);

        initFragment();

    }

    private void initFragment(){
        fragments.add(new SearchingFragment());
        fragments.add(new ScheduleFragment());
        fragments.add(new SelfFragment());

        titles.add("Search");
        titles.add("Schedule");
        titles.add("Self");

        tabIcons.add(R.drawable.select_tab_home);
        tabIcons.add(R.drawable.select_tab_konw);
        tabIcons.add(R.drawable.select_tab_mine);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), fragments, titles));
        tableLayout.setupWithViewPager(viewPager);


        tableLayout.getTabAt(0).setCustomView(getTabView(0));
        tableLayout.getTabAt(1).setCustomView(getTabView(1));
        tableLayout.getTabAt(2).setCustomView(getTabView(2));
    }

    class MyAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragment;
        private List<String> titles;

        public MyAdapter(FragmentManager fm,List<Fragment> fragment,List<String> titles) {
            super(fm);
            this.fragment=fragment;
            this.titles=titles;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragment.get(position);
        }

        @Override
        public int getCount() {
            return fragment.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);

        }
    }

    private View getTabView(int position){
        View view  =LayoutInflater.from(this).inflate(R.layout.layout_table_custom, null);
        TextView title = view.findViewById(R.id.txt_title);
        title.setText(titles.get(position));
        ImageView img = view.findViewById(R.id.img_title);
        img.setImageResource(tabIcons.get(position));
        return view;
    }
}