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

import org.w3c.dom.Text;

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

        nameTV = findViewById(R.id.nameTV);
        numberTV = findViewById(R.id.numberTV);
        emailTV = findViewById(R.id.emailTV);
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
