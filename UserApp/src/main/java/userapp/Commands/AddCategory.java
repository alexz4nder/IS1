/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.io.IOException;
import userapp.UserApp;
import retrofit2.Call;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.http.POST;
import retrofit2.http.Query;
/**
 *
 * @author aco
 */
public class AddCategory implements Command{

    @Override
    public void Execute(Scanner tokens) {
        if (UserApp.userId.equals(-1)) {
            System.out.println("not logged in");
            return;
        }
        if (tokens.hasNext()) {
            System.out.println("To many args");
            return;
        }
        
        String name;
        String parent;
        
        System.out.println("Input name");
        name=UserApp.read.nextLine();
        System.out.println("Input parent (leave empty if category has no parent)");
        parent=UserApp.read.nextLine();
        if (parent=="") {
            parent=null;
        }
        
        try {
            String response = UserApp.http.create(AddCategoryHttp.class)
                    .call(name, parent).execute().body().getFirst();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(AddCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}


interface AddCategoryHttp{
    @POST("addCategory")
    public Call<List<String>> call(@Query("name")String name,@Query("parentName")String parentName);
}