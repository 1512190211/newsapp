package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ListViewFragment extends Fragment {
    private List<News> newsList = new ArrayList<>();
    View view;

    public ListViewFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String url = "http://v.juhe.cn/weixin/query?key=007bd6689229c51bea01dd5d1d12c3bf";
        RxVolley.get(url, new HttpCallback(){
            @Override
            public void onSuccess(String t) {
                Toast.makeText(getContext(),"成功",Toast.LENGTH_SHORT).show();
                Log.i("ListViewFragment", "Json" + t);
                parsingJson(t);
            }
        });
        ListView Flistview = (ListView) getView().findViewById(R.id.fragment_listview);
        NewsAdapter adapter = new NewsAdapter(getActivity(), R.layout.news_item, newsList);
        Flistview.setAdapter(adapter);
        Flistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = newsList.get(position);
                Toast.makeText(getContext(), "跳转中...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ContentActivity.class);
                intent.putExtra(ContentActivity.LINK_NAME, news.getUrl());
                startActivity(intent);
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObhect = new JSONObject(t);
            JSONObject jsonresult = jsonObhect.getJSONObject("result");
            JSONArray jsonArray = jsonresult.getJSONArray("list");
            newsList.clear();
            //拿到返回值
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                String title = object.getString("title");
                String source = object.getString("source");
                String firstImg = object.getString("firstImg");
                String url = object.getString("url");
                Bundle bundle = getArguments();
                int arg = bundle.getInt("arg");
                if (source.contains("车") || title.contains("车")) {
                    if (arg == 1) {
                        News a = new News(title, source, firstImg, url);
                        newsList.add(a);
                    }
                } else if (title.contains("科技") || source.contains("科技")||source.contains("科学")) {
                    if (arg == 2) {
                        News a = new News(title, source, firstImg, url);
                        newsList.add(a);
                    }
                } else if (title.contains("城")  || source.contains("报")) {
                    if (arg == 3) {
                        News a = new News(title, source, firstImg, url);
                        newsList.add(a);
                    }
                } else if (title.contains("娱乐") || source.contains("娱乐")||source.contains("猫")||source.contains("狗")||source.contains("控")) {
                    if (arg == 4) {
                        News a = new News(title, source, firstImg, url);
                        newsList.add(a);
                    }
                } else if (title.contains("生活") || title.contains("日子") || title.contains("岁月") || source.contains("生活")) {
                    if (arg == 5) {
                        News a = new News(title, source, firstImg, url);
                        newsList.add(a);
                    }
                } else if (title.contains("政治") ||title.contains("中国")|| title.contains("央视") || title.contains("国家")) {
                    if (arg == 6) {
                        News a = new News(title, source, firstImg, url);
                        newsList.add(a);
                    }
                } else if (title.contains("经济") || title.contains("资产") || title.contains("国家")) {
                    if (arg == 7) {
                        News a = new News(title, source, firstImg, url);
                        newsList.add(a);
                    }
                } else {
                    if (arg == 8) {
                        News a = new News(title, source, firstImg, url);
                        newsList.add(a);
                    }
                }
            }
        }
         catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
