package com.ant.smartclubfoot.activity;
import com.google.firebase.Timestamp;
public class Cloud_Data {
    String LeftLeg;
    String RightLeg;
    String LeftShoe;
    String RightShoe;

    com.google.firebase.Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getLeftLeg() {
        return LeftLeg;
    }

    public void setLeftLeg(String leftLeg) {
        LeftLeg = leftLeg;
    }

    public String getRightLeg() {
        return RightLeg;
    }

    public void setRightLeg(String rightLeg) {
        RightLeg = rightLeg;
    }

    public String getLeftShoe() {
        return LeftShoe;
    }

    public void setLeftShoe(String leftShoe) {
        LeftShoe = leftShoe;
    }

    public String getRightShoe() {
        return RightShoe;
    }

    public void setRightShoe(String rightShoe) {
        RightShoe = rightShoe;
    }
}
