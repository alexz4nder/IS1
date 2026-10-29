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
public class AllCities implements Command {

    @Override
    public void Execute(Scanner tokens) {
        if (UserApp.isAdmin.equals(false)) {
            System.out.println("You need admin permission for this command");
            return;
        }
        try {
            List<String> res = UserApp.http.create(AllCitiesHttp.class).call().execute().body();
            if (res==null) {
                System.out.println("No cities");
                return;
            }
            for(String s:res){
                System.out.println(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(AllCities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}

interface AllCitiesHttp{
    @GET("allCities")
    Call<List<String>> call();
}