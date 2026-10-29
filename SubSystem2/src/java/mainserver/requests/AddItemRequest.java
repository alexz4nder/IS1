/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.TypedQuery;
import subsystem2.SubSystem2;
import entities.Item;
import entities.Category;
import javax.persistence.NoResultException;
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

    @Override
    public void processRequest() {
        //TODO check if item exists
        
        
        TypedQuery<Category> categoQuery = SubSystem2.em
                .createNamedQuery("Category.findByName", Category.class)
                .setParameter("name", category);
        
        Category cat;
        try {
            cat=categoQuery.getSingleResult();    
        } catch (NoResultException e) {
            response="category doesnt exist";
            sendReply();
            return;
        }
        
        Item newItem = new Item();
        newItem.setIdUser(userId);
        newItem.setName(name);
        newItem.setIdCat(cat);
        newItem.setDescription(description);
        newItem.setPrice(price);
        
        SubSystem2.em.getTransaction().begin();
        SubSystem2.em.persist(newItem);
        SubSystem2.em.getTransaction().commit();
        
        response="successfuly created item";
        sendReply();
    }

}
