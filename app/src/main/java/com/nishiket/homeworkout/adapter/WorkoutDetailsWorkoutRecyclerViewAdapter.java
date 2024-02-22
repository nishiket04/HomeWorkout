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
import com.nishiket.homeworkout.databinding.WorkoutDetailWarmupWoroutDesignBinding;
import com.nishiket.homeworkout.model.WorkoutDetailsWorkoutModel;

import java.util.List;

public class WorkoutDetailsWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutDetailsWorkoutRecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<WorkoutDetailsWorkoutModel> workoutDetailsWorkoutModelList;
    private WorkoutDetailWarmupWoroutDesignBinding workoutDetailWarmupWoroutDesignBinding;

    public WorkoutDetailsWorkoutRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWorkoutDetailsWorkoutModelList(List<WorkoutDetailsWorkoutModel> workoutDetailsWorkoutModelList) {
        this.workoutDetailsWorkoutModelList = workoutDetailsWorkoutModelList;
    }

    @NonNull
    @Override
    public WorkoutDetailsWorkoutRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.workout_detail_warmup_worout_design,parent,false);
        workoutDetailWarmupWoroutDesignBinding = WorkoutDetailWarmupWoroutDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(workoutDetailWarmupWoroutDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutDetailsWorkoutRecyclerViewAdapter.viewHolder holder, int position) {
        WorkoutDetailsWorkoutModel workoutDetailsWorkoutModel = workoutDetailsWorkoutModelList.get(position);
        holder.binding.workoutDetailWorkoutTxt.setText(workoutDetailsWorkoutModel.getWorkout());
        holder.binding.workoutDetailsrepsTxt.setText(workoutDetailsWorkoutModel.getReps());
        holder.binding.image.setImageResource(workoutDetailsWorkoutModel.getImage());
    }

    @Override
    public int getItemCount() {
        if (workoutDetailsWorkoutModelList == null){
            return 0;
        }
        else {
            return workoutDetailsWorkoutModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private WorkoutDetailWarmupWoroutDesignBinding binding;
        public viewHolder(@NonNull WorkoutDetailWarmupWoroutDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            image = itemView.findViewById(R.id.image);
//            workoutDetailsRepsTxt = itemView.findViewById(R.id.workoutDetailsrepsTxt);
//            workoutDetailWorkoutTxt = itemView.findViewById(R.id.workoutDetailWorkoutTxt);
        }
    }
}
