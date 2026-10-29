/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import entities.*;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.TypedQuery;
import subsystem2.SubSystem2;

/**
 *
 * @author aco
 */
public class MyCartRequest extends SubSystemRequest{
    int userId;
    
    public List<String> response;
    public MyCartRequest(int userId){
        this.userId=userId;
    }
    private static final long serialVersionUID = 1L;
    
    
    
    @Override
    public void processRequest() {
        TypedQuery<Cart> query = SubSystem2.em.createNamedQuery("Cart.findByIdUser",Cart.class)
                .setParameter("idUser", userId);
        
        
        List<Cart> carts = query.getResultList();
        response=new LinkedList<>();
        if (carts.isEmpty()) {
            response.add("User has no items");
            sendReply();
            return;
        }
        float cartPrice=0;
        for(Cart i:carts){
            String str ="";
            str+="id:" + i.getIdItem().getIdItem().toString();
            str+="|name:"+i.getIdItem().getName();
            str+="|category:"+i.getIdItem().getIdCat().getName();
            str+="|price:"+Float.toString(i.getIdItem().getPrice());
            str+="|sale:"+Float.toString(i.getIdItem().getSale()*100);
            float priceWithSale = i.getIdItem().getPrice()*(1.0f-i.getIdItem().getSale());
            str+="|price with sale:"+Float.toString(priceWithSale);
            str+="|amount:"+Integer.toString(i.getAmount());
            str+="|final price:"+Float.toString(priceWithSale*i.getAmount());

            response.add(str);
            cartPrice+=priceWithSale*i.getAmount();
        }
        response.add("CART PRICE:"+Float.toString(cartPrice));
        sendReply();
        
    }
}
