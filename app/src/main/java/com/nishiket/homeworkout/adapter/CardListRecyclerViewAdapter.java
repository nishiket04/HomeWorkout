package com.nishiket.homeworkout.adapter;

import static androidx.recyclerview.widget.RecyclerView.*;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.model.CardList;

import java.util.List;

public class CardListRecyclerViewAdapter extends RecyclerView.Adapter<CardListRecyclerViewAdapter.viewHolder> {
    private List<CardList> cardListList;
    Context context;
    private int selectedItem = NO_POSITION;

    public CardListRecyclerViewAdapter (Context context){
        this.context=context;
    }

    public void setActiveComplainList(List<CardList> cardListList){
        this.cardListList = cardListList;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.choose_gender_design,parent,false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardListRecyclerViewAdapter.viewHolder holder, int position) {
        CardList cardList = cardListList.get(position);
        holder.gender.setText(cardList.getGender().toString());
        holder.emoji.setImageResource(cardList.getImage());
        holder.card.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldPosition = selectedItem;
                selectedItem = holder.getAdapterPosition();
                notifyItemChanged(oldPosition);
                notifyItemChanged(selectedItem);
            }
        });
        if (selectedItem == position){
            holder.card.setBackgroundResource(R.drawable.card_selected_design);
            holder.imageCard.setBackgroundResource(R.drawable.card_selected_emoji_design);
        }

    }

    @Override
    public int getItemCount() {
        if(cardListList == null){
            return 0;
        }
        else {
            return cardListList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView emoji;
        TextView gender;
        ConstraintLayout card,imageCard;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            emoji = itemView.findViewById(R.id.emojiGenderCard);
            gender = itemView.findViewById(R.id.genderTxt);
            card = itemView.findViewById(R.id.card);
            imageCard = itemView.findViewById(R.id.imageCard);
        }

    }
}
