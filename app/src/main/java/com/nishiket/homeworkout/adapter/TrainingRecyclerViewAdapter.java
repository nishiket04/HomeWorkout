package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.TrainingModel;

import java.util.List;

public class TrainingRecyclerViewAdapter extends RecyclerView.Adapter<TrainingRecyclerViewAdapter.viewHolder> {
    private List<TrainingModel> trainingModelList;
    private Context context;

    public TrainingRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setTrainingModelList(List<TrainingModel> trainingModelList) {
        this.trainingModelList = trainingModelList;
    }

    public interface OnItemClickedListiner{
        void onItemClicked(int position,TrainingModel trainingModel);
    }
    private OnItemClickedListiner onItemClickedListiner;

    public void setOnItemClickedListiner(OnItemClickedListiner listiner) {
        this.onItemClickedListiner = listiner;
    }

    @NonNull
    @Override
    public TrainingRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trainings_workout_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingRecyclerViewAdapter.viewHolder holder, int position) {

        TrainingModel trainingModel = trainingModelList.get(position);
        holder.traingTxt.setText(trainingModel.getWorkout());
        holder.traingImage.setImageResource(trainingModel.getImage());
        holder.traingLevelTxt.setText(trainingModel.getLevel());
        holder.traingTimetxt.setText(trainingModel.getTime());

        holder.traingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickedListiner !=null){
                    onItemClickedListiner.onItemClicked(position,trainingModel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(trainingModelList == null){
            return 0;
        }
        else {
            return trainingModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView traingTxt,traingLevelTxt,traingTimetxt;
        ImageView traingImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            traingLevelTxt = itemView.findViewById(R.id.traingWorkoutLevelTxt);
            traingTimetxt = itemView.findViewById(R.id.traingWorkoutTimeTxt);
            traingImage = itemView.findViewById(R.id.traingWorkoutImage);
            traingTxt = itemView.findViewById(R.id.traingWorkoutTxt);

        }
    }
}
