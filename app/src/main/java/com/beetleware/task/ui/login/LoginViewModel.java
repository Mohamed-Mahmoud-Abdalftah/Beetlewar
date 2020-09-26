package com.beetleware.task.ui.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.beetleware.task.data.network.ApiFactory;
import com.beetleware.task.data.network.UsersService;
import com.beetleware.task.utils.rxretro.BaseViewModel;
import com.beetleware.task.utils.rxretro.IResponseListener;
import com.beetleware.task.utils.rxretro.RXRetro;


public class LoginViewModel extends BaseViewModel implements IResponseListener {
    private static final String TAG = "SignupViewModel";


    private MutableLiveData<modelLogin> userMutableLiveData;
    private MutableLiveData<Boolean> userMutableLiveDataError;

    UsersService mWebService;


    public MutableLiveData<modelLogin> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }
    public MutableLiveData<Boolean> UserDateError() {

        if (userMutableLiveDataError == null) {
            userMutableLiveDataError = new MutableLiveData<>();
        }
        return userMutableLiveDataError;

    }

    public void setWebService(UsersService mWebService) {
        this.mWebService = mWebService;
    }


    public String isDataValid(String userName,String Pass) {
        RXRetro.getInstance().retrofitEnque(ApiFactory.createApi().auth(new requestLogin(userName,Pass)), this,1);
        return "true";
    }



    @Override
    public void onSuccess(String str, int requestId) {

        Log.d(TAG, "onSuccess: requestId "+requestId);
        Log.d(TAG, "onSuccess: str "+str);
        modelLogin getSample = getGson().fromJson(str, modelLogin.class);
        Log.d(TAG, "onSuccess: getAuthorization "+getSample.getData().getAuthorization());
//            Type collectionType = new TypeToken<Collection<responseMyAds>>() {}.getType();
//            List<responseMyAds> enums = getGson().fromJson(str, collectionType);
        userMutableLiveData.setValue(getSample);
    }





    @Override
    public void serverError(String serverResponse, int paramInt, int errorCode) {
        userMutableLiveDataError.setValue(true);
        Log.d(TAG, "serverError: ");
        super.serverError(serverResponse, paramInt, errorCode);
    }
}
