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
public class PayCart implements Command{

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
        
        String city;
        String address;
        
        try {
            System.out.println("Input city name:");
            city=UserApp.read.nextLine();
            System.out.println("Input address:");
            address = userapp.UserApp.read.nextLine();
        } catch (Exception e) {
            System.out.println("error");
            return;
        }
        
        try {
            String response = UserApp.http.create(PayCartHttp.class)
                    .call(UserApp.userId, city, address)
                    .execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(PayCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}


interface PayCartHttp{
    @POST("payCart")
    Call<List<String>> call(@Query("userId")int userId,
            @Query("cityName")String cityName,
            @Query("address")String address);
}