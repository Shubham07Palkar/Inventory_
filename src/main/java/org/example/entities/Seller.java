package org.example.entities;

public class Seller extends User {
    public Seller() {
    }

    public Seller(int id, String name, String email, UserRole role) {
        super(id, name, email, role);
    }
}
