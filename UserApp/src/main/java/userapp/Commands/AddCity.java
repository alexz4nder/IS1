/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import userapp.UserApp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.List;
import java.util.NoSuchElementException;
/**
 *
 * @author aco
 */
public class AddCity implements Command{

    @Override
    public void Execute(Scanner tokens) {
        try {
            if(UserApp.userId.equals(-1)){
                return;
            }
            if (UserApp.isAdmin.equals(false)) {
                return;
            }
            
            
            String cityname ="";
            try {
                cityname = tokens.next();
            } catch (NoSuchElementException e) {
                System.out.println("Misssing arguments");
                return;
            }
            if (tokens.hasNext()==true) {
                System.out.println("too many arguments");
                return;
            }
            List<String> response =UserApp.http.create(AddCityHttp.class).call(cityname).execute().body();
            System.out.println(response.getFirst());
            
        } catch (IOException ex) {
            Logger.getLogger(AddCity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

interface AddCityHttp{
    @POST("addCity")
    public Call<List<String>> call(@Query("cityName") String city);
}
