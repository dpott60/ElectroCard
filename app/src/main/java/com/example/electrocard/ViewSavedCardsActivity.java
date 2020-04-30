package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class ViewSavedCardsActivity extends AppCompatActivity
{
    public static SavedCardAdapter savedCardAdapter = null;
    public static RecyclerView savedCardRecycler = null;
    public static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_cards);

        myContext = this;
        savedCardAdapter = new SavedCardAdapter();
        savedCardRecycler = findViewById(R.id.userSavedCardsRV);
        savedCardRecycler.setAdapter(savedCardAdapter);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        savedCardRecycler.setLayoutManager(myManager);

        Button backBTN = findViewById(R.id.savedBackBTN);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ini = new Intent(myContext, MainActivity.class);
                startActivity(ini);
            }
        });

        Button addCardBTN = findViewById(R.id.addCardBTN);
        addCardBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent ini = new Intent(myContext, AddCardActivity.class);
                startActivity(ini);
            }
        });
    }

    public static void notifyCardRemoved(int pos) { savedCardAdapter.notifyItemRemoved(pos); }
    public static void notifyCardAdded(int pos) { savedCardAdapter.notifyItemInserted(pos); }
}
