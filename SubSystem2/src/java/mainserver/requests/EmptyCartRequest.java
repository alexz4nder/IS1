/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import entities.Cart;
import java.util.List;
import javax.persistence.TypedQuery;
import subsystem2.SubSystem2;

/**
 *
 * @author aco
 */
public class EmptyCartRequest extends SubSystemRequest{
    int userId;
    
    public EmptyCartRequest(int userId){
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
        
        SubSystem2.em.getTransaction().begin();
        for(Cart i:carts){
            SubSystem2.em.remove(i);
        }
        SubSystem2.em.getTransaction().commit();
        
        
    }
}
