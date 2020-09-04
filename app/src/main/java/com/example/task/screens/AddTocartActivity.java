package com.example.task.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.task.R;
import com.example.task.data.Basket;


public class AddTocartActivity extends AppCompatActivity {
    private ImageView primage;
    private TextView price;
    private TextView name;
    Button addtocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tocart);
        primage=(ImageView)findViewById(R.id.primage);
        price=(TextView)findViewById(R.id.txtprprice);
        name=(TextView)findViewById(R.id.txtprnmae);

        Intent intent=getIntent();
        final String imageurl=intent.getStringExtra("imageurl");
        final String prname=intent.getStringExtra("prname");
        final String prprice=intent.getStringExtra("prprice");

        final int id=intent.getIntExtra("id",0);
        Glide.with(this).load(imageurl).into(primage);
        price.setText("₹."+prprice);
        name.setText(prname);

        addtocart=(Button)findViewById(R.id.addtocartbtn);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Basket cart=new Basket();
                cart.setId(id);
                cart.setImageid(imageurl);
                cart.setName(prname);
                cart.setPrice(prprice);
                if (MainActivity.databaseHelper.cartDao().isAddToCart(id)!=1){
                    MainActivity.databaseHelper.cartDao().addToCart(cart);
                    Toast.makeText(AddTocartActivity.this, "Item added to cart!", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(AddTocartActivity.this, "You are Already added to cart!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
