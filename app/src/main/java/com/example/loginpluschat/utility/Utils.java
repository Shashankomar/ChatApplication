package com.example.loginpluschat.utility;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.loginpluschat.utility.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static ProgressDialog sProgressDialog;

    public static void showProgressDialog(Context context) {
        sProgressDialog = new ProgressDialog(context);
        sProgressDialog.setMessage(Constant.loading_msg);
        sProgressDialog.show();
    }

    public static void hideProgressDialog() {
        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            sProgressDialog.dismiss();
        }
    }

    public static String setDateFormat(String date, String oldPattern, String newPattern) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(oldPattern);
        Date newDate = null;
        try {
            newDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleDateFormat = new SimpleDateFormat(newPattern);
        return simpleDateFormat.format(newDate);
    }

    public static String getDateTime(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void toastMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
