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

        threadLoadCards(lin, nameTV, numberTV, emailTV, shareTV, position);

        /*


        final Intent ini = new Intent(ViewCardsActivity.myContext, EditCardActivity.class);
        ini.putExtra(EditCardActivity.BG_KEY, model.cardList.get(position).backgroundID);
        ini.putExtra(EditCardActivity.NAME_KEY, model.cardList.get(position).firstName + " " + model.cardList.get(position).lastName);
        ini.putExtra(EditCardActivity.PHONE_KEY, model.cardList.get(position).phoneNumber);
        ini.putExtra(EditCardActivity.EMAIL_KEY, model.cardList.get(position).emailAddress);*/
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
                List<Card> cardsList = LoginActivity.getDB().electroDao().getAllCards();
                for (Card card : cardsList)
                {
                    final int back = cardsList.get(pos).backgroundID;
                    final String firstName = cardsList.get(pos).firstName;
                    final String lastName = cardsList.get(pos).lastName;
                    final String phoneNumber = cardsList.get(pos).phoneNumber;
                    final String emailAddress = cardsList.get(pos).emailAddress;
                    final int cardID = cardsList.get(pos).cardID;

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

                    final Intent ini = new Intent(ViewCardsActivity.myContext, EditCardActivity.class);
                    ini.putExtra(EditCardActivity.BG_KEY, cardsList.get(pos).backgroundID);
                    ini.putExtra(EditCardActivity.NAME_KEY, cardsList.get(pos).firstName + " " + cardsList.get(pos).lastName);
                    ini.putExtra(EditCardActivity.PHONE_KEY, cardsList.get(pos).phoneNumber);
                    ini.putExtra(EditCardActivity.EMAIL_KEY, cardsList.get(pos).emailAddress);

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
            }
        }).start();
    }
}