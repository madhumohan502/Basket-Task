package com.example.task.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task.screens.BasketActivity;
import com.example.task.screens.MainActivity;
import com.example.task.R;
import com.example.task.data.Basket;

import java.util.List;


public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {
    private List<Basket> carts;
    private Context context;

    public CartProductAdapter(List<Basket> carts, Context context) {
        this.carts = carts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Basket cart=carts.get(position);

        Glide.with(context).load(carts.get(position).getImageid()).into(holder.primage);
        holder.prprice.setText("₹."+carts.get(position).getPrice());
        holder.prname.setText(carts.get(position).getName());

        holder.deletbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carts.remove(position);
                notifyDataSetChanged();
                MainActivity.databaseHelper.cartDao().deleteItem(cart.getId());
                int cartcount= MainActivity.databaseHelper.cartDao().countCart();
                ((BasketActivity)context).counter(String.valueOf(cartcount));
                Intent intent=new Intent("mymsg");
                intent.putExtra("cartcount",cartcount);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView primage,deletbtn;
        private TextView prprice;
        private TextView prname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            primage=(ImageView)itemView.findViewById(R.id.primage);
            deletbtn=(ImageView)itemView.findViewById(R.id.deletbtn);
            prprice=(TextView)itemView.findViewById(R.id.txtprprice);
            prname=(TextView)itemView.findViewById(R.id.txtprnamee);
        }
    }
}
