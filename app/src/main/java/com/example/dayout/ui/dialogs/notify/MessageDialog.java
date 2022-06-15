package com.example.dayout.ui.dialogs.notify;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dayout.R;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class MessageDialog extends Dialog {

    Context context;

    @BindView(R.id.message_dialog_message)
    TextView messageDialogMessage;

    @BindView(R.id.message_dialog_ok_button)
    Button messageDialogOkButton;

    public MessageDialog(@NonNull Context context, String message) {
        super(context);
        this.context = context;
        setContentView(R.layout.message_dialog);
        setCancelable(false);
        ButterKnife.bind(this);
        initViews(message);
    }

    private void initViews(String message) {
        messageDialogMessage.setText(message);
        messageDialogOkButton.setOnClickListener(onOkButtonClicked);
    }

    private final View.OnClickListener onOkButtonClicked = view -> dismiss();

    @Override
    public void show() {
        WindowManager.LayoutParams wlp = getWindow().getAttributes();
        wlp.gravity = Gravity.CENTER;
        getWindow().setAttributes(wlp);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // match width dialog
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.show();
    }

}
