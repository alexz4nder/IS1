/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import entities.Cart;
import entities.Item;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import subsystem2.SubSystem2;

/**
 *
 * @author aco
 */
public class RemoveFromCartRequest extends SubSystemRequest {
    int userId;
    String itemName;
    int amount;
    
    public String response="not proccessed";
    
    public RemoveFromCartRequest(int userId,String itemName,int amount){
        this.userId=userId;
        this.itemName=itemName;
        this.amount=amount;
    }
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void processRequest() {
        TypedQuery<Item> queryItem = SubSystem2.em.createNamedQuery("Item.findByName",Item.class)
                .setParameter("name", itemName);
        Item item;
        
        try {
            item=queryItem.getSingleResult();
        } catch (NoResultException e) {
            response="Item doesnt exist";
            sendReply();
            return;
        }
        
        TypedQuery<Cart> queryCart = SubSystem2.em.createNamedQuery("Cart.findByIdUserAndIdItem",Cart.class)
                .setParameter("idUser", userId)
                .setParameter("idItem", item);
        
        try {
            Cart cart = queryCart.getSingleResult();
            if (amount>cart.getAmount()) {
                response="amount is bigger then amount in cart";
                sendReply();
                return;
            }
            cart.setAmount(cart.getAmount()-amount);
            if (cart.getAmount()==0) {
                SubSystem2.em.getTransaction().begin();
                SubSystem2.em.remove(cart);
                SubSystem2.em.getTransaction().commit();
                response="Success";
                sendReply();
                return;
            }
            SubSystem2.em.getTransaction().begin();
            SubSystem2.em.persist(cart);
            SubSystem2.em.getTransaction().commit();
            response="Success";
            sendReply();
            return;
        } catch (NoResultException e) {
            
            response="Item isnt in cart";
            sendReply();
            return;
        }
        
        
    }
    
}
