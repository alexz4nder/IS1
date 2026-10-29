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
import retrofit2.http.GET;
import retrofit2.http.Query;
import userapp.UserApp;
/**
 *
 * @author aco
 */
public class MyCart implements Command{

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
            List<String> result = UserApp.http.create(MyCartHttp.class).call(UserApp.userId)
                    .execute().body();
            for(String str:result){
                System.out.println(str);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AllItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

interface MyCartHttp{
    @GET("myCart")
    Call<List<String>> call(@Query("userId")int userId);
}
