package com.beetleware.task.utils.checkNetwork;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;

/**
 *Check and manipulate network
 * status.
 */
public class NetworkUtil {

    private static ConnectivityManager connectivityManager;
    private static NetworkInfo activeNetwork;
    private static InetAddress ipAddr;
    private static WifiManager wifiManager;
    private static TelephonyManager telephonyManager;

    /**
     * Get status of network. Needs ACCESS_NETWORK_STATE permission in manifest.
     *
     * @param context The android Context instance.
     * @return Network status (unknown, wifi connected with internet, wifi
     * connected without internet, mobile connected, offline).
     */
    public static NetworkStatus getConnectivityStatus(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
               // return isOnline();
                return NetworkStatus.WIFI_CONNECTED_WITH_INTERNET;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return NetworkStatus.MOBILE_DATA_CONNECTED;
            }
        }
        return NetworkStatus.OFFLINE;
    }

    /**
     * Check if internet is available
     *
     * @return true or false
     */
    public static NetworkStatus isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0) ? NetworkStatus.WIFI_CONNECTED_WITH_INTERNET :
                    NetworkStatus.WIFI_CONNECTED_WITHOUT_INTERNET;
        } catch (IOException e) {
             e.printStackTrace();
        } catch (InterruptedException e) {
             e.printStackTrace();
        }
        return NetworkStatus.WIFI_CONNECTED_WITHOUT_INTERNET;
    }


    /**
     * Get class of network.
     *
     * @param context The android Context instance.
     * @return Network status (unknown, 2G, 3G, 4G).
     */
    @SuppressLint("MissingPermission")
    public static NetworkStatus getNetworkClass(Context context) {
        telephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NetworkStatus.TWO_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NetworkStatus.THREE_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NetworkStatus.FOUR_G;
            default:
                return NetworkStatus.UNKNOWN;
        }
    }

    /**
     * Change wifi status. Needs CHANGE_WIFI_STATE permission in manifest.
     *
     * @param context The android Context instance.
     * @param status  Indicates the status of wifi.
     */
    public static void setWifiStatus(Context context, boolean status) {

        wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(status);
    }

    /**
     * Change mobile data status. Needs CHANGE_NETWORK_STATE permission in
     * manifest.
     *
     * @param context The android Context instance.
     * @param status  Indicates the status of mobile data.
     */
    public static void setMobileDataStatus(Context context, boolean status) {

        try {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            Class conmanClass = Class.forName(connectivityManager.getClass()
                    .getName());
            Field iConnectivityManagerField = conmanClass
                    .getDeclaredField("mService");
            iConnectivityManagerField.setAccessible(true);
            Object iConnectivityManager = iConnectivityManagerField
                    .get(connectivityManager);
            Class iConnectivityManagerClass = Class
                    .forName(iConnectivityManager.getClass().getName());
            Method setMobileDataStatusMethod = iConnectivityManagerClass
                    .getDeclaredMethod("setMobileDataStatus", Boolean.TYPE);
            setMobileDataStatusMethod.setAccessible(true);
            setMobileDataStatusMethod.invoke(iConnectivityManager, status);
        } catch (ClassNotFoundException e) {
             e.printStackTrace();
        } catch (NoSuchFieldException e) {
             e.printStackTrace();
        } catch (InvocationTargetException e) {
             e.printStackTrace();
        } catch (NoSuchMethodException e) {
             e.printStackTrace();
        } catch (IllegalAccessException e) {
             e.printStackTrace();
        }
    }
}
