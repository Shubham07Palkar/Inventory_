package org.example.repository;

import org.example.entities.CartItem;
import org.example.entities.Product;

import java.util.List;

public interface CartRepo {
    void addItem(Product product, int quantity);

    void removeItem(int productId);

    List<CartItem> getAllCartItems();
}
