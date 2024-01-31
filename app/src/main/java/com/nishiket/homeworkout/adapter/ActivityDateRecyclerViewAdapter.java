package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.ActivityDateModel;

import java.util.List;

public class ActivityDateRecyclerViewAdapter extends RecyclerView.Adapter<ActivityDateRecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<ActivityDateModel> activityDateModelsList;

    public void setActivityDateModelsLst(List<ActivityDateModel> activityDateModelsList) {
        this.activityDateModelsList = activityDateModelsList;
    }

    public ActivityDateRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ActivityDateRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dates_activity_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityDateRecyclerViewAdapter.viewHolder holder, int position) {
        ActivityDateModel activityDateModel = activityDateModelsList.get(position);
        holder.mntActivityTxt.setText(activityDateModel.getMonth());
        holder.dateActivityTxt.setText(activityDateModel.getDate());
        if(position == 0){
            holder.date_bg.setBackgroundResource(R.color.primary_light);
        }

    }

    @Override
    public int getItemCount() {
        if(activityDateModelsList == null){
            return 0;
        }
        else {
            return activityDateModelsList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout date_bg;
        TextView dateActivityTxt,mntActivityTxt;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            date_bg = itemView.findViewById(R.id.date_bg);
            dateActivityTxt = itemView.findViewById(R.id.dateActivityTxt);
            mntActivityTxt = itemView.findViewById(R.id.mntActivityTxt);
        }
    }
}
