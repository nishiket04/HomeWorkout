package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.databinding.WorkoutDetailWarmupWoroutDesignBinding;
import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.model.WorkoutDetailsWorkoutModel;

import java.util.List;

public class WorkoutDetailsWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutDetailsWorkoutRecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<ExercisesModel> workoutDetailsWorkoutModelList;
    private OnClickedItem onClickedItem;
    private WorkoutDetailWarmupWoroutDesignBinding workoutDetailWarmupWoroutDesignBinding;

    public WorkoutDetailsWorkoutRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public interface OnClickedItem{
        void onClicked(int i,ExercisesModel exercisesModel);
    }

    public void setWorkoutDetailsWorkoutModelList(List<ExercisesModel> workoutDetailsWorkoutModelList) {
        this.workoutDetailsWorkoutModelList = workoutDetailsWorkoutModelList;
    }

    public void setOnClickedItem(OnClickedItem onClickedItem) {
        this.onClickedItem = onClickedItem;
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
        ExercisesModel workoutDetailsWorkoutModel = workoutDetailsWorkoutModelList.get(position);
        holder.binding.workoutDetailWorkoutTxt.setText(workoutDetailsWorkoutModel.getExercises());
        holder.binding.workoutDetailsrepsTxt.setText(workoutDetailsWorkoutModel.getTime());
//        holder.binding.image.setImageResource(workoutDetailsWorkoutModel.getImage());
        Glide.with(context).load(workoutDetailsWorkoutModel.getImage()).into(holder.binding.image);
        holder.binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickedItem!=null){
                    onClickedItem.onClicked(holder.getAdapterPosition(),workoutDetailsWorkoutModelList.get(holder.getAdapterPosition()));
                }
            }
        });
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
