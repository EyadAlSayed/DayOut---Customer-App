package com.example.dayout.ui.dialogs.notify;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.dayout.R;
import com.example.dayout.config.AppSharedPreferences;
import com.example.dayout.ui.activities.AuthActivity;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.viewModels.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogOutDialog extends Dialog {


    @BindView(R.id.log_out_btn)
    Button logOutButton;
    @BindView(R.id.return_btn)
    Button returnButton;

    Context context;

    public LogOutDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.log_out_dialog);
        ButterKnife.bind(this);
        this.context = context;
        setCancelable(false);
        returnButton.setOnClickListener(v -> cancel());
        logOutButton.setOnClickListener(onLogOutClicked);
    }


    private final View.OnClickListener onLogOutClicked = v -> {

        UserViewModel.getINSTANCE().logOut();
        UserViewModel.getINSTANCE().successfulMutableLiveData.observe((MainActivity) context, new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> booleanStringPair) {
                if (booleanStringPair != null){
                    if (booleanStringPair.first != null && booleanStringPair.first){
                        LogOutDialog.this.cancel();
                        AppSharedPreferences.REMOVE_ALL();
                        openAuthActivity();
                    }
                    else {
                        new ErrorDialog(context,booleanStringPair.second).show();
                    }
                }
                else {
                    new ErrorDialog(context,context.getResources().getString(R.string.error_connection)).show();
                }
            }
        });
    };


    private void openAuthActivity(){
        context.startActivity(new Intent(context, AuthActivity.class));
        ((MainActivity)context).finish();
    }

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
