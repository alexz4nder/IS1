/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import java.util.List;
import javax.persistence.TypedQuery;
import entities.Order;
import java.util.LinkedList;
import subsystem3.SubSystem3;
/**
 *
 * @author aco
 */
public class MyOrdersRequest extends SubSystemRequest{
    int userId;
    
    public List<String> response;
    public MyOrdersRequest(int userId){
        this.userId=userId;
    }
    private static final long serialVersionUID = 1L;
    
    @Override
    public void processRequest(){
        TypedQuery<Order> query = SubSystem3.em.createNamedQuery("Order.findByIdUser",Order.class)
                .setParameter("idUser", userId);
        
        List<Order> orders = query.getResultList();
        response = new LinkedList<String>();
        if (orders.isEmpty()) {
            response.add("User has no orders");
            sendReply();
            return;
        }
        
        for(Order i:orders){
            String str = "";
            
            str+="IdOrder:"+ i.getIdOrder().toString();
            str+="|TotalPrice:"+Float.toString(i.getTotalPrice());
            str+="|IdCity:"+Integer.toString(i.getIdCity());
            str+="|Address:"+i.getAddress();
            str+="|Time:"+i.getTime().toString();
            response.add(str);
        }
        
        sendReply();
        
    }
}
