/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import entities.Order;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.TypedQuery;
import subsystem3.SubSystem3;

/**
 *
 * @author aco
 */
public class AllOrdersRequest extends SubSystemRequest{
    public List<String> response=null;
    
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void processRequest(){
        TypedQuery<Order> query = SubSystem3.em.createNamedQuery("Order.findAll",Order.class);
        
        
        List<Order> orders = query.getResultList();
        response=new LinkedList<String>();
        if (orders.isEmpty()) {
            response.add("No Orders");
            sendReply();
            System.out.println("NO ORDERS");
            return;
        }
        for(Order i:orders){
            String str ="";
            str+="id:" + i.getIdOrder().toString();
            str+="|idUser:"+i.getIdUser();
            str+="|totalPrice:"+Float.toString(i.getTotalPrice());
            str+="|idCity:"+Integer.toString( i.getIdCity());
            str+="|address:"+i.getAddress();
            str+="|time:"+i.getTime().toString();
            response.add(str);
        }
        System.out.println("orders[0]  "+response.get(0));
        sendReply();
    }
}
