package com.nishiket.homeworkout.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FilterDesignBinding;
import com.nishiket.homeworkout.model.FilterModel;

import java.util.List;

public class FilterDurationRecyclerViewAdapter extends RecyclerView.Adapter<FilterDurationRecyclerViewAdapter.viewHolder> {

    private List<FilterModel> filterModelList;
    private Context context;
    private FilterDesignBinding filterDesignBinding;

    public FilterDurationRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setFilterModelList(List<FilterModel> filterModelList) {
        this.filterModelList = filterModelList;
    }

    @NonNull
    @Override
    public FilterDurationRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.filter_design,parent,false);
        filterDesignBinding = FilterDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(filterDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterDurationRecyclerViewAdapter.viewHolder holder, int position) {
        FilterModel filterModel = filterModelList.get(position);
        holder.binding.filterTxt.setText(filterModel.getFilter());
        holder.binding.filterBg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                holder.binding.filterBg.setBackgroundResource(R.color.primary_light);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(filterModelList == null){
            return 0;
        }
        else {
            return filterModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private FilterDesignBinding binding;
        public viewHolder(@NonNull FilterDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            filter_bg = itemView.findViewById(R.id.filter_bg);
//            filterTxt = itemView.findViewById(R.id.filterTxt);
        }
    }
}
