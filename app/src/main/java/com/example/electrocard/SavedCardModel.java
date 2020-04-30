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
                    db.electroDao().saveCard(new SavedCards(LoginActivity.getLoggedInUserID(), 2345));
                    db.electroDao().saveCard(new SavedCards(LoginActivity.getLoggedInUserID(), 3456));
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
