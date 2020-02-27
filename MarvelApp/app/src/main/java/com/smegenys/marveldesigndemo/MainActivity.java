package com.smegenys.marveldesigndemo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private ListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListData[] myListData = new MyListData[] {
                new MyListData("Iron\nman","Toney Stark", R.drawable.ironman,R.color.yellow),
                new MyListData("Spider\nman","peter parker",R.drawable.spiderman,R.color.red)};

        // hide title bar
        getSupportActionBar().hide();
        adapter = new ListAdapter(myListData);
        recyclerView = findViewById(R.id.recycler_view);

       /*
       * this is callback listener,
       * fire when card swipe left, right,up, down .
       * so here , we are refresh recycler view and
       * remove top card that display on 0 position.
       *
       * */
        SwipeableTouchHelperCallback swipeableTouchHelperCallback =
                new SwipeableTouchHelperCallback(new OnItemSwiped() {
                    @Override public void onItemSwiped() {
                        adapter.removeTopItem();
                        adapter.notifyDataSetChanged();

                    }

                    @Override public void onItemSwipedLeft() {
                        Log.e("SWIPE", "LEFT");
                    }

                    @Override public void onItemSwipedRight() {
                        Log.e("SWIPE", "RIGHT");
                    }

                    @Override public void onItemSwipedUp() {
                        Log.e("SWIPE", "UP");
                    }

                    @Override public void onItemSwipedDown() {
                        Log.e("SWIPE", "DOWN");
                    }
                }) {
                    @Override
                    public int getAllowedSwipeDirectionsMovementFlags(RecyclerView.ViewHolder viewHolder) {
                        return ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                    }
                };

        // set itemtouch listner to recyclerView item
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeableTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new SwipeableLayoutManager().setAngle(20)
                .setAnimationDuratuion(450)
                .setMaxShowCount(5)
                .setScaleGap(0.1f)
                .setTransYGap(0));
        recyclerView.setAdapter(adapter = new ListAdapter(myListData));
    }
}
