package com.beetleware.task.ui.home.homeFragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class model {
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("status")
    @Expose
    private String status;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

