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

public class SavedCardAdapter extends RecyclerView.Adapter<SavedCardAdapter.SavedCardViewHolder> {

    LinearLayout lin;
    TextView nameTV;
    TextView numberTV;
    TextView emailTV;
    TextView shareTV;

    static SavedCardViewHolder vh;

    public static class SavedCardViewHolder extends ViewHolder {

        public LinearLayout convenient;

        public SavedCardViewHolder(LinearLayout v){
            super(v);
            convenient = v;
        }
    }

    private SavedCardModel model;

    public SavedCardAdapter(){
        super();
        this.model = SavedCardModel.getSingleton();
    }

    @Override
    public void onBindViewHolder(@NonNull SavedCardViewHolder holder, final int position) {

        lin = holder.convenient;
        nameTV = lin.findViewById(R.id.savedNameTV);
        numberTV = lin.findViewById(R.id.savedNumberTV);
        emailTV = lin.findViewById(R.id.savedEmailTV);
        shareTV = lin.findViewById(R.id.savedShareTV);

        threadLoadSavedCards();

        lin.setBackgroundResource(model.dbSavedCards.get(position).backgroundID);
        nameTV.setText(model.dbSavedCards.get(position).firstName + " " + model.dbSavedCards.get(position).lastName);
        numberTV.setText(model.dbSavedCards.get(position).phoneNumber);
        emailTV.setText(model.dbSavedCards.get(position).emailAddress);
        shareTV.setText("Share - ID: " + model.dbSavedCards.get(position).cardID);

        Button deleteBTN = vh.convenient.findViewById(R.id.deleteSavedCardBTN);
        deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder viewHolder = ViewSavedCardsActivity.savedCardRecycler.findViewHolderForAdapterPosition(position);
                threadDeleteCard(model.dbSavedCards.get(position).cardID);
            }
        });
    }

    @NonNull
    @Override
    public SavedCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout linear = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_card_view, parent, false);
        vh = new SavedCardViewHolder(linear);
        return vh;
    }

    @Override
    public int getItemCount() {
        return model.dbSavedCards.size();
    }

    public static void threadLoadSavedCards(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Card> savedCardsList = LoginActivity.getDB().electroDao().getSavedCards(LoginActivity.getLoggedInUserID());
                Log.d("Cardlist", " " + savedCardsList.size());
            }
        }).start();
    }

    public static void threadDeleteCard(final int id)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                List<SavedCards> savedCardToDelete = LoginActivity.getDB().electroDao().findSavedCardByID(LoginActivity.getLoggedInUserID(), id);
                for (SavedCards saved : savedCardToDelete){
                    LoginActivity.getDB().electroDao().deleteSavedCard(saved);
                    SavedCardModel.getSingleton().dbSavedCards.remove(vh.getAdapterPosition());
                    ViewSavedCardsActivity.savedCardRecycler.post(new Runnable() {
                        @Override
                        public void run() {
                            ViewSavedCardsActivity.notifyCardRemoved(vh.getAdapterPosition());
                        }
                    });
                }
            }
        }).start();
    }
}
