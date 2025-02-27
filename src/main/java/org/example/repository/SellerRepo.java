package org.example.repository;

import org.example.entities.Seller;

import java.util.List;

public interface SellerRepo {
    void addSeller(Seller seller);

    List<Seller> getAllSeller();

    Seller findById(int id);


}
