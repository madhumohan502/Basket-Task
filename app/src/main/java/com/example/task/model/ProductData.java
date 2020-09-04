package com.example.task.model;

import com.google.gson.annotations.SerializedName;

public class ProductData {
    @SerializedName("id")
    public int id;

    @SerializedName("prname")
    public String prname;

    @SerializedName("primage")
    public String primage;

    @SerializedName("prprice")
    public String prprice;

    public ProductData(int id, String prname, String primage, String prprice) {
        this.id = id;
        this.prname = prname;
        this.primage = primage;
        this.prprice = prprice;
    }

    public int getId() {
        return id;
    }

    public String getPrname() {
        return prname;
    }

    public String getPrimage() {
        return primage;
    }

    public String getPrprice() {
        return prprice;
    }
}
