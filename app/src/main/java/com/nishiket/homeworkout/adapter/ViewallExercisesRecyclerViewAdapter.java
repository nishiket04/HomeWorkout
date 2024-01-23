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
import com.nishiket.homeworkout.model.ViewallExercisesModel;

import java.util.List;

public class ViewallExercisesRecyclerViewAdapter extends RecyclerView.Adapter<ViewallExercisesRecyclerViewAdapter.viewHolder> {
    private Context context;
    private List<ViewallExercisesModel> viewallExercisesModelList;

    public ViewallExercisesRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setViewallExercisesModelList(List<ViewallExercisesModel> viewallExercisesModelList) {
        this.viewallExercisesModelList = viewallExercisesModelList;
    }

    @NonNull
    @Override
    public ViewallExercisesRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewall_exercises_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewallExercisesRecyclerViewAdapter.viewHolder holder, int position) {
        ViewallExercisesModel viewallExercisesModel = viewallExercisesModelList.get(position);
        holder.viewallExercisesTimeTxt.setText(viewallExercisesModel.getTime());
        holder.viewallExercisesTxt.setText(viewallExercisesModel.getTitle());
        holder.viewallExercisesImage.setImageResource(viewallExercisesModel.getImage());
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
        TextView viewallExercisesTxt,viewallExercisesTimeTxt;
        ImageView viewallExercisesImage,viewallExercisesInfoImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            viewallExercisesImage = itemView.findViewById(R.id.viewExercisesImage);
            viewallExercisesInfoImage = itemView.findViewById(R.id.viewExercisesInfoImage);
            viewallExercisesTxt = itemView.findViewById(R.id.viewExercisesTxt);
            viewallExercisesTimeTxt = itemView.findViewById(R.id.viewExercisesTimeTxt);
        }
    }
}
