package com.termux.app;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.termux.R;

/**
 * -- by srb.bans on 2/2/2021.
 */
public class BackgroundOptions_dialog {
    private final Context context;
    private final Callback callback;

    public BackgroundOptions_dialog(Context ctx, Callback call) {
        context = ctx;
        callback = call;
    }

    public void show() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View mView = layoutInflaterAndroid.inflate(R.layout.background_options_dialog, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context, R.style.TermuxAlertDialogStyle);
        alertDialogBuilderUserInput.setView(mView);

        final AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();

        final EditText hex_code = mView.findViewById(R.id.hex_code);
        final Button bt_reset = mView.findViewById(R.id.reset_background);
        final Button bt_save = mView.findViewById(R.id.save);
        final Button bt_pick = mView.findViewById(R.id.choose);

        bt_save.setOnClickListener(view -> {
            alertDialogAndroid.dismiss();
            if (callback != null) {
                String hex = "#00000000";
                if (hex_code.length() > 0) {
                    hex = hex_code.getText().toString();
                }
                callback.onSave(hex);
            }
        });
        bt_pick.setOnClickListener(view -> {
            alertDialogAndroid.dismiss();
            if (callback != null) callback.onPick();
        });
        bt_reset.setOnClickListener(view -> {
            alertDialogAndroid.dismiss();
            if (callback != null) callback.onReset();
        });

        if (alertDialogAndroid.getWindow() != null)
            alertDialogAndroid.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Add animation to view
        //BounceView.addAnimTo(alertDialogAndroid);

        alertDialogAndroid.show();
    }


    public interface Callback {

        void onReset();

        void onPick();

        void onSave(String hex_overlay_color);
    }

}
