package com.beetleware.task.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.beetleware.task.R;
import com.beetleware.task.utils.Dialog.Activity.SmartDialog;
import com.beetleware.task.utils.Dialog.ListenerCallBack.SmartDialogClickListener;
import com.beetleware.task.utils.Dialog.Utilities.SmartDialogBuilder;
import com.kaopiz.kprogresshud.KProgressHUD;

public class Dialogues {
    static KProgressHUD kProgressHUD;
    static android.app.Dialog lovelyStandardDialog;

    public static void CreateDialog(Context context) {
        kProgressHUD = new KProgressHUD(context);
        kProgressHUD
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    public static void DismasDialog() {
        kProgressHUD.dismiss();

    }



    public static void CreatePoPup(Context context, String Title, String Message) {


        new SmartDialogBuilder(context)
                .setTitle(Title)
                .setCancalable(true)
.setSubTitle(Message)
                .setNegativeButtonHide(true) //hide cancel button
                .setPositiveButton(context.getResources().getString(R.string.Ok), new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {

                        smartDialog.dismiss();
                    }
                }).build().show();
    }
}
