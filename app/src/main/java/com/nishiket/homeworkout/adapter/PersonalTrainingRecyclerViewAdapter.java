package com.nishiket.homeworkout.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.PersonalTrainingModel;

import org.w3c.dom.Text;

import java.util.List;

public class PersonalTrainingRecyclerViewAdapter extends RecyclerView.Adapter<PersonalTrainingRecyclerViewAdapter.viewHolder> {

    private List<PersonalTrainingModel> personalTrainingModelList;
    private Context context;
    private int selectItem=-1;

    public interface OnItemClickListener {
        void onItemClick(int position, PersonalTrainingModel personalTrainingModel);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public PersonalTrainingRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setPersonalTrainingModelList(List<PersonalTrainingModel> personalTrainingModelList) {
        this.personalTrainingModelList = personalTrainingModelList;
    }

    public PersonalTrainingModel getData(){
        if(selectItem != -1 && selectItem< personalTrainingModelList.size()){
            return personalTrainingModelList.get(selectItem);
        }
        else {
            return null;
        }
    }

    @NonNull
    @Override
    public PersonalTrainingRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.personal_treaining_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalTrainingRecyclerViewAdapter.viewHolder holder, int position) {
            PersonalTrainingModel personalTrainingModel = personalTrainingModelList.get(position);
            holder.personalTrainingTimeTxt.setText(personalTrainingModel.getTime());
            holder.personalTrainingLevelTxt.setText(personalTrainingModel.getLevel());
            holder.personalTrainingTxt.setText(personalTrainingModel.getWorkout());
            holder.personalTrainingImage.setImageResource(personalTrainingModel.getImage());
            holder.personalTrainingImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectItem = position;
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, personalTrainingModel);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        if(personalTrainingModelList == null){
            return 0;
        }
        else {
            return personalTrainingModelList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView personalTrainingImage;
        TextView personalTrainingTxt,personalTrainingTimeTxt,personalTrainingLevelTxt;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            personalTrainingTxt = itemView.findViewById(R.id.personalTrainingTxt);
            personalTrainingImage = itemView.findViewById(R.id.personalTrainingImage);
            personalTrainingLevelTxt = itemView.findViewById(R.id.personalTrainingLevelTxt);
            personalTrainingTimeTxt = itemView.findViewById(R.id.personalTrainingTimeTxt);
        }
    }
}
