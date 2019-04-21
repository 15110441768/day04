package com.example.lenovo.day04.ui.v2ex.bean;

public class ArticleBean {
    String src;
    String title;
    String author;
    String node;
    String txt;
    String livid;

    public ArticleBean(String src, String title, String author, String node, String txt, String livid) {
        this.src = src;
        this.title = title;
        this.author = author;
        this.node = node;
        this.txt = txt;
        this.livid = livid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getLivid() {
        return livid;
    }

    public void setLivid(String livid) {
        this.livid = livid;
    }
}
