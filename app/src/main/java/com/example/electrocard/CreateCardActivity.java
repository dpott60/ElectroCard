package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CreateCardActivity extends AppCompatActivity
{

    LinearLayout lin;
    int newBackground;
    Context myContext;

    TextView nameET;
    TextView numberET;
    TextView emailET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        myContext = this;

        nameET = findViewById(R.id.nameET);
        numberET = findViewById(R.id.numberET);
        emailET = findViewById(R.id.emailET);

        lin = findViewById(R.id.editcardLIN);

        final Spinner colorSPN = findViewById(R.id.colorSPN);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(CreateCardActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.colors));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSPN.setAdapter(spinnerAdapter);


        colorSPN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                Log.d("COLOR SELECTION", colorSPN.getSelectedItem().toString());
                switch (colorSPN.getSelectedItem().toString())
                {
                    case "Red":
                        newBackground = R.drawable.redback;
                        lin.setBackgroundResource(newBackground);
                        break;
                    case "Orange":
                        newBackground = R.drawable.orangeback;
                        lin.setBackgroundResource(newBackground);
                        break;
                    case "Yellow":
                        newBackground = R.drawable.yellowback;
                        lin.setBackgroundResource(newBackground);
                        break;
                    case "Green":
                        newBackground = R.drawable.greenback;
                        lin.setBackgroundResource(newBackground);
                        break;
                    case "Blue":
                        newBackground = R.drawable.blueback;
                        lin.setBackgroundResource(newBackground);
                        break;
                    case "Purple":
                        newBackground = R.drawable.purpleback;
                        lin.setBackgroundResource(newBackground);
                        break;
                    case "Colors":
                        newBackground = R.drawable.back;
                        lin.setBackgroundResource(newBackground);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        Button confirmCreateBTN = findViewById(R.id.confirmCreateBTN);
        confirmCreateBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                threadCreateCard();

                Intent ini = new Intent(myContext, ViewCardsActivity.class);
                startActivity(ini);
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Card> numCards = LoginActivity.getDB().electroDao().getUserCards(LoginActivity.getLoggedInUserID());
                        ViewCardsActivity.notifyCardAdded(numCards.size() + 1);
                    }
                }).start();*/
            }
        });
    }
    public void threadCreateCard()
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                int randID = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
                List<Card> checkID = LoginActivity.getDB().electroDao().findCardByID(randID);
                while (checkID.isEmpty() == false)
                {
                    randID = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
                    checkID = LoginActivity.getDB().electroDao().findCardByID(randID);
                }
                String[] nameSplit = nameET.getText().toString().split(" ");
                final Card newCard = new Card(randID, LoginActivity.getLoggedInUserID(), nameSplit[0], nameSplit[1], numberET.getText().toString(), emailET.getText().toString(), newBackground);
                LoginActivity.getDB().electroDao().insertCard(newCard);

                lin.post(new Runnable() {
                    @Override
                    public void run() {
                        CardModel.getSingleton().dbCards.add(newCard);
                        ViewCardsActivity.notifyCardAdded(CardModel.getSingleton().dbCards.size() - 1);
                    }
                });
            }
        }).start();
    }
}
