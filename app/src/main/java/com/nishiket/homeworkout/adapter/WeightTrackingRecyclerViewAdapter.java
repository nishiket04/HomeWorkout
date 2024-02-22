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
import com.nishiket.homeworkout.databinding.WeigthTrackingDesignBinding;
import com.nishiket.homeworkout.model.WeightTrackingModel;

import java.util.List;

public class WeightTrackingRecyclerViewAdapter extends RecyclerView.Adapter<WeightTrackingRecyclerViewAdapter.viewHolder> {

    private List<WeightTrackingModel> weightTrackingModelList;
    private Context context;
    private WeigthTrackingDesignBinding weigthTrackingDesignBinding;
    public WeightTrackingRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWeightTrackingModelList(List<WeightTrackingModel> weightTrackingModelList) {
        this.weightTrackingModelList = weightTrackingModelList;
    }

    @NonNull
    @Override
    public WeightTrackingRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.weigth_tracking_design,parent,false);
        weigthTrackingDesignBinding = WeigthTrackingDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(weigthTrackingDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeightTrackingRecyclerViewAdapter.viewHolder holder, int position) {
        WeightTrackingModel weightTrackingModel = weightTrackingModelList.get(position);
        holder.binding.arrow.setImageResource(weightTrackingModel.getImage());
        holder.binding.weightInfo.setText(weightTrackingModel.getWeightInfo());
        holder.binding.weight.setText(weightTrackingModel.getWeight());
        holder.binding.time.setText(weightTrackingModel.getTime());
        holder.binding.date.setText(weightTrackingModel.getDate());

    }

    @Override
    public int getItemCount() {
        if (weightTrackingModelList == null){
            return 0;
        }
        else {
            return weightTrackingModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private WeigthTrackingDesignBinding binding;
        public viewHolder(@NonNull WeigthTrackingDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            date = itemView.findViewById(R.id.date);
//            time = itemView.findViewById(R.id.time);
//            weight = itemView.findViewById(R.id.weight);
//            weightInfo = itemView.findViewById(R.id.weightInfo);
//            arrow = itemView.findViewById(R.id.arrow);

        }
    }
}
