package com.beetleware.task.utils;

import android.app.Activity;

import com.beetleware.task.utils.checkNetwork.MessageUtility;
import com.beetleware.task.utils.checkNetwork.NetworkUtil;


public class AppUtils {
    private static AppUtils iAppUtilities = null;
    public static Activity mActivity;

    public static AppUtils getInstance(){
        if(iAppUtilities == null){
            iAppUtilities = new AppUtils(mActivity);
        }

        return iAppUtilities;
    }
    AppUtils(Activity a) {
        this.mActivity = a;
    }

    /**
     * Check if internet is available
     * @param aActivity The android Context instance.
     * @return true or false
     */
    public  boolean checkNetWork(Activity aActivity){
        switch (NetworkUtil.getConnectivityStatus(aActivity)) {
            case OFFLINE:
                MessageUtility.showToast(aActivity,"No Internet Connection");
                return false;
            case WIFI_CONNECTED_WITHOUT_INTERNET:
                MessageUtility.showToast(aActivity,"No Internet Connection");
                return false;
            case MOBILE_DATA_CONNECTED:
            case WIFI_CONNECTED_WITH_INTERNET:
                return true;
            case UNKNOWN:
                MessageUtility.showToast(aActivity,"No Internet Connection");
                return false;
            default:
                return false;
        }
    }
}
