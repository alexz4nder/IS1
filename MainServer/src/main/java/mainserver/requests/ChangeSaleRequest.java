/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class ChangeSaleRequest extends SubSystemRequest{
    int userId;
    String itemName;
    float newSale;
    
    public String response="not processed";
    public ChangeSaleRequest(int userId,String itemName,float newSale){
        this.userId=userId;
        this.itemName=itemName;
        this.newSale=newSale;
    }
    private static final long serialVersionUID = 1L;
}
