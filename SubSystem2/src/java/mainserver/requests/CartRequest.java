/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import java.util.List;
import javax.persistence.TypedQuery;
import entities.*;
import java.io.Serializable;
import java.util.LinkedList;
import subsystem2.SubSystem2;
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

    @Override
    public void processRequest(){
        TypedQuery<Cart> query = SubSystem2.em.createNamedQuery("Cart.findByIdUser",Cart.class)
                .setParameter("idUser", userId);
        List<Cart> carts = query.getResultList();
        if(carts.isEmpty()){
            SubSystem2.response.send(SubSystem2.Sys3ResponseQueue, 
                    SubSystem2.context.createObjectMessage(this));
            return ;
        }
        
        
        
        List<CartItem> cartItems = new LinkedList<CartItem>();
        for(Cart i:carts){
            CartItem cartItem = new CartItem();
            cartItem.amount=i.getAmount();
            Item item=i.getIdItem();
            cartItem.itemId=item.getIdItem();
            cartItem.price=item.getPrice()*(1-item.getSale());
            cartItem.itemName=item.getName();
            cartItem.sellerId=item.getIdUser();
            cartItems.add(cartItem);
        }
        items=cartItems;
        
        
        SubSystem2.response.send(SubSystem2.Sys3ResponseQueue, 
                    SubSystem2.context.createObjectMessage(this));
        
    }
    
}
