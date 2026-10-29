/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package userapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import userapp.Commands.*;
import java.util.Map.Entry;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 *
 * @author aco
 */
public class UserApp {

    /**
     * @param args the command line arguments
     */
    public static Boolean isAdmin=false;
    public static Integer userId=-1;
    public static String username="";
    
    public static Scanner read = new Scanner(System.in);

    public static Retrofit http = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/MainServer/resources/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    
    public static void main(String[] args) {
        initCommandLookup();
        
        welcome();
        while (true) {
            printPrompt();
            String l = read.nextLine();
            Scanner tokens = new Scanner(l);
            try {
                String command = tokens.next();
                System.out.println("first token:"+command);
                if (command.equals("quit")){
                    return;
                }
                commandLookup.getOrDefault(command, new CommandDoesntExist()).Execute(tokens);
            }
            catch(Exception e){
                
            }
        }
    }
    
    static void welcome(){
        System.out.println("===========================");
        System.out.println("========= WELCOME =========");
        System.out.println("===========================");
        System.out.println("\nuse command \"help\" for list of avaible commands\n");
    }
    
    static void printPrompt(){
        if (userId.equals(-1)) {
            System.out.print("guest >> ");
        }
        else{
            if (isAdmin) {
                System.out.print("admin/");
            }
            else{
                System.out.print("user/");
            }
            System.out.print(username+" >> ");
        }
    }
    
    static Map<String, Command> commandLookup=new HashMap<>();
    static void initCommandLookup(){
        commandLookup.put("help", new Help());
        //SubSystem1
        commandLookup.put("login", new Login());
        commandLookup.put("allCities", new AllCities());
        commandLookup.put("addCity", new AddCity());
        commandLookup.put("register", new Register());
        commandLookup.put("addMoney", new AddMoney());
        commandLookup.put("changeAddress", new ChangeAddress());
        commandLookup.put("allUsers", new AllUsers());
        //SubSystem2
        commandLookup.put("allCategories", new AllCategories());
        commandLookup.put("addCategory", new AddCategory());
        commandLookup.put("addItem", new AddItem());
        commandLookup.put("changePrice", new ChangePrice());
        commandLookup.put("changeSale", new ChangeSale());
        commandLookup.put("allItems", new AllItems());
        commandLookup.put("addToCart", new AddToCart());
        commandLookup.put("removeFromCart", new RemoveFromCart());
        commandLookup.put("myCart", new MyCart());
        commandLookup.put("addToWishList", new AddToWishList());
        commandLookup.put("removeFromWishList",new RemoveFromWishList());
        commandLookup.put("myWishList", new MyWishList());
        //SubSystem3
        commandLookup.put("myOrders", new MyOrders());
        commandLookup.put("payCart", new PayCart());
        commandLookup.put("allOrders", new AllOrders());
        commandLookup.put("allTransactions", new AllTransactions());
    }
}
