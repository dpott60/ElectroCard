package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    private CardAdapter cardAdapter = null;
    private RecyclerView cardRecycler = null;
    //private GestureDetectorCompat detector = null;

    /*// We need a gesture listener
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            View view = cardRecycler.findChildViewUnder(e.getX(), e.getY());
            if (view != null)
            {
                RecyclerView.ViewHolder holder = cardRecycler.getChildViewHolder(view);
                if (holder instanceof CardAdapter.CardViewHolder)
                {
                    int position = holder.getAdapterPosition();
                    // handle single tap
                    Log.d("click", "clicked on item "+ position);
                    CardModel myModel = CardModel.getSingleton();
                    myModel.cardList.remove(position);
                    cardAdapter.notifyItemRemoved(position);
                    return true; // Use up the tap gesture
                }
            }
            // we didn't handle the gesture so pass it on
            return false;
        }
    }*/
    //testing to see if github works also
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardAdapter = new CardAdapter();
        cardRecycler = findViewById(R.id.cardsRV);
        cardRecycler.setAdapter(cardAdapter);
        LinearLayoutManager myManager = new LinearLayoutManager(this);
        cardRecycler.setLayoutManager(myManager);

        /*// Make a Listener for taps
        detector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());
        // add the listener to the recycler
        cardRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
            {
                return detector.onTouchEvent(e);
            }
        });*/
    }
}
