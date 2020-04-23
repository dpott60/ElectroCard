package com.example.electrocard;

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
    LinearLayout lin;
    TextView nameTV;
    TextView numberTV;
    TextView emailTV;
    TextView shareTV;

    static CardViewHolder vh;

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
    public void onBindViewHolder(@NonNull CardViewHolder holder, final int position)
    {
        lin = holder.convenient;
        nameTV = lin.findViewById(R.id.savedNameTV);
        numberTV = lin.findViewById(R.id.savedNumberTV);
        emailTV = lin.findViewById(R.id.savedEmailTV);
        shareTV = lin.findViewById(R.id.savedShareTV);


        threadLoadCards();

        lin.setBackgroundResource(model.dbCards.get(position).backgroundID);
        nameTV.setText(model.dbCards.get(position).firstName + " " + model.dbCards.get(position).lastName);
        numberTV.setText(model.dbCards.get(position).phoneNumber);
        emailTV.setText(model.dbCards.get(position).emailAddress);
        shareTV.setText("Share - ID: " + model.dbCards.get(position).cardID);

            /*final Intent ini = new Intent(ViewCardsActivity.myContext, EditCardActivity.class);
            ini.putExtra(EditCardActivity.BG_KEY, card.backgroundID);
            ini.putExtra(EditCardActivity.NAME_KEY, card.firstName + " " + card.lastName);
            ini.putExtra(EditCardActivity.PHONE_KEY, card.phoneNumber);
            ini.putExtra(EditCardActivity.EMAIL_KEY, card.emailAddress);
            ini.putExtra(EditCardActivity.ID_KEY, card.cardID);*/

           /* Button editBTN = vh.convenient.findViewById(R.id.editBTN);
            editBTN.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    ViewCardsActivity.myContext.startActivity(ini);
                }
            });*/

            Button deleteBTN = vh.convenient.findViewById(R.id.deleteCardBTN);
            deleteBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewHolder viewH = ViewCardsActivity.cardRecycler.findViewHolderForAdapterPosition(position);
                    threadDeleteCard(model.dbCards.get(position).cardID);
                }
            });
        //}
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LinearLayout linear = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        vh = new CardViewHolder(linear);
        return vh;
    }

    @Override
    public int getItemCount()
    {
        return model.dbCards.size();
    }

    public static void threadLoadCards()
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                final List<Card> cardsList = LoginActivity.getDB().electroDao().getUserCards(LoginActivity.getLoggedInUserID());
                Log.d("CARDLIST", " " + cardsList.size());
            }
        }).start();
    }

    public static void threadDeleteCard(final int id)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                List<Card> cardToDelete = LoginActivity.getDB().electroDao().findCardByID(id);
                for (Card card : cardToDelete)
                {
                    LoginActivity.getDB().electroDao().deleteCard(card);
                    CardModel.getSingleton().dbCards.remove(vh.getAdapterPosition());
                    ViewCardsActivity.cardRecycler.post(new Runnable() {
                        @Override
                        public void run() {
                         ViewCardsActivity.notifyCardRemoved(vh.getAdapterPosition());
                        }
                    });
                }
            }
        }).start();
    }

}