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
import com.nishiket.homeworkout.model.WorkoutDetailsWorkoutModel;

import java.util.List;

public class WorkoutDetailsWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutDetailsWorkoutRecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<WorkoutDetailsWorkoutModel> workoutDetailsWorkoutModelList;

    public WorkoutDetailsWorkoutRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWorkoutDetailsWorkoutModelList(List<WorkoutDetailsWorkoutModel> workoutDetailsWorkoutModelList) {
        this.workoutDetailsWorkoutModelList = workoutDetailsWorkoutModelList;
    }

    @NonNull
    @Override
    public WorkoutDetailsWorkoutRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.workout_detail_warmup_worout_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutDetailsWorkoutRecyclerViewAdapter.viewHolder holder, int position) {
        WorkoutDetailsWorkoutModel workoutDetailsWorkoutModel = workoutDetailsWorkoutModelList.get(position);
        holder.workoutDetailWorkoutTxt.setText(workoutDetailsWorkoutModel.getWorkout());
        holder.workoutDetailsRepsTxt.setText(workoutDetailsWorkoutModel.getReps());
        holder.image.setImageResource(workoutDetailsWorkoutModel.getImage());
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
        ImageView image;
        TextView workoutDetailWorkoutTxt,workoutDetailsRepsTxt;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            workoutDetailsRepsTxt = itemView.findViewById(R.id.workoutDetailsrepsTxt);
            workoutDetailWorkoutTxt = itemView.findViewById(R.id.workoutDetailWorkoutTxt);
        }
    }
}
