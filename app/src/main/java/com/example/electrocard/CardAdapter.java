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
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>
{

    public static int cardPos;
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

        cardPos = position;
        threadLoadCards(lin, nameTV, numberTV, emailTV, shareTV);
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

    public static void threadLoadCards(final LinearLayout lin, final TextView nameTV, final TextView numberTV, final TextView emailTV, final TextView shareTV)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                final List<Card> cardsList = LoginActivity.getDB().electroDao().getUserCards(LoginActivity.getLoggedInUserID());
                for (Card card : cardsList)
                {
                    final int back = cardsList.get(cardPos).backgroundID;
                    final String firstName = cardsList.get(cardPos).firstName;
                    final String lastName = cardsList.get(cardPos).lastName;
                    final String phoneNumber = cardsList.get(cardPos).phoneNumber;
                    final String emailAddress = cardsList.get(cardPos).emailAddress;
                    final int cardID = cardsList.get(cardPos).cardID;

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
                    ini.putExtra(EditCardActivity.BG_KEY, cardsList.get(cardPos).backgroundID);
                    ini.putExtra(EditCardActivity.NAME_KEY, cardsList.get(cardPos).firstName + " " + cardsList.get(cardPos).lastName);
                    ini.putExtra(EditCardActivity.PHONE_KEY, cardsList.get(cardPos).phoneNumber);
                    ini.putExtra(EditCardActivity.EMAIL_KEY, cardsList.get(cardPos).emailAddress);
                    ini.putExtra(EditCardActivity.ID_KEY, cardsList.get(cardPos).cardID);

                    Button editBTN = lin.findViewById(R.id.editBTN);
                    editBTN.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            ViewCardsActivity.myContext.startActivity(ini);
                        }
                    });

                    Button deleteBTN = lin.findViewById(R.id.deleteCardBTN);
                    deleteBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            threadDeleteCard(lin, cardsList.get(cardPos).cardID);
                        }
                    });
                }
            }
        }).start();
    }

    public static void threadDeleteCard(final LinearLayout lin, final int id)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                List<Card> cardToDelete = LoginActivity.getDB().electroDao().findCardByID(id);
                final List<Card> userCards = LoginActivity.getDB().electroDao().getUserCards(LoginActivity.loggedInUserID);
                for (Card card : cardToDelete)
                {
                    LoginActivity.getDB().electroDao().deleteCard(card);
                    lin.post(new Runnable() {
                        @Override
                        public void run() {
                            ViewCardsActivity.notifyCardRemoved(cardPos, userCards);
                        }
                    });
                }
            }
        }).start();
    }

    public static void decrementPosition()
    {
        cardPos--;
    }
}