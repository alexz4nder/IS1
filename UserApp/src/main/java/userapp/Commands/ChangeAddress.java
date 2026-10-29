/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import java.util.NoSuchElementException;
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
public class ChangeAddress implements Command{

    @Override
    public void Execute(Scanner tokens) {
        if(UserApp.userId.equals(-1)){
            System.out.println("You must be logged in");
            return ;
        }
        if (UserApp.isAdmin.equals(false)) {
            System.out.println("you must be admin");
            return;
        }
        
        String username;
        String newCity;
        String newAddress;
        try {
            username=tokens.next();
            
        } catch (NoSuchElementException e) {
            System.out.println("Missing arguments");
            return;
        } 
        
        if (tokens.hasNext()) {
            System.out.println("Too many arguments");
            return;
        }
        
        System.out.println("Input new city");
        newCity=UserApp.read.nextLine();
        System.out.println("Input new address");
        newAddress=UserApp.read.nextLine();

        try {
            String response = UserApp.http.create(ChangeAddressHttp.class)
                    .call(username, newCity, newAddress).execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(ChangeAddress.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}


interface ChangeAddressHttp{
    @PUT("changeAddress")
    Call<List<String>> call(@Query("username")String username, 
            @Query("newCity")String newCity,@Query("newAddress")String newAddress);
}