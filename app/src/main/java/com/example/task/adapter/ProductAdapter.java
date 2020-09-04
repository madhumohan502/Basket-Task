package com.example.task.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task.screens.AddTocartActivity;
import com.example.task.R;
import com.example.task.model.ProductData;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProductData>myProductData;
    private Context context;

    public ProductAdapter(List<ProductData> myProductData, Context context) {
        this.myProductData = myProductData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context).load(myProductData.get(position).primage).into(holder.primage);
        holder.prprice.setText("₹."+myProductData.get(position).prprice);
        holder.prname.setText(myProductData.get(position).prname);
        holder.primage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AddTocartActivity.class);
                String imageurl=myProductData.get(position).primage;
                String prprice=myProductData.get(position).prprice;

                intent.putExtra("imageurl",imageurl);
                intent.putExtra("prname",myProductData.get(position).prname);
                intent.putExtra("prprice",prprice);
                intent.putExtra("id",myProductData.get(position).id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myProductData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView primage;
        private TextView prprice;
        private TextView prname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            primage=(ImageView)itemView.findViewById(R.id.primage);
            prprice=(TextView)itemView.findViewById(R.id.txtprprice);
            prname=(TextView)itemView.findViewById(R.id.txtName);
        }
    }
}
