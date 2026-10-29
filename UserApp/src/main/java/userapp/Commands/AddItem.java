/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import userapp.UserApp;
import retrofit2.Call;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.http.POST;
import retrofit2.http.Query;
/**
 *
 * @author aco
 */
public class AddItem implements Command{

    @Override
    public void Execute(Scanner tokens) {
        if (UserApp.userId.equals(-1)) {
            System.out.println("Not logged in");
            return;
        }
        if (tokens.hasNext()) {
            System.out.println("To many arguments");
            return;
        }
        
        
        String name;
        String category;
        String description;
        float price;
        try {
            System.out.println("Input product name");
            name = UserApp.read.nextLine();
            System.out.println("Input product category");
            category = UserApp.read.nextLine().strip();
            System.out.println("Input product description");
            description = UserApp.read.nextLine();
            System.out.println("Input product price");
            price = Float.parseFloat(UserApp.read.nextLine());
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        
        String response;
        try {
            response = UserApp.http.create(AddItemHttp.class).call(UserApp.userId,
                    name, category, description, price).execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
}

interface AddItemHttp{
    @POST("addItem")
    public Call<List<String>> call(@Query("idUser")int idUser,@Query("name")String name,
            @Query("category")String category,@Query("description")String description,
            @Query("price")float price);
}
