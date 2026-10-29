/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.TypedQuery;
import entities.*;
import javax.persistence.NoResultException;
import subsystem2.SubSystem2;
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
            cart.setAmount(cart.getAmount()+amount);
            SubSystem2.em.getTransaction().begin();
            SubSystem2.em.persist(cart);
            SubSystem2.em.getTransaction().commit();
            response="Success";
            sendReply();
            return;
        } catch (NoResultException e) {
            Cart cart = new Cart();
            cart.setIdItem(item);
            cart.setIdUser(userId);
            cart.setAmount(amount);
            SubSystem2.em.getTransaction().begin();
            SubSystem2.em.persist(cart);
            SubSystem2.em.getTransaction().commit();
            response="Success";
            sendReply();
            return;
        }
        
        
    }

    
}
