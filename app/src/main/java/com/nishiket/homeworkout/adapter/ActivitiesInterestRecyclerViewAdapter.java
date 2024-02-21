package com.nishiket.homeworkout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.ActivitiesInterestCardDesignBinding;
import com.nishiket.homeworkout.model.ActivitiesInterestModel;

import java.util.List;

public class ActivitiesInterestRecyclerViewAdapter extends RecyclerView.Adapter<ActivitiesInterestRecyclerViewAdapter.viewHolder> {

   private List<ActivitiesInterestModel> activitiesInterestModelList;
   private Context context;
   private ActivitiesInterestCardDesignBinding activitiesInterestCardDesignBinding;

    public ActivitiesInterestRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void  setActivitiesInterestModelList(List<ActivitiesInterestModel> activitiesInterestModelList){
        this.activitiesInterestModelList= activitiesInterestModelList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activitiesInterestCardDesignBinding = ActivitiesInterestCardDesignBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(activitiesInterestCardDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesInterestRecyclerViewAdapter.viewHolder holder, int position) {
        ActivitiesInterestModel activitiesInterestModel =   activitiesInterestModelList.get(position);
        holder.binding.emojiActivities.setImageResource(activitiesInterestModel.getImage());
        holder.binding.activitiTxt.setText(activitiesInterestModel.getActivitie());
        holder.binding.activitieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.activitiChcekBox.setChecked(true);
                holder.binding.activitieCard.setBackgroundResource(R.drawable.card_selected_design);
                holder.binding.imageActivities.setBackgroundResource(R.drawable.card_selected_emoji_design);
            }
        });

        holder.binding.activitiChcekBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    holder.binding.activitieCard.setBackgroundResource(R.drawable.card_selected_design);
                    holder.binding.imageActivities.setBackgroundResource(R.drawable.card_selected_emoji_design);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return activitiesInterestModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
      private ActivitiesInterestCardDesignBinding binding;
        public viewHolder(@NonNull ActivitiesInterestCardDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }
}
