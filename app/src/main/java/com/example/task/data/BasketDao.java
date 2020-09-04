package com.example.task.data;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BasketDao {

    @Insert
    public void addToCart(Basket basket);

    @Query("SELECT * FROM Basket")
    public List<Basket>getData();

    @Query("SELECT EXISTS (SELECT 1 FROM Basket WHERE id=:id)")
    public int isAddToCart(int id);

    @Query("select COUNT (*) from Basket")
    int countCart();

    @Query("DELETE FROM Basket WHERE id=:id ")
    int deleteItem(int id);


}
