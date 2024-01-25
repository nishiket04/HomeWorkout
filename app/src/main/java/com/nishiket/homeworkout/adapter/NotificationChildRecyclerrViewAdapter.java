package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.NotificationChildModel;

import java.util.List;

public class NotificationChildRecyclerrViewAdapter extends RecyclerView.Adapter<NotificationChildRecyclerrViewAdapter.viewHolder> {

    private List<NotificationChildModel> notificationChildModelList;
    private Context context;

    public NotificationChildRecyclerrViewAdapter(Context context) {
        this.context = context;
    }

    public void setNotificationChildModelList(List<NotificationChildModel> notificationChildModelList) {
        this.notificationChildModelList = notificationChildModelList;
    }

    @NonNull
    @Override
    public NotificationChildRecyclerrViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_design_2,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationChildRecyclerrViewAdapter.viewHolder holder, int position) {
        NotificationChildModel notificationChildModel = notificationChildModelList.get(position);
        holder.notificationImage.setImageResource(notificationChildModel.getImage());
        holder.notificationTxt.setText(notificationChildModel.getNotification());
        holder.notificationTime.setText(notificationChildModel.getTime());

    }

    @Override
    public int getItemCount() {
        if (notificationChildModelList == null){
            return 0;
        }
        else {
            return notificationChildModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView notificationImage;
        TextView notificationTxt,notificationTime;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            notificationTime = itemView.findViewById(R.id.notificationTime);
            notificationTxt = itemView.findViewById(R.id.notificationTxt);
            notificationImage = itemView.findViewById(R.id.notificationImage);
        }
    }
}
