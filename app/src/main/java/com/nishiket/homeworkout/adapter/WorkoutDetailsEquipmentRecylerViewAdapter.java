package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.databinding.WorkoutDetailsEquipmentDesignBinding;
import com.nishiket.homeworkout.model.EquipmentModel;

import java.util.List;

public class WorkoutDetailsEquipmentRecylerViewAdapter extends RecyclerView.Adapter<WorkoutDetailsEquipmentRecylerViewAdapter.viewHolder> {
    private Context context;
    private List<EquipmentModel> equipmentModelList;
    private WorkoutDetailsEquipmentDesignBinding workoutDetailsEquipmentDesignBinding;

    public WorkoutDetailsEquipmentRecylerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWorkoutDetailsEquipmentModelList(List<EquipmentModel> workoutDetailsEquipmentModelList) {
        this.equipmentModelList = workoutDetailsEquipmentModelList;
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
        EquipmentModel equipmentModel = equipmentModelList.get(position);
        holder.binding.equipmentTxt.setText(equipmentModel.getName());
        if(equipmentModel.getImage()!=null){
            Glide.with(context).load(equipmentModel.getImage()).into(holder.binding.equipmentImage);
        }
    }

    @Override
    public int getItemCount() {
       if(equipmentModelList == null){
           return 0;
       }
       else {
           return equipmentModelList.size();
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
