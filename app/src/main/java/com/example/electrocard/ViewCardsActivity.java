package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewCardsActivity extends AppCompatActivity
{
    private CardAdapter cardAdapter = null;
    private RecyclerView cardRecycler = null;
    public static Context myContext;

    /*private CardModel model;

    public static final int EDIT_REQUEST = 1;
    public static final int GOOD_EDIT_RESULT = 0;
    public static final int BAD_EDIT_RESULT = -1;

    private TextView nameTV;
    private TextView numberTV;
    private TextView emailTV;*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);

        myContext = this;
        //this.model = CardModel.getSingleton();
        cardAdapter = new CardAdapter();
        cardRecycler = findViewById(R.id.cardsRV);
        cardRecycler.setAdapter(cardAdapter);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        cardRecycler.setLayoutManager(myManager);

        Button backBTN = findViewById(R.id.backBTN);
        backBTN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent ini = new Intent(myContext, MainActivity.class);
               startActivity(ini);
           }
       });
    }
}
