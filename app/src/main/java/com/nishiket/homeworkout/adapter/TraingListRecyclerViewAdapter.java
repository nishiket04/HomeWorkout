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
import com.nishiket.homeworkout.model.TraingListModel;

import java.util.List;

public class TraingListRecyclerViewAdapter extends RecyclerView.Adapter<TraingListRecyclerViewAdapter.viewHolder> {

    private List<TraingListModel> traingListModelList;
    private Context context;

    public TraingListRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setTraingListModelList(List<TraingListModel> traingListModelList) {
        this.traingListModelList = traingListModelList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.traing_level_caed_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TraingListRecyclerViewAdapter.viewHolder holder, int position) {
        TraingListModel traingListModel = traingListModelList.get(position);
        holder.mainTxt.setText(traingListModel.getMainTxt());
        holder.subTxt.setText(traingListModel.getSubTxt());
        holder.traingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.traingCard.setBackgroundResource(R.drawable.card_selected_design);
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
        private ConstraintLayout traingCard;
        private TextView mainTxt,subTxt;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            traingCard = itemView.findViewById(R.id.traingCard);
            mainTxt = itemView.findViewById(R.id.traingTxt);
            subTxt = itemView.findViewById(R.id.traingSubTxt);

        }
    }
}
