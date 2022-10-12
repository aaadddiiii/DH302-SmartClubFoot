package com.ant.smartclubfoot.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.ant.smartclubfoot.R;
import com.ant.smartclubfoot.api.ApiClient;
import com.ant.smartclubfoot.api.ApiUrls;
import com.ant.smartclubfoot.constants.Constants;
import com.ant.smartclubfoot.model.LoginObject;
import com.ant.smartclubfoot.utils.CommonUtils;

import org.json.JSONObject;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    private final String TAG = LoginActivity.class.getSimpleName();
    Button loginbutton;
    EditText etMobile;
    EditText etDob;

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        loginbutton = findViewById(R.id.btn_login);
        etMobile  = findViewById(R.id.et_mobile);
        etDob = findViewById(R.id.et_dob);

        datePickerDialog = new DatePickerDialog(
                this, LoginActivity.this,
                 Calendar.getInstance().get(Calendar.YEAR),
                 Calendar.getInstance().get(Calendar.MONTH),
                 Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        //TODO : implement this in a cleaner function
        etDob.setOnFocusChangeListener((view, b) -> {
            if (b) {
                CommonUtils.hideKeyboardFrom(LoginActivity.this, etMobile);
                etMobile.clearFocus();
                datePickerDialog.show();
            }
        });

        loginbutton.setOnClickListener(v -> loginnow());
        etDob.setOnClickListener((v -> showDatePickerDialog()));
    }

    public void showDatePickerDialog() {
        CommonUtils.hideKeyboardFrom(this, etMobile);
        etMobile.clearFocus();
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String d, m, y;
        y = String.valueOf(year);
        if (day < 10)
            d = "0" + day;
        else
            d = String.valueOf(day);
        if ((month + 1) < 10)
            m = "0" + (month + 1);
        else
            m = String.valueOf(month + 1);
        etDob.setText(y + "-" + m + "-" + d);
    }

    //TODO: the commented code below implements user authenticaton replace that wtih better code
    public void loginnow()
    {

        boolean isError = false;
        if (etMobile.getText().toString().isEmpty()) {
            etMobile.setError("Cannot be empty");
            isError = true;
            return;
        } else
            etMobile.setError(null);

        if (etDob.getText().toString().isEmpty()) {
            etDob.setError("Cannot be empty");
            isError = true;
            return;
        } else
            etDob.setError(null);

        if (!isError) {
            //makeLoginCall(etMobile.getText().toString(), etDob.getText().toString());
            startActivity(new Intent(LoginActivity.this, ConnectDeviceActivity.class));
        }


    }


//    @OnClick({R.id.btn_login})
//    public void login() {
//        boolean isError = false;
//        if (etMobile.getText().toString().isEmpty()) {
//            etMobile.setError("Cannot be empty");
//            isError = true;
//        } else
//            etMobile.setError(null);
//
//        if (etDob.getText().toString().isEmpty()) {
//            etDob.setError("Cannot be empty");
//            isError = true;
//        } else
//            etDob.setError(null);
//
//        if (!isError) {
//            makeLoginCall(etMobile.getText().toString(), etDob.getText().toString());
//        }
//    }
//
//    private void makeLoginCall(String username, String password) {
//        ApiUrls apiService =
//                ApiClient.getClient().create(ApiUrls.class);
//        Call<JSONObject> call = apiService.login(new LoginObject(username, password));
//        call.enqueue(new Callback<JSONObject>() {
//            @Override
//            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                if (response.code() == 200) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            SharedPreferences sharedPreferences = getSharedPreferences(Constants.SharedPrefConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//                            sharedPreferences.edit().putBoolean(Constants.SharedPrefConstants.IS_LOGGED_IN, true).apply();
//                            sharedPreferences.edit().putString(Constants.SharedPrefConstants.USERNAME, username).apply();
//                            startActivity(new Intent(LoginActivity.this, ConnectDeviceActivity.class));
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JSONObject> call, Throwable t) {
//                // Log error here since request failed
//                Log.e(TAG, t.toString());
//            }
//        });
//    }
}
