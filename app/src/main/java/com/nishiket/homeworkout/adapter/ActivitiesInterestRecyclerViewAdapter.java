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
import com.nishiket.homeworkout.model.ActivitiesInterestModel;

import java.util.List;

public class ActivitiesInterestRecyclerViewAdapter extends RecyclerView.Adapter<ActivitiesInterestRecyclerViewAdapter.viewHolder> {

   private List<ActivitiesInterestModel> activitiesInterestModelList;
   private Context context;

    public ActivitiesInterestRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void  setActivitiesInterestModelList(List<ActivitiesInterestModel> activitiesInterestModelList){
        this.activitiesInterestModelList= activitiesInterestModelList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activities_interest_card_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesInterestRecyclerViewAdapter.viewHolder holder, int position) {
        ActivitiesInterestModel activitiesInterestModel =   activitiesInterestModelList.get(position);
        holder.emojiActivities.setImageResource(activitiesInterestModel.getImage());
        holder.activtiTxt.setText(activitiesInterestModel.getActivitie());
        holder.activitieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.activitiCheckBox.setChecked(true);
                holder.activitieCard.setBackgroundResource(R.drawable.card_selected_design);
                holder.imageActivities.setBackgroundResource(R.drawable.card_selected_emoji_design);
            }
        });

        holder.activitiCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    holder.activitieCard.setBackgroundResource(R.drawable.card_selected_design);
                    holder.imageActivities.setBackgroundResource(R.drawable.card_selected_emoji_design);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return activitiesInterestModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
       private TextView activtiTxt;
       private CheckBox activitiCheckBox;
       private ConstraintLayout activitieCard,imageActivities;
       private ImageView emojiActivities;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            activtiTxt = itemView.findViewById(R.id.activitiTxt);
            activitiCheckBox = itemView.findViewById(R.id.activitiChcekBox);
            emojiActivities = itemView.findViewById(R.id.emojiActivities);
            activitieCard = itemView.findViewById(R.id.activitieCard);
            imageActivities = itemView.findViewById(R.id.imageActivities);

        }
    }
}
