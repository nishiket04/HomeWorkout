package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.databinding.PopularWorkoutDesignBinding;
import com.nishiket.homeworkout.model.PersonalTrainingModel;
import com.nishiket.homeworkout.model.PopularWorkoutModel;

import java.util.List;

public class PopularWorkoutsRecyclerViewAdapter extends RecyclerView.Adapter<PopularWorkoutsRecyclerViewAdapter.viewHolder> {

    private List<PersonalTrainingModel> personalTrainingModelList;
    private Context context;
    private PopularWorkoutDesignBinding popularWorkoutDesignBinding;

    public PopularWorkoutsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setPopularWorkoutModelList(List<PersonalTrainingModel> personalTrainingModelList) {
        this.personalTrainingModelList = personalTrainingModelList;
    }

    @NonNull
    @Override
    public PopularWorkoutsRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.popular_workout_design,parent,false);
        popularWorkoutDesignBinding = PopularWorkoutDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(popularWorkoutDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularWorkoutsRecyclerViewAdapter.viewHolder holder, int position) {

        PersonalTrainingModel personalTrainingModel = personalTrainingModelList.get(position);
        Glide.with(context).load(personalTrainingModel.getImage()).into(holder.binding.popualrWorkoutImage);
        holder.binding.popularWorkoutTxt.setText(personalTrainingModel.getWorkout());
        holder.binding.popularWorkoutLevelTxt.setText(personalTrainingModel.getLevel());
        holder.binding.popularWorkoutTimeTxt.setText(personalTrainingModel.getTime()+" min");

    }

    @Override
    public int getItemCount() {
        if (personalTrainingModelList == null){
            return 0;
        }else {
            return personalTrainingModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private PopularWorkoutDesignBinding binding;
        public viewHolder(@NonNull PopularWorkoutDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            popularWorkoutTxt = itemView.findViewById(R.id.popularWorkoutTxt);
//            popularWorkoutLevelTxt = itemView.findViewById(R.id.popularWorkoutLevelTxt);
//            popularWorkoutTimeTxt = itemView.findViewById(R.id.popularWorkoutTimeTxt);
//            popularWorkoutImage = itemView.findViewById(R.id.popualrWorkoutImage);
        }
    }
}
