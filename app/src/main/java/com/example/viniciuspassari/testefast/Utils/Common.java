package com.example.viniciuspassari.testefast.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.viniciuspassari.testefast.R;

public class Common {

    public static ProgressDialog showLoadingDialog(Context context, String msg){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();

        if(progressDialog.getWindow() != null){
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        progressDialog.setTitle("Loading");
        progressDialog.setMessage(msg);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    public static void showSnackbar(Context context, CoordinatorLayout coordinatorLayout, String msg, int length){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(coordinatorLayout.getWindowToken(), 0);

        Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, length);
        View snackBarView = snackbar.getView();
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.snackbar_textsize));
        textView.setMaxLines(5);
        snackbar.show();
    }

    public static void showSnackbarWithUserAction(Context context, CoordinatorLayout coordinatorLayout, String action, String msg){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(coordinatorLayout.getWindowToken(), 0);

        Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_INDEFINITE)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        snackbar.setActionTextColor(Color.WHITE);
        View snackBarView = snackbar.getView();
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.snackbar_textsize));
        textView.setMaxLines(5);
        snackbar.show();
    }

}