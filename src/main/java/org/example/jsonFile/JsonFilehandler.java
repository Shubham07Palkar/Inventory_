package org.example.jsonFile;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Product;
import org.example.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.repository.InventoryRepoImpl.products;

public class JsonFilehandler {
  private static ObjectMapper objMap = new ObjectMapper();
  private static String filepath = "C:\\Users\\ShubhamPalkar\\OneDrive - Atyeti Inc\\Documents\\SpringBoot-Dev\\spring data jpa and hibernate\\InventoryManagement\\src\\main\\java\\org\\example\\Products.json";

    public List<Product> loadproduct(){
        File file = new File(filepath);
        try{
            if(file.exists() && file.length()>0){
               objMap.readValue(file, new TypeReference<List<Product>>() {
               });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    public void saveProduct(List<Product> products){

        try{
            objMap.writerWithDefaultPrettyPrinter().writeValue(new File(filepath), products);
            Logger.logInfo("Product saved to Json File");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
