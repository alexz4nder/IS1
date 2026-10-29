/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import java.util.Scanner;
import userapp.UserApp;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author aco
 */
public class AllCategories implements Command{

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
        
        try {
            List<String> response = UserApp.http.create(AllCategoriesHttp.class).call().execute().body();
            for(String s:response){
                System.out.println(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(AllCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    interface AllCategoriesHttp{
        @GET("allCategories")
        public Call<List<String>> call();
    }
    
}
