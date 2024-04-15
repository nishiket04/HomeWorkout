package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.databinding.ExercisesDesignBinding;
import com.nishiket.homeworkout.model.ExercisesModel;

import java.util.List;

public class ExercisesRecyclerViewAdapter extends RecyclerView.Adapter<ExercisesRecyclerViewAdapter.viewHolder> {
    private List<ExercisesModel> exercisesModelList;
    private Context context;
    private onClickedItem onClickedItem;
    private ExercisesDesignBinding exercisesDesignBinding;
    public ExercisesRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setOnClickedItem(ExercisesRecyclerViewAdapter.onClickedItem onClickedItem) {
        this.onClickedItem = onClickedItem;
    }

    public interface onClickedItem{
        void onCliced(int i,ExercisesModel exercisesModel);
    }

    public void setExercisesModelList(List<ExercisesModel> exercisesModelList) {
        this.exercisesModelList = exercisesModelList;
    }

    @NonNull
    @Override
    public ExercisesRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.exercises_design,parent,false);
        exercisesDesignBinding = ExercisesDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(exercisesDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesRecyclerViewAdapter.viewHolder holder, int position) {
        ExercisesModel exercisesModel = exercisesModelList.get(position);
        holder.binding.exercisesTxt.setText(exercisesModel.getExercises());
        holder.binding.exercisesTimeTxt.setText(exercisesModel.getTime());
        Glide.with(context).load(exercisesModel.getImage()).into(holder.binding.exercisesImage);
        holder.binding.exercisesInfoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickedItem != null){
                    onClickedItem.onCliced(holder.getAdapterPosition(),exercisesModelList.get(holder.getAdapterPosition()));
                }
            }
        });

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
       private ExercisesDesignBinding binding;

        public viewHolder(@NonNull ExercisesDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
