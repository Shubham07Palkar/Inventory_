package org.example.repository;

import org.example.csvHandler.CsvHandler;
import org.example.entities.Product;
import org.example.exception.ProductNotFoundException;
import org.example.jsonFile.JsonFilehandler;
import org.example.logging.Logger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepoImpl implements InventoryRepo {
    public static List<Product> products = new ArrayList<>(
    );
    JsonFilehandler jsonFilehandler = new JsonFilehandler();
    CsvHandler csvHandler= new CsvHandler();
    private int productCount=100;

    public InventoryRepoImpl() throws FileNotFoundException {
        products = jsonFilehandler.loadproduct();
        products = csvHandler.loadproducts();
    }

    @Override
    public void addProduct(Product product) {
        product.setId(++productCount);
        products.add(product);
        Logger.logInfo("Successfully added product");
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product findById(int id) {
        try {
            Product product = products.
                    stream().
                    filter(x -> x.getId() == id).
                    findFirst().orElse(null);
            if (product == null) {
                Logger.logWarn("Product with Id{} not found", id);
                throw new ProductNotFoundException("Prooduct not Found.");
            } else {
                Logger.logInfo("Product Found :");
            }
            return product;
        }catch (ProductNotFoundException e){
            throw e;
        }
    }


    @Override
    public void updateProduct(Product product) {
        Product existingProduct = findById(product.getId());
//        Seller seller = findById(product.getId()).getSeller();
        if(existingProduct!=null){
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            Logger.logInfo("Product updated :");
        }
    }

    @Override
    public void deleteProduct(int id) {
        Product product = findById(id);
        products.remove(product);
        Logger.logInfo("Product deleted Successfully");
    }


}
