package org.example.service;
import org.example.entities.CartItem;
import org.example.entities.Product;
import org.example.jsonFile.JsonFilehandler;
import org.example.logging.Logger;
import org.example.repository.CartRepoImpl;
import java.util.List;

public class CartService  {
    private CartRepoImpl cartRepoimpl;
    private InventoryService inventoryService ;
    private JsonFilehandler jsonFilehandler = new JsonFilehandler();

    public CartService(CartRepoImpl cartRepoimpl, InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.cartRepoimpl = cartRepoimpl;
    }

    public void addToCart(int productId, int quantity){
        Product product = inventoryService.getProductById(productId);
        cartRepoimpl.addItem(product,quantity);
        product.setQuantity(product.getQuantity()-quantity);
//        jsonFilehandler.saveProduct(product);
        Logger.logInfo("Product added to cart.");
    }

    public List<CartItem> viewCart(){
        return cartRepoimpl.getAllCartItems();
    }
}
