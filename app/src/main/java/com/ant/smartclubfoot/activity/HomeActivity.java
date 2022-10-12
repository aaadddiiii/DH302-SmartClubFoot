package com.ant.smartclubfoot.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.ant.smartclubfoot.R;
import com.ant.smartclubfoot.api.ApiClient;
import com.ant.smartclubfoot.api.ApiUrls;
import com.ant.smartclubfoot.constants.Constants;
import com.ant.smartclubfoot.datastore.DateHelper;
import com.ant.smartclubfoot.datastore.SmartFootStore;
import com.ant.smartclubfoot.graph.SmartFootGraph;
import com.ant.smartclubfoot.model.ReadingsNew;
import com.ant.smartclubfoot.service.UartService;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    private final String TAG = HomeActivity.class.getSimpleName();
    @BindView(R.id.tv_error)
    TextView errorTv;
    @BindView(R.id.sfg_day1)
    SmartFootGraph sfgDay1;
    @BindView(R.id.sfg_day2)
    SmartFootGraph sfgDay2;
    @BindView(R.id.sfg_day3)
    SmartFootGraph sfgDay3;
    @BindView(R.id.sfg_day4)
    SmartFootGraph sfgDay4;
    @BindView(R.id.sfg_day5)
    SmartFootGraph sfgDay5;
    @BindView(R.id.sfg_day6)
    SmartFootGraph sfgDay6;
    @BindView(R.id.sfg_day7)
    SmartFootGraph sfgDay7;
    @BindView(R.id.tv_total)
    TextView totalTv;
    @BindView(R.id.tv_midPoint)
    TextView midPoint;
    @BindView(R.id.header_date)
    TextView headerDate;

    private DateHelper dateHelper;
    Button logoutbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /*myAlarmManager = new MyAlarmManager();
        myAlarmManager.setAlarmManager(this, false);*/
        dateHelper = new DateHelper();
        getReadings(dateHelper.getCurrentDate());
        getReadingFromLocal();
        findViewById(R.id.iv_left).setOnClickListener(v -> getReadings(dateHelper.getPreviousDate()));
        findViewById(R.id.iv_right).setOnClickListener(v -> getReadings(dateHelper.getNextDate()));
        logoutbutton = findViewById(R.id.btn_logout);


        LocalBroadcastManager.getInstance(this).registerReceiver(UART_DATA_RECEIVER, makeGattUpdateIntentFilter());
        logoutbutton.setOnClickListener((v -> logout()));
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UartService.NEW_DATA_POINTS_ADDED);
        return intentFilter;
    }

    private final BroadcastReceiver UART_DATA_RECEIVER = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //*********************//
            if (action.equals(UartService.NEW_DATA_POINTS_ADDED)) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        getReadings(dateHelper.getCurrentDate());
                    }
                });
            }
        }
    };

    private void getReadingFromLocal() {
        ArrayList<String> data = new SmartFootStore().getDataValues(HomeActivity.this);
        if (data != null) {
            float totalFloat = new SmartFootStore().getTotal(HomeActivity.this);
            double total = Math.ceil(totalFloat);
            totalTv.setText(String.valueOf(total));
            midPoint.setText(String.valueOf(total / 2));
            for (int i = 0; i < 7; i++) {
                String d = data.get(i);
                if (!d.isEmpty()) {
                    String[] dSplit = d.split(",");
                    String date = dSplit[0];
                    getGraphForDay(i).setDate(date);
                    getGraphForDay(i).setTotalHours((float) total);
                    getGraphForDay(i).setLeftLeg(Float.parseFloat(dSplit[1]));
                    getGraphForDay(i).setLeftShoe(Float.parseFloat(dSplit[2]));
                    getGraphForDay(i).setRightLeg(Float.parseFloat(dSplit[3]));
                    getGraphForDay(i).setRightShoe(Float.parseFloat(dSplit[4]));
                }
            }
        }
    }

    private SmartFootGraph getGraphForDay(int day) {
        if (day == 0)
            return sfgDay1;
        if (day == 1)
            return sfgDay2;
        if (day == 2)
            return sfgDay3;
        if (day == 3)
            return sfgDay4;
        if (day == 4)
            return sfgDay5;
        if (day == 5)
            return sfgDay6;
        if (day == 6)
            return sfgDay7;
        return null;
    }

    public void logout() {
        new SmartFootStore().clear(this);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SharedPrefConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(Constants.SharedPrefConstants.BLE_ADDRESS, "").apply();
        sharedPreferences.edit().putBoolean(Constants.SharedPrefConstants.IS_LOGGED_IN, false).apply();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(UART_DATA_RECEIVER);
        } catch (Exception ignore) {
            Log.e(TAG, ignore.toString());
        }
        //myAlarmManager.unregisterAlarmBroadcast(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void getReadings(String date) {
        ApiUrls apiService =
                ApiClient.getClient().create(ApiUrls.class);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SharedPrefConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String deviceID = sharedPreferences.getString(Constants.SharedPrefConstants.BLE_ADDRESS, "");
        Call<ReadingsNew> call = apiService.getReading(deviceID, date);
        call.enqueue(new Callback<ReadingsNew>() {
            @Override
            public void onResponse(Call<ReadingsNew> call, Response<ReadingsNew> response) {
                if (response.body() != null) {
                    double total = 0;
                    for (int i = 0; i < 7; i++) {
                        Double leftL = 0.0, leftS = 0.0, rightL = 0.0, rightS = 0.0;
                        String date = response.body().categories.get(i);
                        if (i == 0) {
                            dateHelper.startDate(date);
                        } else if (i == 6) {
                            dateHelper.endDate(date);
                        }
                        leftL = response.body().data.get(0).get(i);
                        leftS = response.body().data.get(1).get(i);
                        rightL = response.body().data.get(2).get(i);
                        rightS = response.body().data.get(3).get(i);
                        if (leftL + rightL > leftS + rightS) {
                            if (total == 0)
                                total = leftL + rightL;
                            else if (total < leftL + rightL)
                                total = leftL + rightL;
                        } else {
                            if (total == 0)
                                total = leftS + rightS;
                            else if (total < leftS + rightS)
                                total = leftS + rightS;
                        }
                        new SmartFootStore().saveDataValues(HomeActivity.this, i + 1, date + "," + leftL + "," + leftS + "," + rightL + "," + rightS);
                    }

                    new SmartFootStore().saveTotal(HomeActivity.this, total);
                    if (HomeActivity.this != null && !HomeActivity.this.isFinishing())
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                headerDate.setText(dateHelper.getStartDate() + " - " + dateHelper.getEndDate());
                                getReadingFromLocal();
                            }
                        });
                }
            }

            @Override
            public void onFailure(Call<ReadingsNew> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
