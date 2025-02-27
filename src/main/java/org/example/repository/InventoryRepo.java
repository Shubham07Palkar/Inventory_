package org.example.repository;

import org.example.entities.Product;

import java.util.List;

public interface InventoryRepo {
    //create
    void addProduct(Product product);

    //read
    List<Product> getAllProducts();
    Product findById(int id);

    //update
    void updateProduct(Product product);

    //delete
    void deleteProduct(int id);
}
