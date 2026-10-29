/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aco
 */
public class CartRequest extends SubSystemRequest{
    public class CartItem implements Serializable{
        public int amount;
        public float price;
        public int itemId;
        public int sellerId;
        public String itemName;
        
        private static final long serialVersionUID = 1L;
    }
    public List<CartItem> items=null;
    int userId;
    public CartRequest(int userId){
        this.userId=userId;
    }
    
    private static final long serialVersionUID = 1L;

    
}
