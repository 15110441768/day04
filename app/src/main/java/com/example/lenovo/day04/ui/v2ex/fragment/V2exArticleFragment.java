package com.example.lenovo.day04.ui.v2ex.fragment;

/**
 *  杨博钦
 */

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.ui.v2ex.adapter.V2exRecyclerViewAdapter;
import com.example.lenovo.day04.ui.v2ex.bean.ArticleBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class V2exArticleFragment extends Fragment {

    private static final String TAG = "V2exArticleFragment";

    private RecyclerView recyclerview;

    String linkHref;
    private ArrayList<ArticleBean> recyclerviewList;
    private V2exRecyclerViewAdapter v2exRecyclerViewAdapter;

    @SuppressLint("ValidFragment")
    public V2exArticleFragment(String linkHref) {
        this.linkHref = linkHref;
//        Log.e(TAG, "V2exArticleFragment: " + linkHref);
    }

    public V2exArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_v2ex_article, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerviewList = new ArrayList<>();

        v2exRecyclerViewAdapter = new V2exRecyclerViewAdapter(recyclerviewList, getActivity());
        recyclerview.setAdapter(v2exRecyclerViewAdapter);

    }

    private void initData() {
        final String mUrl="https://www.v2ex.com"+linkHref;
        final ArrayList<ArticleBean> articleBeans = new ArrayList<>();
        new Thread(new Runnable() {

            private String txt;
            private String livid;
            private String critic;
            private String author;

            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
//                    Log.e(TAG, "run: " + mUrl);
                    Elements elements = doc.select("div.cell.item");
                    for (Element element:elements) {
                        // 图片
                        Element image = element.select("table tr td a img").first();
                        String src = image.attr("src");
//                        Log.e(TAG, "图片: " + src);

                        // 标题
                        String title = element.select("table tr td span.item_title").first().text();
                        // " 分享创造 "
                        String node = element.select("table tr td span.topic_info a.node").first().text();

                        Elements strong = element.select("table tr td span strong");
                        if (strong.size()>0){
                            // 作者
                            author = strong.get(0).text();
                        }
                        if (strong.size()>1){
                            // 最后的评论者
                            critic = strong.get(1).text();
                        }

                        Elements tds = element.select("table tr td a.count_livid");
//                        Log.e(TAG, "run: " + tds.size());
                        if (tds.size()>0){
                            // 评论数
                            livid = element.select("table tr td a.count_livid").first().text();
                        }else {
                            livid="";
                        }

//                        Log.e(TAG, "标题: " +title+",分享创造"+node+",作者"+author+",最后的评论者"+critic+",评论数"+livid );

                        String text = element.select("table tr td span.topic_info").first().text();
                        String[] split = text.split("•");
                        if (split.length>2){
                            txt = split[2]+"·"+split[3];
                        }else {
                            txt="";
                        }
//                        Log.e(TAG, "txt: " + txt);
//                        Log.e(TAG, "text: " + text);
                        ArticleBean articleBean = new ArticleBean(src, title, author, node, txt, livid);
                        articleBeans.add(articleBean);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerviewList.addAll(articleBeans);
                            v2exRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
