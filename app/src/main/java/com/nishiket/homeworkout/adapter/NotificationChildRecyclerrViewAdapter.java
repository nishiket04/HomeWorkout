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
import com.nishiket.homeworkout.databinding.NotificationDesign2Binding;
import com.nishiket.homeworkout.model.NotificationChildModel;

import java.util.List;

public class NotificationChildRecyclerrViewAdapter extends RecyclerView.Adapter<NotificationChildRecyclerrViewAdapter.viewHolder> {

    private List<NotificationChildModel> notificationChildModelList;
    private Context context;
    private NotificationDesign2Binding notificationDesign2Binding;

    public NotificationChildRecyclerrViewAdapter(Context context) {
        this.context = context;
    }

    public void setNotificationChildModelList(List<NotificationChildModel> notificationChildModelList) {
        this.notificationChildModelList = notificationChildModelList;
    }

    @NonNull
    @Override
    public NotificationChildRecyclerrViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.notification_design_2,parent,false);
        notificationDesign2Binding = NotificationDesign2Binding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(notificationDesign2Binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationChildRecyclerrViewAdapter.viewHolder holder, int position) {
        NotificationChildModel notificationChildModel = notificationChildModelList.get(position);
        holder.binding.notificationImage.setImageResource(notificationChildModel.getImage());
        holder.binding.notificationTxt.setText(notificationChildModel.getNotification());
        holder.binding.notificationTime.setText(notificationChildModel.getTime());

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
        private NotificationDesign2Binding binding;
        public viewHolder(@NonNull NotificationDesign2Binding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            notificationTime = itemView.findViewById(R.id.notificationTime);
//            notificationTxt = itemView.findViewById(R.id.notificationTxt);
//            notificationImage = itemView.findViewById(R.id.notificationImage);
        }
    }
}
