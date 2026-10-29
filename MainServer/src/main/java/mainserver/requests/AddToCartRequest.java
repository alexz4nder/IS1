/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class AddToCartRequest extends SubSystemRequest{
    int userId;
    String itemName;
    int amount;
    
    public String response="not proccessed";
    
    public AddToCartRequest(int userId,String itemName,int amount){
        this.userId=userId;
        this.itemName=itemName;
        this.amount=amount;
    }
    
    private static final long serialVersionUID = 1L;

}
