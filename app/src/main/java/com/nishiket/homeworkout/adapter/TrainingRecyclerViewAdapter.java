package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.databinding.TrainingsWorkoutDesignBinding;
import com.nishiket.homeworkout.model.PersonalTrainingModel;

import java.util.List;

public class TrainingRecyclerViewAdapter extends RecyclerView.Adapter<TrainingRecyclerViewAdapter.viewHolder> {
    private List<PersonalTrainingModel> personalTrainingModelList;
    private Context context;
    private TrainingsWorkoutDesignBinding trainingsWorkoutDesignBinding;

    public TrainingRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setTrainingModelList(List<PersonalTrainingModel> personalTrainingModelList) {
        this.personalTrainingModelList = personalTrainingModelList;
    }

    public interface OnItemClickedListiner{
        void onItemClicked(int position,PersonalTrainingModel personalTrainingModel);
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

        PersonalTrainingModel personalTrainingModel = personalTrainingModelList.get(position);
        holder.binding.traingWorkoutTxt.setText(personalTrainingModel.getWorkout());
        Glide.with(context).load(personalTrainingModel.getImage()).into(holder.binding.traingWorkoutImage);
        holder.binding.traingWorkoutLevelTxt.setText(personalTrainingModel.getLevel());
        holder.binding.traingWorkoutTimeTxt.setText(personalTrainingModel.getTime());

        holder.binding.traingWorkoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickedListiner !=null){
                    onItemClickedListiner.onItemClicked(position,personalTrainingModel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(personalTrainingModelList == null){
            return 0;
        }
        else {
            return personalTrainingModelList.size();
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
