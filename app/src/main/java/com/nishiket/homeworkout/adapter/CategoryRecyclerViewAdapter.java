package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.CategoryModel;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.viewHolder> {

    private List<CategoryModel> categoryModelList;
    private Context context;

    public CategoryRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.viewHolder holder, int position) {
        CategoryModel categoryModel = categoryModelList.get(position);
        holder.categoryImage.setImageResource(categoryModel.getImage());
        holder.categoryTxt.setText(categoryModel.getCategory());

    }

    @Override
    public int getItemCount() {
        if(categoryModelList == null){
            return 0;
        }
        else {
            return categoryModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryTxt;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTxt = itemView.findViewById(R.id.categoryTxt);
            categoryImage = itemView.findViewById(R.id.categoryImage);
        }
    }
}
