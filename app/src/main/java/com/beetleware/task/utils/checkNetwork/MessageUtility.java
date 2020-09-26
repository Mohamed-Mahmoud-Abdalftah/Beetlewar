package com.beetleware.task.utils.checkNetwork;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by z.maher on 6/18/2017.
 */

public class MessageUtility {

    private static boolean isshow = true;

    public static String decrypted_response(String encrypted_response) {

        String decrypted_response = "";

        try {
            //MCrypt mcrypt = new MCrypt();
            //decrypted_response = new String(mcrypt.decrypt(encrypted_response));
        } catch (Exception e) {

            return "";
        }
        return decrypted_response;
    }

    public static String Authorization() {
        String creds = String.format("%s:%s", "gate", "123456");
        String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
        return auth;
    }

    public static String encryptString(String Key) {
        // MCrypt mcrypt = new MCrypt();
        String encrypted_UserGUID = "";
        try {
            //  encrypted_UserGUID = MCrypt.bytesToHex(mcrypt.encrypt(Key));

        } catch (Exception e) {
            return "";
        }
        return encrypted_UserGUID;
    }


    public static void showToast(Activity v, String message) {
        if (v != null)
            Toast.makeText(v, message + "", Toast.LENGTH_SHORT).show();
        else
            return;
    }

    private static Toast toast;

    public static void showToast(Context c, String message) {
        if (c != null) {
            if (toast != null) {
                toast.setText(message + "");
            } else {
                toast = Toast.makeText(c, message + "", Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    public static void Logerror(String tag, String error) {
        if (isshow) {
            Log.e(tag + "", error + "");
        } else {
            Log.e("Log OFF", "Log OFF");
            Log.d("Log OFF", "Log OFF");
            //return;
        }
    }
}
