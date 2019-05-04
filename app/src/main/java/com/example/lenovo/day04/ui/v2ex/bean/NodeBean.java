package com.example.lenovo.day04.ui.v2ex.bean;

import java.util.ArrayList;

public class NodeBean {
    String header;
    ArrayList<String> list;

    public NodeBean() {
    }

    public NodeBean(String header, ArrayList<String> list) {
        this.header = header;
        this.list = list;
    }

    public String getHeader() {

        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}
