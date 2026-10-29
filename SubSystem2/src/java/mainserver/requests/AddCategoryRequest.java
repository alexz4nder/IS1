/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.TypedQuery;
import entities.Category;
import javax.persistence.NoResultException;
import subsystem2.SubSystem2;

/**
 *
 * @author aco
 */
public class AddCategoryRequest extends SubSystemRequest{
    public String name;
    public String parentName;
    
    public String response="not processed";
    
    public AddCategoryRequest(String name,String parentName){
        this.name=name;
        this.parentName=parentName;
    }
    private static final long serialVersionUID = 1L;

    @Override
    public void processRequest() {
        TypedQuery<Category> checkCategoryExists=SubSystem2.em.createNamedQuery("Category.findByName",Category.class)
                .setParameter("name", name);
        
        try {
            checkCategoryExists.getSingleResult();
            response="Category alredy exists";
            sendReply();
            return;
        } catch (NoResultException e) {
        }
        
        Category parent=null;
        if (parentName!=null) {
            try {
                checkCategoryExists.setParameter("name",parentName);
                parent=checkCategoryExists.getSingleResult();
            } catch (NoResultException e) {
                response="Parent category doesnt exist";
                sendReply();
                return;
            }
        }
        
        Category newCat = new Category();
        newCat.setName(name);
        newCat.setIdParent(parent);
        SubSystem2.em.getTransaction().begin();
        SubSystem2.em.persist(newCat);
        SubSystem2.em.getTransaction().commit();
        response="Successfully added category";
        sendReply();
    }
    
    
}
