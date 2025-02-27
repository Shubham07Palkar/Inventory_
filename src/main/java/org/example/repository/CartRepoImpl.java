package org.example.repository;

import org.example.entities.CartItem;
import org.example.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepoImpl implements CartRepo{
    List<CartItem> cart = new ArrayList<>();

    @Override
    public void addItem(Product product, int quantity) {
        cart.add(new CartItem(product, quantity));
    }

    @Override
    public void removeItem(int productId) {
        cart.removeIf(item -> item.getProduct().getId()==productId);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cart;
    }
}
