package com.example.lenovo.day04.ui.v2ex.activity;

/**
 * 杨博钦
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.lenovo.day04.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class FadeActivity extends AppCompatActivity {

    private static final String TAG = "FadeActivity";

    private String mUrl="https://www.v2ex.com/?tab=creative";

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade);
        initView();
        initData();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("节点导航");

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    Elements elements = doc.select("div#Main div.box");
                    Element element1 = elements.get(1);

                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
                    Elements cells = element1.select("div.cell");
                    for (int i = 1; i < cells.size(); i++) {

                        // 分享与探索、V2EX、iOS、Geek、游戏、Apple、生活、Internet、城市
                        String title = cells.get(i).select("table tr td span").text();
                        titles.add(title);

                        ArrayList<String> arrayList = new ArrayList<>();
                        Elements articles = cells.get(i).select("table tr td a");
                        for (int j = 0; j < articles.size(); j++) {
                            String text = articles.get(j).text();
                            arrayList.add(text);
                        }
                        arrayLists.add(arrayList);
                    }

                    // 品牌
                    String title = element1.select("div.inner table tr td span").text();
                    titles.add(title);

                    ArrayList<String> arrayList = new ArrayList<>();
                    Elements select = element1.select("div.inner table tr td a");
                    for (int i = 0; i < select.size(); i++) {
                        String text = select.get(i).text();
                        arrayList.add(text);
                    }
                    arrayLists.add(arrayList);

                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
