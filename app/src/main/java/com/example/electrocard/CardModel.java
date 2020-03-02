package com.example.electrocard;

import java.util.ArrayList;

public class CardModel
{
    public static class CardInfo
    {
        public String name;
        public String number;
        public String email;
        public int id;

        public CardInfo(String name, String number, String email, int id)
        {
            this.name = name;
            this.number = number;
            this.email = email;
            this.id = id;
        }
    }

    public ArrayList<CardInfo> cardList;

    private CardModel()
    {
        cardList = new ArrayList<CardInfo>();
        loadCards();
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

    private void loadCards()
    {
        cardList.add(new CardInfo("John Doe", "123-456-7890", "johndoe@email.com", 2345));
        cardList.add(new CardInfo("Jimmy T", "098-765-4321", "JIMMY@email.com", 3456));
        cardList.add(new CardInfo("SMITE Sunday", "867-5309", "jenny@email.com", 1234));
    }
}
