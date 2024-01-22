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
import com.nishiket.homeworkout.model.ExercisesModel;

import java.util.List;

public class ExercisesRecyclerViewAdapter extends RecyclerView.Adapter<ExercisesRecyclerViewAdapter.viewHolder> {
    private List<ExercisesModel> exercisesModelList;
    private Context context;

    public ExercisesRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setExercisesModelList(List<ExercisesModel> exercisesModelList) {
        this.exercisesModelList = exercisesModelList;
    }

    @NonNull
    @Override
    public ExercisesRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exercises_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesRecyclerViewAdapter.viewHolder holder, int position) {
        ExercisesModel exercisesModel = exercisesModelList.get(position);
        holder.exercisesTxt.setText(exercisesModel.getExercise());
        holder.exercisesTimeTxt.setText(exercisesModel.getTime());
        holder.exercisesImage.setImageResource(exercisesModel.getImage());

    }

    @Override
    public int getItemCount() {
        if (exercisesModelList == null){
            return 0;
        }
        else {
            return exercisesModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView exercisesTxt,exercisesTimeTxt;
        ImageView exercisesImage,exercisesInfoImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            exercisesTxt = itemView.findViewById(R.id.exercisesTxt);
            exercisesTimeTxt = itemView.findViewById(R.id.exercisesTimeTxt);
            exercisesImage = itemView.findViewById(R.id.exercisesImage);
            exercisesInfoImage = itemView.findViewById(R.id.exercisesInfoImage);
        }
    }
}
