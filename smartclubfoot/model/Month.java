package com.ant.smartclubfoot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Month {

    @SerializedName("days")
    @Expose
    private List<Integer> days = null;
    @SerializedName("data")
    @Expose
    private List<Object> data = null;

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

}
