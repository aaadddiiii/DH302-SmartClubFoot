package com.ant.smartclubfoot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("hour")
    @Expose
    private float hour;
    @SerializedName("count")
    @Expose
    private float count;
    @SerializedName("leftF")
    @Expose
    private float leftF;
    @SerializedName("leftB")
    @Expose
    private float leftB;
    @SerializedName("rightF")
    @Expose
    private float rightF;
    @SerializedName("rightB")
    @Expose
    private float rightB;

    public float getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public float getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public float getLeftF() {
        return leftF;
    }

    public void setLeftF(Integer leftF) {
        this.leftF = leftF;
    }

    public float getLeftB() {
        return leftB;
    }

    public void setLeftB(Integer leftB) {
        this.leftB = leftB;
    }

    public float getRightF() {
        return rightF;
    }

    public void setRightF(Integer rightF) {
        this.rightF = rightF;
    }

    public float getRightB() {
        return rightB;
    }

    public void setRightB(Integer rightB) {
        this.rightB = rightB;
    }

}
