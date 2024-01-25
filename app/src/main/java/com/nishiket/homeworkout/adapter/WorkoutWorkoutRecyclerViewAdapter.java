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
import com.nishiket.homeworkout.model.WorkoutWorkoutModel;

import java.util.List;

public class WorkoutWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutWorkoutRecyclerViewAdapter.viewHolder> {

    private List<WorkoutWorkoutModel> workoutWorkoutModelList;
    private Context context;

    public WorkoutWorkoutRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWorkoutWorkoutModelList(List<WorkoutWorkoutModel> workoutWorkoutModelList) {
        this.workoutWorkoutModelList = workoutWorkoutModelList;
    }

    @NonNull
    @Override
    public WorkoutWorkoutRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.warmup_workout_stretching_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutWorkoutRecyclerViewAdapter.viewHolder holder, int position) {
        if (workoutWorkoutModelList == null){
            holder.exercisesTimeTxt1.setVisibility(View.GONE);
            holder.exercisesTxt1.setText("Add");
            holder.exercisesImage1.setImageResource(R.drawable.bicep);
            holder.exercisesInfoImage1.setVisibility(View.GONE);
        }
        else {
            WorkoutWorkoutModel workoutWorkoutModel = workoutWorkoutModelList.get(position);
            holder.exercisesTimeTxt1.setText(workoutWorkoutModel.getTime());
            holder.exercisesTxt1.setText(workoutWorkoutModel.getWorkout());
            holder.exercisesImage1.setImageResource(workoutWorkoutModel.getImage());
        }

    }

    @Override
    public int getItemCount() {
        if (workoutWorkoutModelList == null){
            return 0;
        }
        else {
            return workoutWorkoutModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView exercisesImage1,exercisesInfoImage1;
        TextView exercisesTxt1,exercisesTimeTxt1;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            exercisesImage1 = itemView.findViewById(R.id.exercisesImage1);
            exercisesInfoImage1 = itemView.findViewById(R.id.exercisesInfoImage1);
            exercisesTxt1 = itemView.findViewById(R.id.exercisesTxt1);
            exercisesTimeTxt1 = itemView.findViewById(R.id.exercisesTimeTxt1);
        }
    }
}
