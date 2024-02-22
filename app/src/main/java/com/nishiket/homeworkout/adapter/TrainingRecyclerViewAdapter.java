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
import com.nishiket.homeworkout.databinding.TrainingsWorkoutDesignBinding;
import com.nishiket.homeworkout.model.TrainingModel;

import java.util.List;

public class TrainingRecyclerViewAdapter extends RecyclerView.Adapter<TrainingRecyclerViewAdapter.viewHolder> {
    private List<TrainingModel> trainingModelList;
    private Context context;
    private TrainingsWorkoutDesignBinding trainingsWorkoutDesignBinding;

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
//        View view = LayoutInflater.from(context).inflate(R.layout.trainings_workout_design,parent,false);
        trainingsWorkoutDesignBinding = TrainingsWorkoutDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(trainingsWorkoutDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingRecyclerViewAdapter.viewHolder holder, int position) {

        TrainingModel trainingModel = trainingModelList.get(position);
        holder.binding.traingWorkoutTxt.setText(trainingModel.getWorkout());
        holder.binding.traingWorkoutImage.setImageResource(trainingModel.getImage());
        holder.binding.traingWorkoutLevelTxt.setText(trainingModel.getLevel());
        holder.binding.traingWorkoutTimeTxt.setText(trainingModel.getTime());

        holder.binding.traingWorkoutImage.setOnClickListener(new View.OnClickListener() {
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
        private TrainingsWorkoutDesignBinding binding;
        public viewHolder(@NonNull TrainingsWorkoutDesignBinding itemView) {
            super(itemView.getRoot());
//            traingLevelTxt = itemView.findViewById(R.id.traingWorkoutLevelTxt);
//            traingTimetxt = itemView.findViewById(R.id.traingWorkoutTimeTxt);
//            traingImage = itemView.findViewById(R.id.traingWorkoutImage);
//            traingTxt = itemView.findViewById(R.id.traingWorkoutTxt);
            binding = itemView;
        }
    }
}
