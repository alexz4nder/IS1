/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class RemoveFromWishListRequest extends SubSystemRequest{
    int userId;
    String itemName;
    
    public String response="not proccessed";
    
    public RemoveFromWishListRequest(int userId,String itemName){
        this.userId=userId;
        this.itemName=itemName;
    }
    
    private static final long serialVersionUID = 1L;
}
