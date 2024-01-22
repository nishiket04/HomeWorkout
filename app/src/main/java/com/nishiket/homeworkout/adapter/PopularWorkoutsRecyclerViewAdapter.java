package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.PopularWorkoutModel;

import java.util.List;

public class PopularWorkoutsRecyclerViewAdapter extends RecyclerView.Adapter<PopularWorkoutsRecyclerViewAdapter.viewHolder> {

    private List<PopularWorkoutModel> popularWorkoutModelList;
    private Context context;

    public PopularWorkoutsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setPopularWorkoutModelList(List<PopularWorkoutModel> popularWorkoutModelList) {
        this.popularWorkoutModelList = popularWorkoutModelList;
    }

    @NonNull
    @Override
    public PopularWorkoutsRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_workout_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularWorkoutsRecyclerViewAdapter.viewHolder holder, int position) {

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
        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
