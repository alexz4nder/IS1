/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import userapp.UserApp;
/**
 *
 * @author aco
 */
public class Register implements Command{

    @Override
    public void Execute(Scanner tokens) {
        if (!UserApp.userId.equals(-1)) {
            System.out.println("You must be logged out to register");
            return;
        }
        
        String username;
        String password;
        
        try {
            username=tokens.next();
            password=tokens.next();
        } catch (NoSuchElementException e) {
            System.out.println("Missing arguments");
            return;
        }
        if (tokens.hasNext()) {
            System.out.println("To many arguments");
            return;
        }
        
        String name;
        String surname;
        
        System.out.println("Input your name");
        name = UserApp.read.nextLine();
        System.out.println("Input your surname");
        surname = UserApp.read.nextLine();
        
        String city;
        String address;
        System.out.println("Input city you live in");
        city = UserApp.read.nextLine();
        System.out.println("Input your address");
        address = UserApp.read.nextLine();
        
        try {
            String response = UserApp.http.create(RegisterHttp.class)
                    .call(username, password, name, surname, city, address)
                    .execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}

interface RegisterHttp{
    @POST("register")
    Call<List<String>> call(@Query("username") String username,@Query("password")String password,
            @Query("name")String name,@Query("surname")String surname,
            @Query("city")String city,@Query("address")String address);
}
