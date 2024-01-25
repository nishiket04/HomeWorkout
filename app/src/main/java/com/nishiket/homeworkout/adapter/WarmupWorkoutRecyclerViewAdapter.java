package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.WarmUpWorkoutModel;

import java.util.List;

public class WarmupWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WarmupWorkoutRecyclerViewAdapter.viewHolder> {
    private List<WarmUpWorkoutModel> warmUpWorkoutModelList;
    private Context context;

    public WarmupWorkoutRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWarmUpWorkoutModelList(List<WarmUpWorkoutModel> warmUpWorkoutModelList) {
        this.warmUpWorkoutModelList = warmUpWorkoutModelList;
    }

    @NonNull
    @Override
    public WarmupWorkoutRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.warmup_workout_stretching_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WarmupWorkoutRecyclerViewAdapter.viewHolder holder, int position) {

        if(warmUpWorkoutModelList == null && position==0){
            holder.exercisesTimeTxt1.setVisibility(View.GONE);
            holder.exercisesTxt1.setText("Add");
            holder.exercisesImage1.setImageResource(R.drawable.bicep);
            holder.exercisesInfoImage1.setVisibility(View.GONE);
        }
        else {
            WarmUpWorkoutModel warmUpWorkoutModel = warmUpWorkoutModelList.get(position);
            holder.exercisesTimeTxt1.setText(warmUpWorkoutModel.getTime());
            holder.exercisesTxt1.setText(warmUpWorkoutModel.getWorkout());
            holder.exercisesImage1.setImageResource(warmUpWorkoutModel.getImage());
        }

    }

    @Override
    public int getItemCount() {
        if (warmUpWorkoutModelList == null){
            return 0;
        }
        else {
            return warmUpWorkoutModelList.size();
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
