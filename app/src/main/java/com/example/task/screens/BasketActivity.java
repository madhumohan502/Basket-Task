package com.example.task.screens;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.task.R;
import com.example.task.adapter.CartProductAdapter;

import com.example.task.adapter.Redirector;
import com.example.task.data.Basket;
import com.example.task.data.DatabaseHelper;


import java.util.List;

public class BasketActivity extends AppCompatActivity implements Redirector {
    RecyclerView rv;
    public static DatabaseHelper databaseHelper;
    List<Basket> cartProductData ;
    CartProductAdapter cartProductAdapter;
    TextView counterText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        rv=(RecyclerView)findViewById(R.id.res);
        counterText = (TextView)findViewById(R.id.id_counter_text) ;
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this,1));


        getdata();
    }

    private void getdata() {
        databaseHelper= Room.databaseBuilder(getApplicationContext(), DatabaseHelper.class,"Basket").allowMainThreadQueries().build();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int counter = databaseHelper.cartDao().countCart();
                counterText.setText(String.valueOf(counter));
                cartProductData = databaseHelper.cartDao().getData();
                Log.d("madhu",cartProductData.get(0).name);
                cartProductAdapter=new CartProductAdapter(cartProductData, BasketActivity.this);
                rv.setAdapter(cartProductAdapter);

            }
        });
    }

    @Override
    public void counter(String count) {
        Log.d("madhu",count);
        counterText.setText(count);
    }
}
