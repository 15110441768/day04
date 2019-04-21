package com.example.lenovo.day04.ui.v2ex.bean;

public class TabsBean {
    String linkHref;
    String linkText;

    public TabsBean(String linkHref, String linkText) {
        this.linkHref = linkHref;
        this.linkText = linkText;
    }

    public String getLinkHref() {
        return linkHref;
    }

    public void setLinkHref(String linkHref) {
        this.linkHref = linkHref;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }
}
