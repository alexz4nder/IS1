/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class ChangePriceRequest extends SubSystemRequest{
    int userId;
    String itemName;
    float newPrice;
    
    public String response="not processed";
    public ChangePriceRequest(int userId,String itemName,float newPrice){
        this.userId=userId;
        this.itemName=itemName;
        this.newPrice=newPrice;
    }
    private static final long serialVersionUID = 1L;

    
}
