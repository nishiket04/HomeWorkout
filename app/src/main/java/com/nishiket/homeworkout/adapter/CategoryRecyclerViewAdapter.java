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
import com.nishiket.homeworkout.databinding.CategoryDesignBinding;
import com.nishiket.homeworkout.model.CategoryModel;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.viewHolder> {

    private List<CategoryModel> categoryModelList;
    private Context context;
    private CategoryDesignBinding categoryDesignBinding;

    public CategoryRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        categoryDesignBinding = CategoryDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(categoryDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.viewHolder holder, int position) {
        CategoryModel categoryModel = categoryModelList.get(position);
        holder.binding.categoryImage.setImageResource(categoryModel.getImage());
        holder.binding.categoryTxt.setText(categoryModel.getCategory());

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
        private CategoryDesignBinding binding;
        public viewHolder(@NonNull CategoryDesignBinding itemView) {
            super(itemView.getRoot());
                binding = itemView;
//            categoryTxt = itemView.findViewById(R.id.categoryTxt);
//            categoryImage = itemView.findViewById(R.id.categoryImage);
        }
    }
}
