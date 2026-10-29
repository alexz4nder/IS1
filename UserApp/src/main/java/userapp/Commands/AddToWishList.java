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
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import userapp.UserApp;

/**
 *
 * @author aco
 */
public class AddToWishList implements Command{
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
        
        try {
            System.out.println("Input item name:");
            itemName=UserApp.read.nextLine();
            
        } catch (Exception e) {
            System.out.println("error");
            return;
        }

        try {
            String response = UserApp.http.create(AddToWishListHttp.class).call(UserApp.userId, itemName)
                    .execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(AddToCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}


interface AddToWishListHttp{
    @POST("addToWishList")
    Call<List<String>>call(@Query("userId")int userId
            ,@Query("itemName")String itemName);
}