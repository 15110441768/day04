package com.example.lenovo.day04.ui.v2ex.activity;

/**
 * 杨博钦
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.ui.v2ex.adapter.V2exNodeRecyclerViewAdapter;
import com.example.lenovo.day04.ui.v2ex.bean.NodeBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import qdx.stickyheaderdecoration.NormalDecoration;

public class FadeActivity extends AppCompatActivity {

    private static final String TAG = "FadeActivity";

    private String mUrl="https://www.v2ex.com/?tab=creative";

    private RecyclerView recyclerview;
    private ArrayList<NodeBean> recyclerviewList;
    private V2exNodeRecyclerViewAdapter v2exNodeRecyclerViewAdapter;

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
        recyclerviewList = new ArrayList<>();

        setData();
    }

    private void setData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(FadeActivity.this));
        v2exNodeRecyclerViewAdapter = new V2exNodeRecyclerViewAdapter(recyclerviewList, FadeActivity.this);
        //返回头布局的内容
        final NormalDecoration decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return recyclerviewList.get(i).getHeader();
            }
        };
        //自定义头布局,可不设置
//        decoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
//            @Override
//            public View getHeaderView(final int i) {
//                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_header, null);
//                TextView tv = inflate.findViewById(R.id.tv);
//                tv.setText(mCars.get(i).header);
//
//                return inflate;
//            }
//        });

        recyclerview.addItemDecoration(decoration);
        //头布局的点击事件
//        decoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
//            @Override
//            public void headerClick(int i) {
//                Toast.makeText(getContext(), mCars.get(i).header, Toast.LENGTH_SHORT).show();
//                //startActivity(new Intent(getContext(), FlowActivity.class));
//                startActivity(new Intent(getContext(), MaterialActivity.class));
//            }
//        });
        recyclerview.setAdapter(v2exNodeRecyclerViewAdapter);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    Elements elements = doc.select("div#Main div.box");
                    Element element1 = elements.get(1);

                    final ArrayList<String> titles = new ArrayList<>();
                    final ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
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

                    runOnUiThread(new Runnable() {

                        private NodeBean nodeBean;
                        private ArrayList<String> list;
                        private String title;

                        @Override
                        public void run() {
                            ArrayList<NodeBean> nodeBeans = new ArrayList<>();
                            for (int i = 0; i < titles.size(); i++) {
                                title = titles.get(i);
                                nodeBean = new NodeBean();
                                nodeBean.setHeader(title);
                                nodeBeans.add(nodeBean);
                            }
                            for (int j = 0; j < arrayLists.size(); j++) {
                                list = arrayLists.get(j);
                                nodeBean.setList(list);
                                NodeBean nodeBean = nodeBeans.get(j);
                                nodeBean.setList(list);
                            }
                            Log.e(TAG, "run: " +arrayLists.size());
                            recyclerviewList.addAll(nodeBeans);
                            v2exNodeRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    });

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
