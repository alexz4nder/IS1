/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.*;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
import retrofit2.http.GET;
import retrofit2.http.Query;
import userapp.UserApp;
import java.util.Map;
/**
 *
 * @author aco
 */
public class Login implements  Command {

    @Override
    public void Execute(Scanner tokens) {
        if (!UserApp.userId.equals(-1)) {
            System.out.println("You are alredy logged in");
            return ;
        }
        String username;
        String password;
        try{
        username = tokens.next();
        password = tokens.next();
        }
        catch(NoSuchElementException e){
            System.out.println("Missing arguments");
            return;
        }
        if (tokens.hasNext()) {
            System.out.println("to many arguments");
            return;
        }
        
        Call<Map<String,String>> httpCall = UserApp.http.create(LoginHttp.class).login(username, password);
        Response<Map<String,String>> response=null;
        
        try {
            response = httpCall.execute();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Map<String,String> responesData = response.body();
        if (responesData.get("id").equals("-1")) {
            System.out.println("Couldnt login");
            return;
        }
        
        UserApp.isAdmin=Boolean.parseBoolean(responesData.get("isAdmin"));
        UserApp.userId=Integer.parseInt(responesData.get("id")) ;
        UserApp.username=username;
    }
    
}

interface LoginHttp{
    @GET("userLogin")
    public Call<Map<String,String>> login(@Query("username") String username,@Query("password") String password);
    
}