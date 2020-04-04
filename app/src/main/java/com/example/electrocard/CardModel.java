package com.example.electrocard;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.ArrayList;

public class CardModel
{
    public static class CardInfo
    {
        public String name;
        public String number;
        public String email;
        public int id;
        public int background;

        public CardInfo(String name, String number, String email, int id, int background)
        {
            this.name = name;
            this.number = number;
            this.email = email;
            this.id = id;
            this.background = background;
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
        cardList.add(new CardInfo("John Doe", "123-456-7890", "johndoe@email.com", 2345, R.drawable.redback));
        cardList.add(new CardInfo("Jimmy T", "098-765-4321", "JIMMY@email.com", 3456, R.drawable.greenback));
        cardList.add(new CardInfo("Jenny", "867-5309", "jenny@email.com", 1234, R.drawable.blueback));
    }
}
