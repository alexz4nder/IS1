/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import java.util.Scanner;
import userapp.UserApp;
import retrofit2.Call;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.http.PUT;
import retrofit2.http.Query;
/**
 *
 * @author aco
 */
public class ChangePrice implements Command{

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
        float newPrice;
        try {
            System.out.println("Input item name");
            itemName=UserApp.read.nextLine().strip();
            System.out.println("Input new price");
            newPrice =Float.parseFloat(UserApp.read.nextLine());
        } catch (Exception e) {
            System.out.println("error");
            return;
        }
        
        try {
            String response = UserApp.http.create(ChangePriceHttp.class)
                    .call(UserApp.userId, itemName, newPrice).execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(ChangePrice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}

interface ChangePriceHttp{
    @PUT("changePrice")
    public Call<List<String>> call(@Query("userId")int Id,
            @Query("itemName")String name,@Query("newPrice")float newPrice);
}
