package com.ant.smartclubfoot.model;

import com.google.gson.annotations.SerializedName;

public class ReadingObject {

    @SerializedName("device_id")
    String deviceId;
    @SerializedName("leftF")
    String leftF;
    @SerializedName("leftB")
    String leftB;
    @SerializedName("rightF")
    String rightF;
    @SerializedName("rightB")
    String rightB;
    @SerializedName("timestamp")
    String timestamp;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLeftF() {
        return leftF;
    }

    public void setLeftF(String leftF) {
        this.leftF = leftF;
    }

    public String getLeftB() {
        return leftB;
    }

    public void setLeftB(String leftB) {
        this.leftB = leftB;
    }

    public String getRightF() {
        return rightF;
    }

    public void setRightF(String rightF) {
        this.rightF = rightF;
    }

    public String getRightB() {
        return rightB;
    }

    public void setRightB(String rightB) {
        this.rightB = rightB;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
