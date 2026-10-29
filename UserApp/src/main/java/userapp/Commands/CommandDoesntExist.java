/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import java.util.Scanner;

/**
 *
 * @author aco
 */
public class CommandDoesntExist implements Command{

    @Override
    public void Execute(Scanner tokens) {
        System.out.println("Command doesnt exitst");
    }
    
}
