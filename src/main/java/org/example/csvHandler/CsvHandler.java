package org.example.csvHandler;

import org.example.entities.Product;
import org.example.logging.Logger;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;


public class CsvHandler {
    private static final String FILE_PATH = "C:\\Users\\ShubhamPalkar\\OneDrive - Atyeti Inc\\Documents\\SpringBoot-Dev\\spring data jpa and hibernate\\InventoryManagement\\src\\main\\java\\org\\example\\Products.csv";

    public List<Product> loadproducts() throws FileNotFoundException {
        List<Product> products = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists() || file.length()==0){
            return products;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            boolean isheader = true;

            while((line = br.readLine())!=null){
                if (isheader){
                    isheader= false;
                    continue;
                }

                String[] values = line.split(",");
                if(values.length==4){
                    int id = Integer.parseInt(values[0].trim());
                    String name = values[1].trim();
                    double price = Double.parseDouble(values[2].trim());
                    int quantity = Integer.parseInt(values[3].trim());
                    products.add(new Product(id, name, price, quantity));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return products;
    }

    public void saveProducts(List<Product> products) throws IOException {
        try(BufferedWriter brw = new BufferedWriter(new FileWriter(FILE_PATH))){
            brw.write(("ID,Name,Price,Quantity"));
            brw.newLine();

            for (Product product:products){
                brw.write(product.getId()+","+product.getName()+","+product.getPrice()+","+product.getQuantity());
                brw.newLine();
            }
            Logger.logInfo("Products Saved to CSV File.");
        }
    }
}
