package com.example.dayout.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dayout.R;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressLint("NonConstantResourceId")
public class ErrorDialog extends Dialog {


    String errorMessage;
    @BindView(R.id.error_txt)
    TextView errorTxt;

    public ErrorDialog(@NonNull Context context, String errorMessage) {
        super(context);
        setContentView(R.layout.error_dialog);
        setCancelable(false);
        ButterKnife.bind(this);
        this.errorMessage = errorMessage;
    }

    @Override
    public void show() {

        errorTxt.setText(errorMessage);

        WindowManager.LayoutParams wlp = getWindow().getAttributes();
        wlp.gravity = Gravity.CENTER;
        getWindow().setAttributes(wlp);
        // match width dialog
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.show();
    }
}
