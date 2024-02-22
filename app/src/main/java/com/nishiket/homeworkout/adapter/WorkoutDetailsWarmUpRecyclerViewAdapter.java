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
import com.nishiket.homeworkout.model.WorkoutDetailsWarmUpModel;

import java.util.List;

public class WorkoutDetailsWarmUpRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutDetailsWarmUpRecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<WorkoutDetailsWarmUpModel> workoutDetailsWarmUpModelList;
    private WorkoutDetailWarmupWoroutDesignBinding workoutDetailWarmupWoroutDesignBinding;

    public void setWorkoutDetailsWarmUpModelList(List<WorkoutDetailsWarmUpModel> workoutDetailsWarmUpModelList) {
        this.workoutDetailsWarmUpModelList = workoutDetailsWarmUpModelList;
    }

    public WorkoutDetailsWarmUpRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WorkoutDetailsWarmUpRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.workout_detail_warmup_worout_design,parent,false);
        workoutDetailWarmupWoroutDesignBinding = WorkoutDetailWarmupWoroutDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(workoutDetailWarmupWoroutDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutDetailsWarmUpRecyclerViewAdapter.viewHolder holder, int position) {
        WorkoutDetailsWarmUpModel workoutDetailsWarmUpModel = workoutDetailsWarmUpModelList.get(position);
        holder.binding.workoutDetailWorkoutTxt.setText(workoutDetailsWarmUpModel.getWorkout());
        holder.binding.workoutDetailsrepsTxt.setText(workoutDetailsWarmUpModel.getTime());
        holder.binding.image.setImageResource(workoutDetailsWarmUpModel.getImage());
    }

    @Override
    public int getItemCount() {
        if(workoutDetailsWarmUpModelList == null){
            return 0;
        }
        else {
            return workoutDetailsWarmUpModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private WorkoutDetailWarmupWoroutDesignBinding binding;
        public viewHolder(@NonNull WorkoutDetailWarmupWoroutDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            image = itemView.findViewById(R.id.image);
//            workoutDetailTimeTxt = itemView.findViewById(R.id.workoutDetailsrepsTxt);
//            workoutDetailWorkoutTxt = itemView.findViewById(R.id.workoutDetailWorkoutTxt);
        }
    }
}
