package com.otavio.iddog.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Dogs {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("list")
    @Expose
    private List<String> urlsPhotos;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getList() {
        return urlsPhotos;
    }

    public void setList(List<String> list) {
        this.urlsPhotos = list;
    }

}