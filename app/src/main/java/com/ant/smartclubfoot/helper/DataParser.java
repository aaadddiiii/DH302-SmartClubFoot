package com.ant.smartclubfoot.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ant.smartclubfoot.api.ApiClient;
import com.ant.smartclubfoot.api.ApiUrls;
import com.ant.smartclubfoot.constants.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataParser {

    private final String TAG = DataParser.class.getSimpleName();
    private JSONArray collectionOfData;
    private DataParserCallback dataParserCallback;
    private Handler sendDataHandler;
    private Context context;
    private String address;

    public DataParser(Context context, DataParserCallback dataParserCallback) {
        this.context = context;
        this.dataParserCallback = dataParserCallback;
        sendDataHandler = new Handler();
    }

    public void addData(String data) {
        sendDataHandler.removeCallbacks(sendDataToServer);
        String[] rawData = data.split(",");
        if (rawData.length > 0) {
            if (collectionOfData == null)
                collectionOfData = new JSONArray();
            for (String rawDatum : rawData) {
                    try {
                        if(rawDatum.length() > 27) {
                            String[] processedData = rawDatum.split("\\s+");
                            if (processedData[processedData.length - 1].length() > 1) {
                                String c = processedData[processedData.length - 1].substring(0, 1);
                                processedData[processedData.length - 1] = c;
                            }
                            collectionOfData.put(processData(processedData[0], processedData[1], processedData[2], processedData[3], processedData[4], processedData[5], processedData[6]));
                        }
                    } catch (JSONException | ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
            }
        }
        sendDataHandler.postDelayed(sendDataToServer,5000);
    }

    private JSONObject processData(String srNo, String date, String time, String leftB, String leftF, String rightB, String rightF) throws JSONException {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SharedPrefConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(Constants.SharedPrefConstants.LAST_ROW,srNo).apply();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("date", date);
        jsonObject.put("time", time);
        jsonObject.put("leftF", leftF);
        jsonObject.put("leftB", leftB);
        jsonObject.put("rightF", rightF);
        jsonObject.put("rightB", rightB);
        return jsonObject;
    }

    Runnable sendDataToServer = new Runnable() {
        @Override
        public void run() {
            if (collectionOfData != null && collectionOfData.length() > 0)
                postReadings(collectionOfData);
        }
    };

    public void postReadings(JSONArray jsonArray) {
        ApiUrls apiService =
                ApiClient.getClient().create(ApiUrls.class);
        Call<JSONObject> call = apiService.postReading(address, (JsonArray) new JsonParser().parse(jsonArray.toString()));
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(@NonNull Call<JSONObject> call,@NonNull Response<JSONObject> response) {
                if (response.code() == 200) {
                    if (dataParserCallback != null)
                        dataParserCallback.dataParsingComplete();
                    Toast.makeText(context,"Api Call done",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JSONObject> call,@NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                if (dataParserCallback != null)
                    dataParserCallback.dataParsingFailed(t.getMessage());
            }
        });
    }

    public String getLastRowNo() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SharedPrefConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.SharedPrefConstants.LAST_ROW,"0");
    }

    public void setDeviceId(String address) {
        this.address = address;
    }

    public interface DataParserCallback {
        void dataParsingComplete();

        void dataParsingFailed(String error);
    }
}
