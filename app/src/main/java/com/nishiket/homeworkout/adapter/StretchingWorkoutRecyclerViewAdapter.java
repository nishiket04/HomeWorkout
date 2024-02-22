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
import com.nishiket.homeworkout.databinding.WarmupWorkoutStretchingDesignBinding;
import com.nishiket.homeworkout.model.StretchingWorkoutModel;

import java.util.List;

public class StretchingWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<StretchingWorkoutRecyclerViewAdapter.viewHolder> {
    private List<StretchingWorkoutModel> stretchingWorkoutModelList;
    private Context context;
    private WarmupWorkoutStretchingDesignBinding warmupWorkoutStretchingDesignBinding;

    public StretchingWorkoutRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setStretchingWorkoutModelList(List<StretchingWorkoutModel> stretchingWorkoutModelList) {
        this.stretchingWorkoutModelList = stretchingWorkoutModelList;
    }

    @NonNull
    @Override
    public StretchingWorkoutRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.warmup_workout_stretching_design,parent,false);
        warmupWorkoutStretchingDesignBinding = WarmupWorkoutStretchingDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(warmupWorkoutStretchingDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StretchingWorkoutRecyclerViewAdapter.viewHolder holder, int position) {
        if (stretchingWorkoutModelList == null){
            holder.binding.exercisesTimeTxt1.setVisibility(View.GONE);
            holder.binding.exercisesTxt1.setText("Add");
            holder.binding.exercisesImage1.setImageResource(R.drawable.bicep);
            holder.binding.exercisesInfoImage1.setVisibility(View.GONE);
        }
        else {
            StretchingWorkoutModel stretchingWorkoutModel = stretchingWorkoutModelList.get(position);
            holder.binding.exercisesTimeTxt1.setText(stretchingWorkoutModel.getTime());
            holder.binding.exercisesTxt1.setText(stretchingWorkoutModel.getWorkout());
            holder.binding.exercisesImage1.setImageResource(stretchingWorkoutModel.getImage());
        }
    }

    @Override
    public int getItemCount() {
        if (stretchingWorkoutModelList == null){
            return 0;
        }
        else {
            return stretchingWorkoutModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private WarmupWorkoutStretchingDesignBinding binding;
        public viewHolder(@NonNull WarmupWorkoutStretchingDesignBinding itemView) {
            super(itemView.getRoot());
//            exercisesImage1 = itemView.findViewById(R.id.exercisesImage1);
//            exercisesInfoImage1 = itemView.findViewById(R.id.exercisesInfoImage1);
//            exercisesTxt1 = itemView.findViewById(R.id.exercisesTxt1);
//            exercisesTimeTxt1 = itemView.findViewById(R.id.exercisesTimeTxt1);
            binding = itemView;
        }
    }
}
