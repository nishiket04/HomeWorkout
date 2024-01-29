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
import com.nishiket.homeworkout.model.WorkoutDetailsEquipmentModel;

import java.util.List;

public class WorkoutDetailsEquipmentRecylerViewAdapter extends RecyclerView.Adapter<WorkoutDetailsEquipmentRecylerViewAdapter.viewHolder> {
    private Context context;
    private List<WorkoutDetailsEquipmentModel> workoutDetailsEquipmentModelList;

    public WorkoutDetailsEquipmentRecylerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWorkoutDetailsEquipmentModelList(List<WorkoutDetailsEquipmentModel> workoutDetailsEquipmentModelList) {
        this.workoutDetailsEquipmentModelList = workoutDetailsEquipmentModelList;
    }

    @NonNull
    @Override
    public WorkoutDetailsEquipmentRecylerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.workout_details_equipment_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutDetailsEquipmentRecylerViewAdapter.viewHolder holder, int position) {
        WorkoutDetailsEquipmentModel workoutDetailsEquipmentModel = workoutDetailsEquipmentModelList.get(position);
        holder.equipmentImage.setImageResource(workoutDetailsEquipmentModel.getImage());
        holder.equipmentTxt.setText(workoutDetailsEquipmentModel.getEquipment());
    }

    @Override
    public int getItemCount() {
       if(workoutDetailsEquipmentModelList == null){
           return 0;
       }
       else {
           return workoutDetailsEquipmentModelList.size();
       }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView equipmentImage;
        TextView equipmentTxt;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            equipmentImage = itemView.findViewById(R.id.equipmentImage);
            equipmentTxt = itemView.findViewById(R.id.equipmentTxt);
        }
    }
}
