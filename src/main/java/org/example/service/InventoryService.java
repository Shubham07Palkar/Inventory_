package org.example.service;

import org.example.csvHandler.CsvHandler;
import org.example.entities.Product;
import org.example.exception.ProductNotFoundException;
import org.example.jsonFile.JsonFilehandler;
import org.example.repository.InventoryRepo;
import org.example.repository.InventoryRepoImpl;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class InventoryService {

    InventoryRepoImpl inventoryRepo = new InventoryRepoImpl();
    JsonFilehandler jsonfileHandler = new JsonFilehandler();
    CsvHandler csvHandler = new CsvHandler();

    public InventoryService() throws FileNotFoundException {
    }

//    public InventoryService() {
//
////        List<Product> products = jsonfileHandler.loadproduct();
////        for (Product product : products) {
////            inventoryRepo.addProduct(product);
////        }
//    }



    public void addProduct(String name, double price, int quantity) throws IOException {
        try{
            if (name == null ){
                throw new ProductNotFoundException("Name cannot be null");
            }
        }catch (ProductNotFoundException e){
            System.out.println(e.getMessage());
        }
        Product product = new Product(0, name,price, quantity);
        inventoryRepo.addProduct(product);
        jsonfileHandler.saveProduct(inventoryRepo.getAllProducts());
        csvHandler.saveProducts(inventoryRepo.getAllProducts());

    }

    public Product getProductById(int id){
        return inventoryRepo.findById(id);

    }

    public List<Product> getProducts(){
        return inventoryRepo.getAllProducts();
    }

    public void updateProduct(int id, String name, double price, int quantity){
        try{
            Product existingProduct = inventoryRepo.findById(id);
            Product product = new Product(id,name,price,quantity);
            inventoryRepo.updateProduct(product);
        } catch (Exception e) {
           throw new ProductNotFoundException(e.getMessage());
        }

    }

    public void deleteProduct(int id){
        try{
            inventoryRepo.deleteProduct(id);
        }catch (ProductNotFoundException e) {
            System.out.println("⚠ ERROR: " + e.getMessage()); // Handle gracefully
//            throw new ProductNotFoundException("⚠ Delete failed: " + e.getMessage());
        }

    }


}
