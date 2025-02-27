package org.example;

import org.example.entities.Customer;
import org.example.entities.Seller;
import org.example.entities.User;
import org.example.entities.UserRole;
import org.example.logging.Logger;
import org.example.repository.CartRepoImpl;
import org.example.repository.InventoryRepoImpl;
import org.example.service.CartService;
import org.example.service.InventoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static List<User> users = new ArrayList<>();
//    private static int userIdCounter =1;
    public static void main(String[] args) throws IOException {
        InventoryService inventoryService = new InventoryService();
        Scanner sc = new Scanner(System.in);
        CartService cartService = new CartService(new CartRepoImpl(), inventoryService);

        boolean running = true;

        while(running){
            System.out.println("\n - Inventory Management System -");
            System.out.println("1.Register User");
            System.out.println("2.View Users");
            System.out.println("3. Add Product");
            System.out.println("4. View Products");
            System.out.println("5. Update Product");
            System.out.println("6. Delete Product");
            System.out.println("7. Add Items to cart");
            System.out.println("8. View Cart");
            System.out.println("9. Exit ");
            System.out.println("Rohit Patil");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter User Id");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Username");
                    String name=sc.nextLine();
                    System.out.println("Enter Email");
                    String email = sc.nextLine();
                    System.out.println("Enter User Role(1. customer, 2.Seller)");
                    int roleChoice = sc.nextInt();
                    UserRole role = (roleChoice==1)?UserRole.CUSTOMER:UserRole.SELLER;

                    User user = (role==UserRole.CUSTOMER)
                            ?new Customer(id,name,email,role)
                            :new Seller(id, name,email,role);

                    users.add(user);
                    Logger.logInfo("User Registered Successfully");
                    System.out.println(user);
                    break;

                case 2: // view users
                    List<User> registeredUsers= users;
                    System.out.println(registeredUsers);
                    break;

                case 3:
//                    name,price, quantity,seller
                    System.out.print("Enter Product Name: ");
                    String prodName = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double prodPrice = sc.nextDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int quantity = sc.nextInt();
                    inventoryService.addProduct(prodName,prodPrice,quantity);
                    break;

                case 4:
                    inventoryService.getProducts().forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("Enter User Id");
                    int updatedId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String updatedprodName = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double updatedProdPrice = sc.nextDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int updatedQuantity = sc.nextInt();
                    System.out.print("Enter Seller Id: ");
                    String seller = sc.nextLine();
                    inventoryService.updateProduct(updatedId,updatedprodName,updatedProdPrice,updatedQuantity);
                    break;

                case 6:
                    System.out.println("Enter product Id to delete");
                    int prodId = sc.nextInt();
                    inventoryService.deleteProduct(prodId);
                    break;

                case 7:
                    System.out.println("Enter ProductId");
                    int itemId = sc.nextInt();
                    System.out.println("Enter Quantity");
                    int cartQuantity = sc.nextInt();
                    cartService.addToCart(itemId,cartQuantity);
                    break;

                case 8:
                    cartService.viewCart().forEach(System.out::println);

                case 9:
                     running = false;
            }
        }
    }
}