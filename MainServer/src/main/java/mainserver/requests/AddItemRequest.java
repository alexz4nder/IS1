/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class AddItemRequest extends SubSystemRequest{
    int userId;
    String name;
    String category;
    String description;
    float price;
    
    public String response = "not processed";
    
    public AddItemRequest(int userId,String name,String category,String description,float price){
        this.userId=userId;
        this.name=name;
        this.category=category;
        this.description=description;
        this.price=price;
    }
    
    private static final long serialVersionUID = 1L;

}
