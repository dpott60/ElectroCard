package com.example.electrocard;

import java.util.ArrayList;
import java.util.List;

public class CardModel
{
    ElectroDatabase db = LoginActivity.getDB();

    /*public static class CardInfo
    {
        public String fName;
        public String lName;
        public String number;
        public String email;
        public int id;
        public int background;

        public CardInfo(String fName, String lName, String number, String email, int id, int background)
        {
            this.fName = fName;
            this.lName = lName;
            this.number = number;
            this.email = email;
            this.id = id;
            this.background = background;
        }
    }*/

    public List<Card> dbCards;
    public ArrayList<Card> cardList;

    private CardModel()
    {
        threadLoadCards();
        threadGetAllCards();
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

    private void threadLoadCards()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (db.electroDao().getAllCards() == null)
                {
                    db.electroDao().insertCard(new Card(2345, 0001, "John", "Doe", "123-456-7890", "johndoe@email.com", R.drawable.redback));
                    db.electroDao().insertCard(new Card(3456, 0001, "Jimmy", "T", "098-765-4321", "JIMMY@email.com", R.drawable.greenback));
                    db.electroDao().insertCard(new Card(1234, 0001, "Jenny", "", "867-5309", "jenny@email.com", R.drawable.blueback));
                }
            }
        }).start();
        //cardList.add(new CardInfo("Jenny", "", "867-5309", "jenny@email.com", 1234, R.drawable.blueback));
    }

    public void threadGetAllCards()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbCards = db.electroDao().getAllCards();
            }
        }).start();
    }
}
