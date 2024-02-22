package com.nishiket.homeworkout.adapter;

import static com.nishiket.homeworkout.R.drawable.card_selected_design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.TraingLevelCaedDesignBinding;
import com.nishiket.homeworkout.model.TraingListModel;

import java.util.List;

public class TraingListRecyclerViewAdapter extends RecyclerView.Adapter<TraingListRecyclerViewAdapter.viewHolder> {

    private List<TraingListModel> traingListModelList;
    private Context context;
    private TraingLevelCaedDesignBinding traingLevelCaedDesignBinding;

    public TraingListRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setTraingListModelList(List<TraingListModel> traingListModelList) {
        this.traingListModelList = traingListModelList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.traing_level_caed_design,parent,false);
        traingLevelCaedDesignBinding = TraingLevelCaedDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(traingLevelCaedDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TraingListRecyclerViewAdapter.viewHolder holder, int position) {
        TraingListModel traingListModel = traingListModelList.get(position);
        holder.binding.traingTxt.setText(traingListModel.getMainTxt());
        holder.binding.traingSubTxt.setText(traingListModel.getSubTxt());
        holder.binding.traingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.traingCard.setBackgroundResource(R.drawable.card_selected_design);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(traingListModelList == null){
            return 0;
        }
        else {
            return traingListModelList.size();
        }
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private TraingLevelCaedDesignBinding binding;
        public viewHolder(@NonNull TraingLevelCaedDesignBinding itemView) {
            super(itemView.getRoot());
                binding = itemView;
//            traingCard = itemView.findViewById(R.id.traingCard);
//            mainTxt = itemView.findViewById(R.id.traingTxt);
//            subTxt = itemView.findViewById(R.id.traingSubTxt);

        }
    }
}
