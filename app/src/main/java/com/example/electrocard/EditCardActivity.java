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

public class EditCardActivity extends AppCompatActivity
{
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "number";
    public static final String EMAIL_KEY = "email";
    public static final String BG_KEY = "background";
    public static final String ID_KEY = "cardid";
    private Context myContext;
    LinearLayout lin;
    int sentBackground;
    int newBackground;
    int cardID;

    TextView nameET;
    TextView numberET;
    TextView emailET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        myContext = this;

        nameET = findViewById(R.id.nameET);
        numberET = findViewById(R.id.numberET);
        emailET = findViewById(R.id.emailET);

        Intent ini = getIntent();
        lin = findViewById(R.id.editcardLIN);

        sentBackground = ini.getIntExtra(BG_KEY, R.drawable.back);
        lin.setBackgroundResource(sentBackground);
        cardID = ini.getIntExtra(ID_KEY, 0000);
        nameET.setText(ini.getStringExtra(NAME_KEY));
        numberET.setText(ini.getStringExtra(PHONE_KEY));
        emailET.setText(ini.getStringExtra(EMAIL_KEY));


        Button cancelEditBTN = findViewById(R.id.cancelEditBTN);
        cancelEditBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent ini = new Intent(myContext, ViewCardsActivity.class);
                startActivity(ini);
            }
        });

        final Spinner colorSPN = findViewById(R.id.colorSPN);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(EditCardActivity.this,
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
                        newBackground = sentBackground;
                        lin.setBackgroundResource(newBackground);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        Button saveBTN = findViewById(R.id.saveBTN);
        saveBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                threadUpdateCard();
                Intent ini = new Intent(myContext, ViewCardsActivity.class);
                startActivity(ini);
            }
        });
    }
    public void threadUpdateCard()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Card> cardToUpdate = LoginActivity.getDB().electroDao().findCardByID(cardID);

                for (Card update : cardToUpdate)
                {
                    String[] nameSplit = nameET.getText().toString().split(" ");
                    Log.d("NAME LOGGING", "First Name:" + nameSplit[0]);
                    update.firstName = nameSplit[0];
                    Log.d("NAME LOGGING", "Last Name:" + nameSplit[1]);
                    update.lastName = nameSplit[1];
                    update.phoneNumber = numberET.getText().toString();
                    update.emailAddress = emailET.getText().toString();
                    update.backgroundID = newBackground;

                    LoginActivity.getDB().electroDao().updateCard(update);
                }
            }
        }).start();
    }
}
