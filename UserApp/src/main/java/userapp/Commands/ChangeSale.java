/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import userapp.UserApp;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Query;
/**
 *
 * @author aco
 */
public class ChangeSale implements Command{

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
        float sale;
        try {
            System.out.println("Input item name");
            itemName=UserApp.read.nextLine().strip();
            System.out.println("Input sale percentage");
            sale =Float.parseFloat(UserApp.read.nextLine())/100.0f;
            if (sale>1.0f || sale<0.0f) {
                System.out.println("Sale must between 0 and 100");
                return;
            }
        } catch (Exception e) {
            System.out.println("error");
            return;
        }
        
        try {
            String response = UserApp.http.create(ChangeSaleHttp.class)
                    .call(UserApp.userId, itemName, sale).execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(ChangePrice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}

interface ChangeSaleHttp{
    @PUT("changeSale")
    public Call<List<String>> call(@Query("userId")int Id,
            @Query("itemName")String name,@Query("newSale")float newSale);
}