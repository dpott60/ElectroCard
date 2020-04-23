package com.example.electrocard;

import java.util.List;

public class SavedCardModel {
    ElectroDatabase db = LoginActivity.getDB();

    public List<Card> dbSavedCards;

    private SavedCardModel() {
        //threadPlaceHolderSavedCards();
        threadGetSavedCards();
        threadPlaceHolderSavedCards();
    }

    private static SavedCardModel theModel = null;

    public static SavedCardModel getSingleton(){
        if(theModel == null){
            theModel = new SavedCardModel();
        }
        return theModel;
    }

    private void threadPlaceHolderSavedCards(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (db.electroDao().getSavedCards(LoginActivity.getLoggedInUserID()).isEmpty()){
                    db.electroDao().insertCard(new Card(2345, 0001, "John", "Doe", "123-456-7890", "johndoe@email.com", R.drawable.redback));
                    db.electroDao().saveCard(new SavedCards(LoginActivity.getLoggedInUserID(), 2345));
                    db.electroDao().insertCard(new Card(3456, 0001, "Jimmy", "T", "098-765-4321", "JIMMY@email.com", R.drawable.greenback));
                    db.electroDao().saveCard(new SavedCards(LoginActivity.getLoggedInUserID(), 3456));
                    db.electroDao().insertCard(new Card(1234, 0001, "Jenny", "", "867-5309", "jenny@email.com", R.drawable.blueback));
                    db.electroDao().saveCard(new SavedCards(LoginActivity.getLoggedInUserID(), 1234));
                }
            }
        }).start();
    }

    public void threadGetSavedCards(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbSavedCards = db.electroDao().getSavedCards(LoginActivity.getLoggedInUserID());
            }
        }).start();
    }
}
