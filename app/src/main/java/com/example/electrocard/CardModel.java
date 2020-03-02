package com.example.electrocard;

import java.util.ArrayList;

public class CardModel
{
    public static class CardInfo
    {
        public String name;
        public String number;
        public String email;

        public CardInfo(String name, String number, String email)
        {
            this.name = name;
            this.number = number;
            this.email = email;
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
        cardList.add(new CardInfo("John Doe", "123-456-7890", "johndoe@email.com"));
    }
}
