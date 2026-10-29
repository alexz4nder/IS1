/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import java.util.Scanner;
import userapp.UserApp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.http.GET;
/**
 *
 * @author aco
 */
public class AllUsers implements Command{

    @Override
    public void Execute(Scanner tokens) {
        if (UserApp.userId.equals(-1)) {
            System.out.println("You are not logged in");
            return;
        }
        if (UserApp.isAdmin.equals(false)) {
            System.out.println("You are not admin");
            return;
        }
        
        if (tokens.hasNext()) {
            System.out.println("To many arguments");
            return ;
        }
        
        try {
            List<String> result = UserApp.http.create(AllUsersHttp.class).call().execute().body();
            for(String s:result){
                System.out.println(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(AllUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

interface AllUsersHttp{
    @GET("allUsers")
    Call<List<String>> call();
}