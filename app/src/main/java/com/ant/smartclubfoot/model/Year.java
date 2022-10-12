package com.ant.smartclubfoot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Year {

    @SerializedName("months")
    @Expose
    private List<String> months = null;
    @SerializedName("data")
    @Expose
    private List<Object> data = null;

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

}
