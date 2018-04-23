package com.example.newsapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.astuetz.PagerSlidingTabStrip;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ListViewFragment> list = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private ViewPager vp;
    private PagerSlidingTabStrip pst;
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp= (ViewPager) findViewById(R.id.viewPager);
        pst=(PagerSlidingTabStrip) findViewById(R.id.pst);
        init();
        MyFragmentViewAdapter ma = new MyFragmentViewAdapter(getSupportFragmentManager(),list,title);
        vp.setAdapter(ma);
        pst.setViewPager(vp);
    }

    private void init(){
        title.add("汽车");
        title.add("科技");
        title.add("城市");
        title.add("娱乐");;
        title.add("生活");
        title.add("政治");
        title.add("财经");
        title.add("其他");
        for(int i = 1;i<=8;i++){
            ListViewFragment fragment = new ListViewFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("arg",i);
            fragment.setArguments(bundle);
            list.add(fragment);
        }
    }
}
