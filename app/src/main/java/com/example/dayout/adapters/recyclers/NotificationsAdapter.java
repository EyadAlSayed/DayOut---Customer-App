package com.example.dayout.adapters.recyclers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.models.notification.NotificationData;
import com.example.dayout.models.poll.PollsData;
import com.example.dayout.models.room.notificationsRoom.database.NotificationsDatabase;
import com.example.dayout.models.room.pollsRoom.databases.PollsDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    List<NotificationData> notifications;
    Context context;

    public NotificationsAdapter(List<NotificationData> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
    }

    public void refreshList(List<NotificationData> notifications){
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        insertRoomObject(notifications.get(position));

        holder.notificationTitle.setText(notifications.get(position).title);
        holder.notificationDescription.setText(notifications.get(position).body);
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public void insertRoomObject(NotificationData notification) {

        // insert object in room database
        NotificationsDatabase.getINSTANCE(context).iNotifications()
                .insertNotification(notification)
                .subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Notifications Adapter", "onError: " + e.toString());
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.notification_title)
        TextView notificationTitle;

        @BindView(R.id.notification_description)
        TextView notificationDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
