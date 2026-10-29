/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import entities.*;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import subsystem2.SubSystem2;

/**
 *
 * @author aco
 */
public class AddToWishListRequest extends SubSystemRequest{
    int userId;
    String itemName;
    
    public String response="not proccessed";
    
    public AddToWishListRequest(int userId,String itemName){
        this.userId=userId;
        this.itemName=itemName;
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
        
        TypedQuery<Wishlist> queryWish = SubSystem2.em.createNamedQuery("Wishlist.findByIdUserAndIdItem",Wishlist.class)
                .setParameter("idUser", userId)
                .setParameter("idItem", item);
        
        try {
            Wishlist wishlist = queryWish.getSingleResult();
            
            response="Item alredy on WishList";
            sendReply();
            return;
        } catch (NoResultException e) {
            Wishlist wish = new Wishlist();
            wish.setIdItem(item);
            wish.setIdUser(userId);
            SubSystem2.em.getTransaction().begin();
            SubSystem2.em.persist(wish);
            SubSystem2.em.getTransaction().commit();
            SubSystem2.em.refresh(wish);
            response="Success";
            sendReply();
            return;
        }
        
        
    }
    
}
