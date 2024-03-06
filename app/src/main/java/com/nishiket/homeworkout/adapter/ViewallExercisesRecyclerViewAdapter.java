package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.databinding.ViewallExercisesDesignBinding;
import com.nishiket.homeworkout.model.ExercisesModel;

import java.util.List;

public class ViewallExercisesRecyclerViewAdapter extends RecyclerView.Adapter<ViewallExercisesRecyclerViewAdapter.viewHolder> {
    private Context context;
    private List<ExercisesModel> viewallExercisesModelList;
    private ViewallExercisesDesignBinding viewallExercisesDesignBinding;

    public ViewallExercisesRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setViewallExercisesModelList(List<ExercisesModel> viewallExercisesModelList) {
        this.viewallExercisesModelList = viewallExercisesModelList;
    }

    @NonNull
    @Override
    public ViewallExercisesRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.viewall_exercises_design,parent,false);
        viewallExercisesDesignBinding = ViewallExercisesDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(viewallExercisesDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewallExercisesRecyclerViewAdapter.viewHolder holder, int position) {
        ExercisesModel viewallExercisesModel = viewallExercisesModelList.get(position);
        holder.binding.viewExercisesTimeTxt.setText(viewallExercisesModel.getTime());
        holder.binding.viewExercisesTxt.setText(viewallExercisesModel.getExercises());
        Glide.with(context).load(viewallExercisesModel.getImage()).into(holder.binding.viewExercisesImage);
    }

    @Override
    public int getItemCount() {
        if(viewallExercisesModelList == null){
            return 0;
        }else {
            return viewallExercisesModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ViewallExercisesDesignBinding binding;
        public viewHolder(@NonNull ViewallExercisesDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            viewallExercisesImage = itemView.findViewById(R.id.viewExercisesImage);
//            viewallExercisesInfoImage = itemView.findViewById(R.id.viewExercisesInfoImage);
//            viewallExercisesTxt = itemView.findViewById(R.id.viewExercisesTxt);
//            viewallExercisesTimeTxt = itemView.findViewById(R.id.viewExercisesTimeTxt);
        }
    }
}
