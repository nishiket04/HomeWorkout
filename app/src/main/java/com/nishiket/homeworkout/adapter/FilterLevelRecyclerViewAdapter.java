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
import com.nishiket.homeworkout.model.FilterModel;

import java.util.List;

public class FilterLevelRecyclerViewAdapter extends RecyclerView.Adapter<FilterLevelRecyclerViewAdapter.viewHolder> {

    private List<FilterModel> filterModelList;
    private Context context;

    public FilterLevelRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setFilterModelList(List<FilterModel> filterModelList) {
        this.filterModelList = filterModelList;
    }

    @NonNull
    @Override
    public FilterLevelRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterLevelRecyclerViewAdapter.viewHolder holder, int position) {
        FilterModel filterModel = filterModelList.get(position);
        holder.filterTxt.setText(filterModel.getFilter());
        holder.filter_bg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                holder.filter_bg.setBackgroundResource(R.color.primary_light);
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
        TextView filterTxt;
        ConstraintLayout filter_bg;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            filter_bg = itemView.findViewById(R.id.filter_bg);
            filterTxt = itemView.findViewById(R.id.filterTxt);
        }
    }
}
