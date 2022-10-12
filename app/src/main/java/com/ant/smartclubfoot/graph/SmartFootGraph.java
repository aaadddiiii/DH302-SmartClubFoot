package com.ant.smartclubfoot.graph;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ant.smartclubfoot.R;

public class SmartFootGraph extends LinearLayout implements View.OnClickListener {

    private LinearLayout topLL, bottomLL;
    private TextView leftLeg, leftShoe, rightLeg, rightShoe;
    private TextView date;

    public SmartFootGraph(Context context) {
        this(context, null);
    }

    public SmartFootGraph(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartFootGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        View view = inflate(getContext(), R.layout.smartfoot_graph, this);
        topLL = view.findViewById(R.id.topLL);
        bottomLL = view.findViewById(R.id.bottomLL);
        leftLeg = view.findViewById(R.id.leftLeg);
        leftShoe = view.findViewById(R.id.leftShoe);
        rightLeg = view.findViewById(R.id.rightLeg);
        rightShoe = view.findViewById(R.id.rightShoe);
        leftLeg.setOnClickListener(this);
        leftShoe.setOnClickListener(this);
        rightLeg.setOnClickListener(this);
        rightShoe.setOnClickListener(this);
        date = view.findViewById(R.id.tv_date);
    }

    public void setTotalHours(float totalHours) {
        topLL.setWeightSum(totalHours);
        bottomLL.setWeightSum(totalHours);
    }

    public void setDate(String dateStr) {
        date.setText(dateStr);
    }

    private LinearLayout.LayoutParams getDefaultLayoutParam(float weight) {
        return new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT,
                weight
        );
    }

    public void setLeftLeg(float weight) {
        leftLeg.setText("Left Leg(" + String.valueOf(weight) + ")");
        leftLeg.setLayoutParams(getDefaultLayoutParam(weight));
    }

    public void setLeftShoe(float weight) {
        leftShoe.setText("Left Shoe(" + String.valueOf(weight) + ")");
        leftShoe.setLayoutParams(getDefaultLayoutParam(weight));
    }

    public void setRightLeg(float weight) {
        rightLeg.setText("Right Leg(" + String.valueOf(weight) + ")");
        rightLeg.setLayoutParams(getDefaultLayoutParam(weight));
    }

    public void setRightShoe(float weight) {
        rightShoe.setText("Right Shoe(" + String.valueOf(weight) + ")");
        rightShoe.setLayoutParams(getDefaultLayoutParam(weight));
    }

    @Override
    public void onClick(View v) {
        String text = "";
        switch (v.getId()) {
            case R.id.leftLeg:
                text = leftLeg.getText().toString();
                break;

            case R.id.leftShoe:
                text = leftShoe.getText().toString();
                break;

            case R.id.rightLeg:
                text = rightLeg.getText().toString();
                break;

            case R.id.rightShoe:
                text = rightShoe.getText().toString();
                break;
        }
        if (!text.isEmpty())
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
