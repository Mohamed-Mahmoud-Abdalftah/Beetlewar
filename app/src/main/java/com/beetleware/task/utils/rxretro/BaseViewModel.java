package com.beetleware.task.utils.rxretro;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.exceptions.OnErrorFailedException;

public class BaseViewModel extends ViewModel implements IBaseResponseListener {
    private static final String TAG = "BaseViewModel";
    /**
     * BaseActivity implements IBaseResponseListener
     * Server error from the ResponseListener wil e handled globally - not to implement in every activity.
     * Activities should extend BaseActivity
     */

    private Gson mGson;
    public MutableLiveData<Throwable> onFailureLiveData;


    protected Gson getGson(){
        if(mGson == null){
            mGson = new Gson();
        }
        return mGson;
    }
    public MutableLiveData<Throwable> onFailureData() {

        if (onFailureLiveData == null) {
            onFailureLiveData = new MutableLiveData<>();
        }
        return onFailureLiveData;

    }

    protected void showToast(String msg){
       // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void serverError(String serverResponse, int paramInt, int errorCode) {
        showToast(serverResponse + " ("+ errorCode + ") ");
    }

    /**
     * By implementing this method in BaseActivity will by default shows the server api call failure message
     * If needed , this method can be overridden in your own activity
     *
     * @param e - Server error message
     * @param requestId - Shows ID of the requested ApiCall
     */

    @Override
    public void onFailure(Throwable e, int requestId) {
        try {

            if (e instanceof OnErrorFailedException) {
                Log.e(TAG, "onError: instanceof OnErrorFailedException");
            } else if (e instanceof retrofit2.HttpException) {
                Log.e(TAG, "onError: instanceof HttpException");
                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                if (responseBody != null) {
                    String errorStr = getStringFromByte(getByteStream(responseBody));
                    Log.e(TAG, "onError: error msg" + errorStr);
                }
            } else {
                if (onFailureLiveData != null) {
                    onFailureLiveData.setValue(e);
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, "onError: "+ e.getLocalizedMessage() );
        }
    }





    public String getStringFromByte(InputStream paramInputStream) {
        StringBuilder localStringBuilder = new StringBuilder();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
        try {
            while (true) {
                String str = localBufferedReader.readLine();
                if (str == null)
                    break;
                localStringBuilder.append(str);
            }
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return localStringBuilder.toString();
    }

    public InputStream getByteStream(ResponseBody paramResponse) {
        return paramResponse.byteStream();
    }


}
