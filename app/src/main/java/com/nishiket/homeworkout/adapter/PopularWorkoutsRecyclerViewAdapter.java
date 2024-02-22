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
import com.nishiket.homeworkout.databinding.PopularWorkoutDesignBinding;
import com.nishiket.homeworkout.model.PopularWorkoutModel;

import java.util.List;

public class PopularWorkoutsRecyclerViewAdapter extends RecyclerView.Adapter<PopularWorkoutsRecyclerViewAdapter.viewHolder> {

    private List<PopularWorkoutModel> popularWorkoutModelList;
    private Context context;
    private PopularWorkoutDesignBinding popularWorkoutDesignBinding;

    public PopularWorkoutsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setPopularWorkoutModelList(List<PopularWorkoutModel> popularWorkoutModelList) {
        this.popularWorkoutModelList = popularWorkoutModelList;
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

        PopularWorkoutModel popularWorkoutModel = popularWorkoutModelList.get(position);
        holder.binding.popualrWorkoutImage.setImageResource(popularWorkoutModel.getImage());
        holder.binding.popularWorkoutTxt.setText(popularWorkoutModel.getWorkout());
        holder.binding.popularWorkoutLevelTxt.setText(popularWorkoutModel.getType());
        holder.binding.popularWorkoutTimeTxt.setText(popularWorkoutModel.getTime());

    }

    @Override
    public int getItemCount() {
        if (popularWorkoutModelList == null){
            return 0;
        }else {
            return popularWorkoutModelList.size();
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
