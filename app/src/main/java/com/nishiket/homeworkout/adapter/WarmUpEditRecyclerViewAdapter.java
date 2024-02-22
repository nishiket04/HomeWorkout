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
import com.nishiket.homeworkout.databinding.WarmupEditDesignBinding;
import com.nishiket.homeworkout.model.WarmUpEditModel;

import java.util.List;

public class WarmUpEditRecyclerViewAdapter extends RecyclerView.Adapter<WarmUpEditRecyclerViewAdapter.viewHolder> {

    private List<WarmUpEditModel> warmUpEditModelList;
    private Context context;
    private WarmupEditDesignBinding warmupEditDesignBinding;

    public WarmUpEditRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWarmUpEditModelList(List<WarmUpEditModel> warmUpEditModelList) {
        this.warmUpEditModelList = warmUpEditModelList;
    }

    @NonNull
    @Override
    public WarmUpEditRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.warmup_edit_design,parent,false);
        warmupEditDesignBinding = WarmupEditDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(warmupEditDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WarmUpEditRecyclerViewAdapter.viewHolder holder, int position) {
        WarmUpEditModel warmUpEditModel = warmUpEditModelList.get(position);
        holder.binding.warmupEditTxt.setText(warmUpEditModel.getWorkout());
        holder.binding.warmupEditTimeTxt.setText(warmUpEditModel.getTime());
        holder.binding.warmupEditImage.setImageResource(warmUpEditModel.getImage());

    }

    @Override
    public int getItemCount() {
        if (warmUpEditModelList == null){
            return 0;
        }
        else {
            return warmUpEditModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private WarmupEditDesignBinding binding;
        public viewHolder(@NonNull WarmupEditDesignBinding itemView) {
            super(itemView.getRoot());
//            warmupEditAddImage = itemView.findViewById(R.id.warmupEditAddImage);
//            warmupEditImage = itemView.findViewById(R.id.warmupEditImage);
//            warmupEditImageditMinusImage = itemView.findViewById(R.id.warmupMinusImage);
//            warmupEditTxt = itemView.findViewById(R.id.warmupEditTxt);
//            warmupEditTimeTxt = itemView.findViewById(R.id.warmupEditTimeTxt);
//            warmupEditDeleteImage = itemView.findViewById(R.id.warmupEditDeleteImage);
            binding = itemView;
        }
    }
}
