package com.example.electrocard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        LinearLayout lin = holder.convenient;
        TextView nameTV = lin.findViewById(R.id.nameTV);
        TextView numberTV = lin.findViewById(R.id.numberTV);
        TextView emailTV = lin.findViewById(R.id.emailTV);
        TextView shareTV = lin.findViewById(R.id.shareTV);

        lin.setBackgroundResource(model.cardList.get(position).background);
        nameTV.setText(model.cardList.get(position).fName + " " + model.cardList.get(position).lName);
        numberTV.setText(model.cardList.get(position).number);
        emailTV.setText(model.cardList.get(position).email);
        shareTV.setText("Share: ID - " + model.cardList.get(position).id);


        final Intent ini = new Intent(ViewCardsActivity.myContext, EditCardActivity.class);
        ini.putExtra(EditCardActivity.NAME_KEY, model.cardList.get(position).fName + " " + model.cardList.get(position).lName);
        ini.putExtra(EditCardActivity.PHONE_KEY, model.cardList.get(position).number);
        ini.putExtra(EditCardActivity.EMAIL_KEY, model.cardList.get(position).email);

        Button editBTN = lin.findViewById(R.id.editBTN);
        editBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ViewCardsActivity.myContext.startActivity(ini);
            }
        });

    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
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