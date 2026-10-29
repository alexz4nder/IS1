/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import entities.Item;
import entities.Wishlist;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import subsystem2.SubSystem2;

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
            SubSystem2.em.getTransaction().begin();
            SubSystem2.em.remove(wishlist);
            SubSystem2.em.getTransaction().commit();
            response="Item removed from wishlist";
            sendReply();
            return;
        } catch (NoResultException e) {
            
            response="Item isnt on wishlist";
            sendReply();
            return;
        }
        
        
    }
    
}
