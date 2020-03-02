package com.example.electrocard;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.*;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>
{
    public static class CardViewHolder extends ViewHolder
    {
        public LinearLayout convenient;

        public CardViewHolder(LinearLayout v)
        {
            super(v);
            convenient = v;
        }
    }

    private CardModel model;

    public CardAdapter()
    {
        super();
        this.model = CardModel.getSingleton();
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position)
    {
        //holder.convenient.setText(model.theDays.get(position).name);
        LinearLayout lin = holder.convenient;
        TextView nameTV = lin.findViewById(R.id.nameTV);
        TextView numberTV = lin.findViewById(R.id.numberTV);
        TextView emailTV = lin.findViewById(R.id.emailTV);
        nameTV.setText(model.cardList.get(position).name);
        numberTV.setText(model.cardList.get(position).name);
        emailTV.setText(model.cardList.get(position).name);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.day_text_view, parent, false);
        LinearLayout lin = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        CardViewHolder vh = new CardViewHolder(lin);
        return vh;
    }

    @Override
    public int getItemCount()
    {
        return model.cardList.size();
    }
}
