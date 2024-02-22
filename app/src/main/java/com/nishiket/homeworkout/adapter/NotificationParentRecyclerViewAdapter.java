package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.NotificationDesign1Binding;
import com.nishiket.homeworkout.model.NotificationParentModel;

import java.util.List;

public class NotificationParentRecyclerViewAdapter extends RecyclerView.Adapter<NotificationParentRecyclerViewAdapter.viewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<NotificationParentModel> notificationParentModelList;
    private Context context;
    private NotificationDesign1Binding notificationDesign1Binding;

    public NotificationParentRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setNotificationParentModelList(List<NotificationParentModel> notificationParentModelList) {
        this.notificationParentModelList = notificationParentModelList;
    }

    @NonNull
    @Override
    public NotificationParentRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.notification_design_1,parent,false);
        notificationDesign1Binding = NotificationDesign1Binding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(notificationDesign1Binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NotificationParentModel notificationParentModel = notificationParentModelList.get(position);
        holder.binding.notificationDateTxt.setText(notificationParentModel.getNotifiacationTime());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        layoutManager.setInitialPrefetchItemCount(notificationParentModel.getNotificationChildModelList().size());
        NotificationChildRecyclerrViewAdapter notificationChildRecyclerrViewAdapter = new NotificationChildRecyclerrViewAdapter(context);
        notificationChildRecyclerrViewAdapter.setNotificationChildModelList(notificationParentModel.getNotificationChildModelList());
        holder.binding.notificationChildRecyclerView.setAdapter(notificationChildRecyclerrViewAdapter);
        holder.binding.notificationChildRecyclerView.setLayoutManager(layoutManager);
        holder.binding.notificationChildRecyclerView.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        if (notificationParentModelList == null){
            return 0;
        }
        else {
            return notificationParentModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private NotificationDesign1Binding binding;
        public viewHolder(@NonNull NotificationDesign1Binding itemView) {
            super(itemView.getRoot());
//            notificationDateTxt = itemView.findViewById(R.id.notificationDateTxt);
//            notificationChildRecyclerView = itemView.findViewById(R.id.notificationChildRecyclerView);
                binding = itemView;
        }
    }
}
