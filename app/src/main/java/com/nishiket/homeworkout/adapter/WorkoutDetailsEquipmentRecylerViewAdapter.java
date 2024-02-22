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
import com.nishiket.homeworkout.databinding.WorkoutDetailsEquipmentDesignBinding;
import com.nishiket.homeworkout.model.WorkoutDetailsEquipmentModel;

import java.util.List;

public class WorkoutDetailsEquipmentRecylerViewAdapter extends RecyclerView.Adapter<WorkoutDetailsEquipmentRecylerViewAdapter.viewHolder> {
    private Context context;
    private List<WorkoutDetailsEquipmentModel> workoutDetailsEquipmentModelList;
    private WorkoutDetailsEquipmentDesignBinding workoutDetailsEquipmentDesignBinding;

    public WorkoutDetailsEquipmentRecylerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWorkoutDetailsEquipmentModelList(List<WorkoutDetailsEquipmentModel> workoutDetailsEquipmentModelList) {
        this.workoutDetailsEquipmentModelList = workoutDetailsEquipmentModelList;
    }

    @NonNull
    @Override
    public WorkoutDetailsEquipmentRecylerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.workout_details_equipment_design,parent,false);
        workoutDetailsEquipmentDesignBinding = WorkoutDetailsEquipmentDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(workoutDetailsEquipmentDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutDetailsEquipmentRecylerViewAdapter.viewHolder holder, int position) {
        WorkoutDetailsEquipmentModel workoutDetailsEquipmentModel = workoutDetailsEquipmentModelList.get(position);
        holder.binding.equipmentImage.setImageResource(workoutDetailsEquipmentModel.getImage());
        holder.binding.equipmentTxt.setText(workoutDetailsEquipmentModel.getEquipment());
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
        private WorkoutDetailsEquipmentDesignBinding binding;
        public viewHolder(@NonNull WorkoutDetailsEquipmentDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            equipmentImage = itemView.findViewById(R.id.equipmentImage);
//            equipmentTxt = itemView.findViewById(R.id.equipmentTxt);
        }
    }
}
