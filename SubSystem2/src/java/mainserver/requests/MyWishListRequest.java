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
import java.util.Date;
/**
 *
 * @author aco
 */
public class MyWishListRequest extends SubSystemRequest {
    int userId;
    
    public List<String> response;
    public MyWishListRequest(int userId){
        this.userId=userId;
    }
    private static final long serialVersionUID = 1L;
    
    @Override
    public void processRequest() {
        TypedQuery<Wishlist> query = SubSystem2.em.createNamedQuery("Wishlist.findByIdUser",Wishlist.class)
                .setParameter("idUser", userId);
        
        
        List<Wishlist> wishes = query.getResultList();
        response=new LinkedList<>();
        if (wishes.isEmpty()) {
            response.add("User has no items");
            sendReply();
            return;
        }
        for(Wishlist i:wishes){
            String str ="";
            str+="id:" + i.getIdItem().getIdItem().toString();
            str+="|name:"+i.getIdItem().getName();
            str+="|category:"+i.getIdItem().getIdCat().getName();
            str+="|price:"+Float.toString(i.getIdItem().getPrice());
            str+="|date added:"+i.getTime().toString();

            response.add(str);
        }
        sendReply();
        
    }
    
}
