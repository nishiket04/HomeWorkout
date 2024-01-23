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
import com.nishiket.homeworkout.model.ViewallCategoryModel;

import java.util.List;

public class ViewallCategoryRecyclerViewAdapter extends RecyclerView.Adapter<ViewallCategoryRecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<ViewallCategoryModel> viewallCategoryModelList;

    public ViewallCategoryRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setViewallCategoryModelList(List<ViewallCategoryModel> viewallCategoryModelList) {
        this.viewallCategoryModelList = viewallCategoryModelList;
    }

    @NonNull
    @Override
    public ViewallCategoryRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewall_category_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewallCategoryRecyclerViewAdapter.viewHolder holder, int position) {
            ViewallCategoryModel viewallCategoryModel = viewallCategoryModelList.get(position);
            holder.viewallCategoryTimeTxt.setText(viewallCategoryModel.getWorkouts());
            holder.viewallCategoryTxt.setText(viewallCategoryModel.getCategory());
            holder.viewallCategoryImage.setImageResource(viewallCategoryModel.getImage());
    }

    @Override
    public int getItemCount() {
        if(viewallCategoryModelList == null){
            return 0;
        }
        else {
            return viewallCategoryModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView viewallCategoryTimeTxt,viewallCategoryTxt;
        ImageView viewallCategoryImage,viewallCategoryInfoImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            viewallCategoryImage = itemView.findViewById(R.id.viewallCategoryImage);
            viewallCategoryTxt = itemView.findViewById(R.id.viewallCategoryTxt);
            viewallCategoryTimeTxt = itemView.findViewById(R.id.viewallCategoryTimeTxt);
            viewallCategoryInfoImage = itemView.findViewById(R.id.viewallCategoryInfoImage);
        }
    }
}
