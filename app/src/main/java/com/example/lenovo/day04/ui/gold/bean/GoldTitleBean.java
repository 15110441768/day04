package com.example.lenovo.day04.ui.gold.bean;

import java.io.Serializable;

public class GoldTitleBean implements Serializable{
    public int title;
    public boolean isChecked;

    public GoldTitleBean(int title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
