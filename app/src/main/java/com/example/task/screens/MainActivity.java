package com.example.task.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.adapter.ProductAdapter;
import com.example.task.data.DatabaseHelper;
import com.example.task.model.ProductData;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView cartbtn;
    TextView cartcount;
    ProductAdapter ProductAdapter;
    public static DatabaseHelper databaseHelper;
    List<ProductData> ProductData = new ArrayList<ProductData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.res);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        cartbtn=(ImageView)findViewById(R.id.cart_btn);
        cartcount=(TextView)findViewById(R.id.cartcount);


        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BasketActivity.class));
            }
        });

        databaseHelper= Room.databaseBuilder(getApplicationContext(), DatabaseHelper.class,"Basket").allowMainThreadQueries().build();

        getdata();


    }

    private void updatacartcount() {
        if (cartcount==null)return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (databaseHelper.cartDao().countCart()==0)
                    cartcount.setVisibility(View.INVISIBLE);
                else {
                    cartcount.setVisibility(View.VISIBLE);
                    cartcount.setText(String.valueOf(databaseHelper.cartDao().countCart()));
                }
            }
        });

    }

    private void getdata() {

        ProductData.add(new ProductData(1,"sony","https://images-na.ssl-images-amazon.com/images/I/71p11135VSL._AC_SL1500_.jpg","800"));
        ProductData.add(new ProductData(2,"Mi","https://uniqueandrocode.000webhostapp.com/hiren/androidtutorial/mycart/images/1.jpg","700"));
        ProductData.add(new ProductData(3,"Nokia","https://images-na.ssl-images-amazon.com/images/I/613Wvm%2BVqKL._AC_SL1500_.jpg","200"));
        ProductData.add(new ProductData(4,"Apple","https://uniqueandrocode.000webhostapp.com/hiren/androidtutorial/mycart/images/1.jpg","500"));
        ProductData.add(new ProductData(5,"sony","https://images-na.ssl-images-amazon.com/images/I/71p11135VSL._AC_SL1500_.jpg","800"));
        ProductData.add(new ProductData(4,"Apple","https://uniqueandrocode.000webhostapp.com/hiren/androidtutorial/mycart/images/1.jpg","500"));
        ProductAdapter=new ProductAdapter(ProductData,MainActivity.this);
        recyclerView.setAdapter(ProductAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        updatacartcount();
    }
}

