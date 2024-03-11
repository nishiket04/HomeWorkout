package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.databinding.ViewallCategoryDesignBinding;
import com.nishiket.homeworkout.model.CategoryModel;

import java.util.List;

public class ViewallCategoryRecyclerViewAdapter extends RecyclerView.Adapter<ViewallCategoryRecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<CategoryModel> categoryModelList;
    private ViewallCategoryDesignBinding viewallCategoryDesignBinding;

    public ViewallCategoryRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setViewallCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewallCategoryRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.viewall_category_design,parent,false);
        viewallCategoryDesignBinding = ViewallCategoryDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(viewallCategoryDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewallCategoryRecyclerViewAdapter.viewHolder holder, int position) {
            CategoryModel categoryModel = categoryModelList.get(position);
            holder.binding.viewallCategoryTimeTxt.setText(""+categoryModel.getWorkouts()+" workouts");
        Log.d("data", "onBindViewHolder: "+categoryModelList.size());
            holder.binding.viewallCategoryTxt.setText(categoryModel.getCategory());
            Glide.with(context).load(categoryModel.getImage()).into(holder.binding.viewallCategoryImage);
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
        private ViewallCategoryDesignBinding binding;
        public viewHolder(@NonNull ViewallCategoryDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            viewallCategoryImage = itemView.findViewById(R.id.viewallCategoryImage);
//            viewallCategoryTxt = itemView.findViewById(R.id.viewallCategoryTxt);
//            viewallCategoryTimeTxt = itemView.findViewById(R.id.viewallCategoryTimeTxt);
//            viewallCategoryInfoImage = itemView.findViewById(R.id.viewallCategoryInfoImage);
        }
    }
}
