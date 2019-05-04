package com.example.lenovo.day04.ui.zhihu.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DailyNewsArticleBean {
    @Id
    private Long newId;
    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private String images;
    private String css;
    @Generated(hash = 278847240)
    public DailyNewsArticleBean(Long newId, String body, String image_source,
            String title, String image, String share_url, String ga_prefix,
            int type, int id, String images, String css) {
        this.newId = newId;
        this.body = body;
        this.image_source = image_source;
        this.title = title;
        this.image = image;
        this.share_url = share_url;
        this.ga_prefix = ga_prefix;
        this.type = type;
        this.id = id;
        this.images = images;
        this.css = css;
    }
    @Generated(hash = 453909983)
    public DailyNewsArticleBean() {
    }
    public Long getNewId() {
        return this.newId;
    }
    public void setNewId(Long newId) {
        this.newId = newId;
    }
    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getImage_source() {
        return this.image_source;
    }
    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getShare_url() {
        return this.share_url;
    }
    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
    public String getGa_prefix() {
        return this.ga_prefix;
    }
    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImages() {
        return this.images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public String getCss() {
        return this.css;
    }
    public void setCss(String css) {
        this.css = css;
    }
}
