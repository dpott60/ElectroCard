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

import java.util.List;

public class ViewCardsActivity extends AppCompatActivity
{
    public static CardAdapter cardAdapter = null;
    public static RecyclerView cardRecycler = null;
    public static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);

        myContext = this;
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

        Button createCardBTN = findViewById(R.id.addCardBTN);
        createCardBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ini = new Intent(myContext, CreateCardActivity.class);
                startActivity(ini);
            }
        });
    }
    public static void notifyCardRemoved(int pos)
    {
        cardAdapter.notifyItemRemoved(pos);
    }
    public static void notifyCardAdded(int pos)
    {
        cardAdapter.notifyItemInserted(pos);
    }
}
