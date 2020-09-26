package com.beetleware.task.ui.home.homeFragment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.beetleware.task.data.network.ApiFactory;
import com.beetleware.task.data.network.UsersService;
import com.beetleware.task.ui.login.modelLogin;
import com.beetleware.task.ui.login.requestLogin;
import com.beetleware.task.utils.rxretro.BaseViewModel;
import com.beetleware.task.utils.rxretro.IResponseListener;
import com.beetleware.task.utils.rxretro.RXRetro;


public class homeViewModel extends BaseViewModel implements IResponseListener {
    private static final String TAG = "SignupViewModel";


    private MutableLiveData<model> userMutableLiveData;
    private MutableLiveData<model> userMutableLiveDataProducts;
    private MutableLiveData<Boolean> userMutableLiveDataError;

    UsersService mWebService;


    public MutableLiveData<model> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }
    public MutableLiveData<model> getUserProducts() {

        if (userMutableLiveDataProducts == null) {
            userMutableLiveDataProducts = new MutableLiveData<>();
        }
        return userMutableLiveDataProducts;

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


    public String isDataValid(String auth) {
        RXRetro.getInstance().retrofitEnque(ApiFactory.createApi().sold_items("Bearer"+auth), this,1);
        return "true";
    }

    public String isDataValidProducts(String auth) {
        RXRetro.getInstance().retrofitEnque(ApiFactory.createApi().products("Bearer"+auth), this,2);
        return "true";
    }


    @Override
    public void onSuccess(String str, int requestId) {
        if (requestId == 1) {
            Log.d(TAG, "onSuccess: requestId "+requestId);
            Log.d(TAG, "onSuccess: str "+str);
            model getSample = getGson().fromJson(str, model.class);
//            Type collectionType = new TypeToken<Collection<responseMyAds>>() {}.getType();
//            List<responseMyAds> enums = getGson().fromJson(str, collectionType);
            userMutableLiveData.setValue(getSample);
        }else if(requestId==2){
            Log.d(TAG, "onSuccess: requestId "+requestId);
            Log.d(TAG, "onSuccess: str "+str);
            model getSample = getGson().fromJson(str, model.class);
//            Type collectionType = new TypeToken<Collection<responseMyAds>>() {}.getType();
//            List<responseMyAds> enums = getGson().fromJson(str, collectionType);
            userMutableLiveDataProducts.setValue(getSample);
        }


    }





    @Override
    public void serverError(String serverResponse, int paramInt, int errorCode) {
        userMutableLiveDataError.setValue(true);
        Log.d(TAG, "serverError: ");
        super.serverError(serverResponse, paramInt, errorCode);
    }
}
