package com.ant.smartclubfoot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Day {

    @SerializedName("hours")
    @Expose
    private List<String> hours = null;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
