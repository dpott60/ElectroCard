package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AddCardActivity extends AppCompatActivity
{
    private Context myContext;
    TextView nameTV;
    TextView numberTV;
    TextView emailTV;
    TextView foundcardTV;
    LinearLayout lin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        myContext = this;

        nameTV = findViewById(R.id.savedNameTV);
        numberTV = findViewById(R.id.savedNumberTV);
        emailTV = findViewById(R.id.savedEmailTV);
        foundcardTV = findViewById(R.id.foundcardTV);
        lin = findViewById(R.id.cardviewLIN);

        Button findCardBTN = findViewById(R.id.findCardBTN);
        findCardBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                threadFindCard();
            }
        });

        Button cancelBTN = findViewById(R.id.cancelActivityBTN);
        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ini = new Intent(myContext, ViewSavedCardsActivity.class);
                startActivity(ini);
            }
        });

        Button addBTN = findViewById(R.id.addCardBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCard();
                Intent ini = new Intent(myContext, ViewSavedCardsActivity.class);
                startActivity(ini);
            }
        });
    }

    public void saveCard()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Card card = null;
                EditText enteredidET = findViewById(R.id.enteredidET);
                List<Card> foundCard = LoginActivity.getDB().electroDao().findCardByID(Integer.parseInt(enteredidET.getText().toString()));
                while(foundCard.isEmpty() == false) {
                    int x = 0;
                    card = foundCard.get(0);
                    x++;
                }
                final Card cardToSave = card;
                final SavedCards savedCard = new SavedCards(LoginActivity.getLoggedInUserID(), cardToSave.cardID);
                LoginActivity.getDB().electroDao().saveCard(savedCard);

                lin.post(new Runnable() {
                    @Override
                    public void run() {
                        SavedCardModel.getSingleton().dbSavedCards.add(cardToSave);
                        ViewSavedCardsActivity.notifyCardAdded(SavedCardModel.getSingleton().dbSavedCards.size() - 1);
                    }
                });
            }
        }).start();
    }

    public void threadFindCard()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EditText enteredidET = findViewById(R.id.enteredidET);
                List<Card> foundCard = LoginActivity.getDB().electroDao().findCardByID(Integer.parseInt(enteredidET.getText().toString()));
                if (foundCard.isEmpty())
                {
                    nameTV.setText("");
                    numberTV.setText("");
                    emailTV.setText("");
                    lin.setBackgroundResource(R.drawable.back);
                    foundcardTV.post(new Runnable() {
                        @Override
                        public void run() {
                            foundcardTV.setText("Card not found!");
                        }
                    });
                }
                else
                {
                    for (Card found : foundCard)
                    {
                        nameTV.setText(found.firstName + " " + found.lastName);
                        numberTV.setText(found.phoneNumber);
                        emailTV.setText(found.emailAddress);
                        lin.setBackgroundResource(found.backgroundID);
                        foundcardTV.post(new Runnable() {
                            @Override
                            public void run() {
                                foundcardTV.setText("");
                            }
                        });
                    }
                }
            }
        }).start();
    }
}
