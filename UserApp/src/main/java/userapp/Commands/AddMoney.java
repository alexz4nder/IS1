/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import retrofit2.Call;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import userapp.UserApp;
/**
 *
 * @author aco
 */
public class AddMoney implements Command{

    @Override
    public void Execute(Scanner tokens) {
        
        if (UserApp.userId.equals(-1)) {
            System.out.println("not logged in");
            return;
        }
        if (UserApp.isAdmin.equals(false)) {
            System.out.println("you are not admin");
            return;
        }
        
        String username;
        int moneyAmount;
        
        try {
            username=tokens.next();
            moneyAmount=tokens.nextInt();
        } catch (NoSuchElementException e) {
            System.out.println("Missing arguments");
            return ;
        }
        
        if (tokens.hasNext()) {
            System.out.println("Too many arguments");
            return;
        }
        
        try {
            String response = UserApp.http.create(AddMoneyHttp.class).call(username, moneyAmount).execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(AddMoney.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}

interface AddMoneyHttp{
    @PUT("addMoney")
    Call<List<String>> call(@Query("username")String username,@Query("money")float money);
}