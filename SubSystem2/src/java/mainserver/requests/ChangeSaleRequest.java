/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import entities.Item;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import subsystem2.SubSystem2;

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
    
    @Override
    public void processRequest() {
        TypedQuery<Item> query = SubSystem2.em.createNamedQuery("Item.findByIdUserAndName",Item.class)
                .setParameter("idUser", userId)
                .setParameter("name", itemName);
        
        try {
            Item item = query.getSingleResult();
            if (newSale>1.0 ||newSale<0) {
                response="Sale must be between 0 and 1";
                sendReply();
                return;
            }
            item.setSale(newSale);
            SubSystem2.em.getTransaction().begin();
            SubSystem2.em.persist(item);
            SubSystem2.em.getTransaction().commit();
            response="Successfully changed price";
            sendReply();
            return;
        } catch (NoResultException e) {
            response="product doesnt exist";
            sendReply();
            return;
        }
    }
}
