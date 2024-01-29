package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.MyWorkoutParentModel;

import java.util.List;

public class MyWorkoutParentRecyclerViewAdapter extends RecyclerView.Adapter<MyWorkoutParentRecyclerViewAdapter.viewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Context context;
    private List<MyWorkoutParentModel> myWorkoutParentModelList;

    public MyWorkoutParentRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setMyWorkoutParentModelList(List<MyWorkoutParentModel> myWorkoutParentModelList) {
        this.myWorkoutParentModelList = myWorkoutParentModelList;
    }

    @NonNull
    @Override
    public MyWorkoutParentRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_workour_history_parent_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyWorkoutParentRecyclerViewAdapter.viewHolder holder, int position) {
        MyWorkoutParentModel myWorkoutParentModel = myWorkoutParentModelList.get(position);
        holder.workoutTimeAndWorkoutsTxt.setText(myWorkoutParentModel.getWorkout());
        holder.myWorkoutDate.setText(myWorkoutParentModel.getDate());
        MyWorkoutChildRecyclerViewAdapter myWorkoutChildRecyclerViewAdapter = new MyWorkoutChildRecyclerViewAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        layoutManager.setInitialPrefetchItemCount(myWorkoutParentModel.getMyWorkoutChildModelsList().size());
        myWorkoutChildRecyclerViewAdapter.setMyWorkoutChildModels(myWorkoutParentModel.getMyWorkoutChildModelsList());
        holder.myWorkoutChildRecyclerView.setNestedScrollingEnabled(false);
        holder.myWorkoutChildRecyclerView.setLayoutManager(layoutManager);
        holder.myWorkoutChildRecyclerView.setAdapter(myWorkoutChildRecyclerViewAdapter);
        holder.myWorkoutChildRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        if(myWorkoutParentModelList == null){
            return 0;
        }
        else {
            return myWorkoutParentModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RecyclerView myWorkoutChildRecyclerView;
        TextView myWorkoutDate,workoutTimeAndWorkoutsTxt;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            myWorkoutChildRecyclerView = itemView.findViewById(R.id.myWorkoutChildRecyclerView);
            myWorkoutDate = itemView.findViewById(R.id.WorkoutDateTxt);
            workoutTimeAndWorkoutsTxt = itemView.findViewById(R.id.workoutTimeAndWorkoutsTxt);
        }
    }
}
