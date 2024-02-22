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
import com.nishiket.homeworkout.databinding.ViewallCategoryDesignBinding;
import com.nishiket.homeworkout.model.ViewallCategoryModel;

import java.util.List;

public class ViewallCategoryRecyclerViewAdapter extends RecyclerView.Adapter<ViewallCategoryRecyclerViewAdapter.viewHolder> {

    private Context context;
    private List<ViewallCategoryModel> viewallCategoryModelList;
    private ViewallCategoryDesignBinding viewallCategoryDesignBinding;

    public ViewallCategoryRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setViewallCategoryModelList(List<ViewallCategoryModel> viewallCategoryModelList) {
        this.viewallCategoryModelList = viewallCategoryModelList;
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
            ViewallCategoryModel viewallCategoryModel = viewallCategoryModelList.get(position);
            holder.binding.viewallCategoryTimeTxt.setText(viewallCategoryModel.getWorkouts());
            holder.binding.viewallCategoryTxt.setText(viewallCategoryModel.getCategory());
            holder.binding.viewallCategoryImage.setImageResource(viewallCategoryModel.getImage());
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
