/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.util.Scanner;
import userapp.UserApp;
/**
 *
 * @author aco
 */
public class Help implements Command{

    @Override
    public void Execute(Scanner tokens) {
        if(UserApp.userId.equals(-1)){
        System.out.println("===========================");
        System.out.println("===== AVAIBLE COMMANDS=====");
        System.out.println("===========================");
        System.out.println("login <username> <password>");
        System.out.println("register <username> <passowrd>");
        return;
        }
        System.out.println("===========================");
        System.out.println("===== AVAIBLE COMMANDS=====");
        System.out.println("===========================");
        if (UserApp.isAdmin) {
            System.out.println("allCities");
            System.out.println("addCity <city>");
            System.out.println("addMoney <username> <amount>");
            System.out.println("allUsers");
            System.out.println("changeAddress");
        }
        System.out.println("allCategories");
        System.out.println("addCategory");
        System.out.println("addItem");
        System.out.println("changePrice");
        System.out.println("changeSale");
        System.out.println("allItems");
        System.out.println("addToCart");
        System.out.println("removeFromCart");
        System.out.println("myCart");
        System.out.println("addToWishList");
        System.out.println("removeFromWishList");
        System.out.println("myWishList");
        System.out.println("myOrders");
        System.out.println("payCart");
        System.out.println("allOrders");
        System.out.println("allTransactions");
    }
    
    
}
