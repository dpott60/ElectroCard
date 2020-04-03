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

        /*nameTV = findViewById(R.id.nameTV);
        numberTV = findViewById(R.id.numberTV);
        emailTV = findViewById(R.id.emailTV);

        Button editBTN = findViewById(R.id.editBTN);

        editBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent ini = new Intent (myContext, EditCardActivity.class);

                ini.putExtra(EditCardActivity.NAME_KEY, nameTV.getText());
                ini.putExtra(EditCardActivity.PHONE_KEY, numberTV.getText());
                ini.putExtra(EditCardActivity.EMAIL_KEY, emailTV.getText());

                startActivityForResult(ini, EDIT_REQUEST);
            }
        });*/
    }

    /*public void onActivityResult(int reqCode, int resCode, Intent ini)
    {
        if (reqCode == EDIT_REQUEST){
            if (resCode == GOOD_EDIT_RESULT)
            {
                // GOOD THINGS
            }
            else if (resCode == BAD_EDIT_RESULT)
            {
                // BAD THINGS HAVE HAPPENED, PANIC, DO NOTHING
            }
            else
            {
                Log.d("intent", "unknown result code " + resCode);
            }
        }
    }*/
    public Context getMyContext()
    {
        return myContext;
    }
}
