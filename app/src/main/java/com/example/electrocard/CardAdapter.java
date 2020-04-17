package com.example.electrocard;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.*;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>
{
    static int cardPOS;

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

        cardPOS = position;

        threadLoadCards(lin, nameTV, numberTV, emailTV, shareTV, position);

        /*lin.setBackgroundResource(model.cardList.get(position).backgroundID);
        nameTV.setText(model.cardList.get(position).firstName + " " + model.cardList.get(position).lastName);
        numberTV.setText(model.cardList.get(position).phoneNumber);
        emailTV.setText(model.cardList.get(position).emailAddress);
        shareTV.setText("Share: ID - " + model.cardList.get(position).cardID);


        final Intent ini = new Intent(ViewCardsActivity.myContext, EditCardActivity.class);
        ini.putExtra(EditCardActivity.BG_KEY, model.cardList.get(position).backgroundID);
        ini.putExtra(EditCardActivity.NAME_KEY, model.cardList.get(position).firstName + " " + model.cardList.get(position).lastName);
        ini.putExtra(EditCardActivity.PHONE_KEY, model.cardList.get(position).phoneNumber);
        ini.putExtra(EditCardActivity.EMAIL_KEY, model.cardList.get(position).emailAddress);*/

        Button editBTN = lin.findViewById(R.id.editBTN);
        editBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //ViewCardsActivity.myContext.startActivity(ini);
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
        return model.dbCards.size();
    }

    public static void threadLoadCards(final LinearLayout lin, final TextView nameTV, final TextView numberTV, final TextView emailTV, final TextView shareTV, final int pos)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                List<Card> cardsList = MainActivity.getDB().electroDao().getAllCards();
                int position = pos;
                while (position < cardsList.size())
                {
                    Log.d("CARD OUTPUT", cardsList.get(position).firstName);
                    final int back = cardsList.get(position).backgroundID;
                    final String firstName = cardsList.get(position).firstName;
                    final String lastName = cardsList.get(position).lastName;
                    final String phoneNumber = cardsList.get(position).phoneNumber;
                    final String emailAddress = cardsList.get(position).emailAddress;
                    final int cardID = cardsList.get(position).cardID;

                    lin.post(new Runnable() {
                        @Override
                        public void run()
                        {
                            lin.setBackgroundResource(back);
                        }
                    });
                    nameTV.post(new Runnable() {
                        @Override
                        public void run()
                        {
                            nameTV.setText(firstName + " " + lastName);
                        }
                    });
                    numberTV.post(new Runnable() {
                        @Override
                        public void run()
                        {
                            numberTV.setText(phoneNumber);
                        }
                    });
                    emailTV.post(new Runnable() {
                        @Override
                        public void run()
                        {
                            emailTV.setText(emailAddress);
                        }
                    });
                    shareTV.post(new Runnable() {
                        @Override
                        public void run()
                        {
                            shareTV.setText("Share: ID - " + cardID);
                        }
                    });
                    /*lin.setBackgroundResource(cardsList.get(i).backgroundID);
                    nameTV.setText(cardsList.get(i).firstName + " " + cardsList.get(i).lastName);
                    numberTV.setText(cardsList.get(i).phoneNumber);
                    emailTV.setText(cardsList.get(i).emailAddress);
                    shareTV.setText("Share: ID - " + cardsList.get(i).cardID);*/
                }
            }
        }).start();
    }

    public static int getPosition()
    {
        return cardPOS;
    }
}