package com.beetleware.task.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 10/1/2016.
 */

public class UserInfo {

    private static final String PREF_NAME = "userinfo";
    private static final String AccessToken = "AccessToken";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }



    public void seAccessToken(String Access){
        editor.putString(AccessToken, Access);
        editor.apply();
    }


    public String getAccessToken() {
        return prefs.getString(AccessToken, "");
    }



    public void clearUserInfo() {
        editor.clear();
        editor.commit();
    }

    public void remove() {
        editor.remove(AccessToken);
        editor.commit();

    }





}
