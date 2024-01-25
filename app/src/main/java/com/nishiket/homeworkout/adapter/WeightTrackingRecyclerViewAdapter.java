package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.WeightTrackingModel;

import java.util.List;

public class WeightTrackingRecyclerViewAdapter extends RecyclerView.Adapter<WeightTrackingRecyclerViewAdapter.viewHolder> {

    private List<WeightTrackingModel> weightTrackingModelList;
    private Context context;

    public WeightTrackingRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWeightTrackingModelList(List<WeightTrackingModel> weightTrackingModelList) {
        this.weightTrackingModelList = weightTrackingModelList;
    }

    @NonNull
    @Override
    public WeightTrackingRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weigth_tracking_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeightTrackingRecyclerViewAdapter.viewHolder holder, int position) {
        WeightTrackingModel weightTrackingModel = weightTrackingModelList.get(position);
        holder.arrow.setImageResource(weightTrackingModel.getImage());
        holder.weightInfo.setText(weightTrackingModel.getWeightInfo());
        holder.weight.setText(weightTrackingModel.getWeight());
        holder.time.setText(weightTrackingModel.getTime());
        holder.date.setText(weightTrackingModel.getDate());

    }

    @Override
    public int getItemCount() {
        if (weightTrackingModelList == null){
            return 0;
        }
        else {
            return weightTrackingModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView date,time,weight,weightInfo;
        ImageView arrow;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            weight = itemView.findViewById(R.id.weight);
            weightInfo = itemView.findViewById(R.id.weightInfo);
            arrow = itemView.findViewById(R.id.arrow);

        }
    }
}
