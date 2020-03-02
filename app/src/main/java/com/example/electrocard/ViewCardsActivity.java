package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ViewCardsActivity extends AppCompatActivity
{
    private CardAdapter cardAdapter = null;
    private RecyclerView cardRecycler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);

        cardAdapter = new CardAdapter();
        cardRecycler = findViewById(R.id.cardsRV);
        cardRecycler.setAdapter(cardAdapter);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        cardRecycler.setLayoutManager(myManager);
    }
}
