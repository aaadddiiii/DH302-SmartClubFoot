package com.ant.smartclubfoot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReadingsNew {

    @SerializedName("categories")
    @Expose
    public List<String> categories = null;
    @SerializedName("data")
    @Expose
    public List<List<Double>> data = null;

}
