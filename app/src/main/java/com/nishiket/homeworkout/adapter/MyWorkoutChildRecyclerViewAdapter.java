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
import com.nishiket.homeworkout.model.MyWorkoutChildModel;

import java.util.List;

public class MyWorkoutChildRecyclerViewAdapter extends RecyclerView.Adapter<MyWorkoutChildRecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<MyWorkoutChildModel> myWorkoutChildModelsList;

    public MyWorkoutChildRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setMyWorkoutChildModels(List<MyWorkoutChildModel> myWorkoutChildModels) {
        this.myWorkoutChildModelsList = myWorkoutChildModels;
    }

    @NonNull
    @Override
    public MyWorkoutChildRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_workout_history_child_desing,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyWorkoutChildRecyclerViewAdapter.viewHolder holder, int position) {
        MyWorkoutChildModel myWorkoutChildModel = myWorkoutChildModelsList.get(position);
        holder.myWorkoutImage.setImageResource(myWorkoutChildModel.getImage());
        holder.myWorkoutTxt.setText(myWorkoutChildModel.getWokrout());
        holder.myWorkoutTimeTxt.setText(myWorkoutChildModel.getTime());
    }

    @Override
    public int getItemCount() {
        if(myWorkoutChildModelsList == null){
            return 0;
        }
        else {
            return myWorkoutChildModelsList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView myWorkoutImage;
        TextView myWorkoutTxt,myWorkoutTimeTxt;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            myWorkoutImage = itemView.findViewById(R.id.myWorkutImage);
            myWorkoutTxt = itemView.findViewById(R.id.myWorkutWorkout);
            myWorkoutTimeTxt = itemView.findViewById(R.id.myWorkutTime);
        }
    }
}
