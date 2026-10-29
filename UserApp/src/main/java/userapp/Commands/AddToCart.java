/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import userapp.UserApp;
import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Query;
/**
 *
 * @author aco
 */
public class AddToCart implements Command{

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
        
        String itemName;
        int amount;
        
        try {
            System.out.println("Input item name:");
            itemName=UserApp.read.nextLine();
            System.out.println("Input amount:");
            amount = Integer.parseInt(userapp.UserApp.read.nextLine());
        } catch (Exception e) {
            System.out.println("error");
            return;
        }

        try {
            String response = UserApp.http.create(AddToCartHttp.class).call(UserApp.userId, itemName, amount)
                    .execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(AddToCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}

interface AddToCartHttp{
    @PUT("addToCart")
    Call<List<String>>call(@Query("userId")int userId
            ,@Query("itemName")String itemName,@Query("amount")int amount);
}