package com.example.electrocard;

import java.util.List;

public class CardModel
{
    ElectroDatabase db = LoginActivity.getDB();

    public List<Card> dbCards;

    private CardModel()
    {
        //threadPlaceholderCards();
        threadGetUserCards();
        threadPlaceholderCards();
    }

    private static CardModel theModel = null;

    public static CardModel getSingleton()
    {
        if (theModel == null)
        {
            theModel = new CardModel();
        }
        return theModel;
    }

    private void threadPlaceholderCards()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (db.electroDao().getAllCards().isEmpty())
                {
                    db.electroDao().insertCard(new Card(2345, 0001, "John", "Doe", "123-456-7890", "johndoe@email.com", R.drawable.redback));
                    db.electroDao().insertCard(new Card(3456, 0001, "Jimmy", "T", "098-765-4321", "JIMMY@email.com", R.drawable.greenback));
                    db.electroDao().insertCard(new Card(1234, 0001, "Jenny", "", "867-5309", "jenny@email.com", R.drawable.blueback));
                }
            }
        }).start();
    }

    public void threadGetUserCards()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbCards = db.electroDao().getUserCards(LoginActivity.getLoggedInUserID());
            }
        }).start();
    }
}
