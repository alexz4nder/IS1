/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.TypedQuery;
import entities.Item;
import javax.persistence.NoResultException;
import subsystem2.SubSystem2;
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

    @Override
    public void processRequest() {
        TypedQuery<Item> query = SubSystem2.em.createNamedQuery("Item.findByIdUserAndName",Item.class)
                .setParameter("idUser", userId)
                .setParameter("name", itemName);
        
        try {
            Item item = query.getSingleResult();
            item.setPrice(newPrice);
            SubSystem2.em.getTransaction().begin();
            SubSystem2.em.persist(item);
            SubSystem2.em.getTransaction().commit();
            response="Successfully changed sale";
            sendReply();
            return;
        } catch (NoResultException e) {
            response="product doesnt exist";
            sendReply();
            return;
        }
        
    }

    
    
}
