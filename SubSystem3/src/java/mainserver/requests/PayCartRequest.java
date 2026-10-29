/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import subsystem3.SubSystem3;
import entities.*;
import java.util.Collection;
import java.util.LinkedList;
/**
 *
 * @author aco
 */
public class PayCartRequest extends SubSystemRequest {
    int userId;
    String cityName;
    String address;
    
    public String response = "not processed";
    
    public PayCartRequest(int userId,String cityName,String address){
        this.cityName=cityName;
        this.userId=userId;
        this.address=address;
    }
    
    private static final long serialVersionUID = 1L;

    
    
    @Override
    public void processRequest(){
        //get cart items
        CartRequest cartRequest = new CartRequest(userId);
        SubSystem3.response.send(SubSystem3.sys2Requests, 
                SubSystem3.context.createObjectMessage(cartRequest));
        ObjectMessage cartReplyMsg = (ObjectMessage)SubSystem3.sys3Response.receive();
        CartRequest cartReply;
        try {
            cartReply = cartReplyMsg.getBody(CartRequest.class);
        } catch (JMSException ex) {
             try {
                 cartReplyMsg = (ObjectMessage)SubSystem3.sys3Response.receive();
                cartReply = cartReplyMsg.getBody(CartRequest.class);
            } catch (JMSException e) {
                Logger.getLogger(PayCartRequest.class.getName()).log(Level.SEVERE, null, e);
                response="error";
                sendReply();
                return;
            }
            
        }
        if (cartReply.items.isEmpty()) {
            response="no items in cart";
            sendReply();
            return;
        }
        
        
        //creating order,suborders and calculating total price
        Order order = new Order();
        order.setIdUser(userId);
        order.setAddress(address);
        float totalPrice = 0;
        Collection<Suborder> suborders=new LinkedList<>();
        order.setSuborderCollection(suborders);
        for(CartRequest.CartItem i:cartReply.items){
            totalPrice+=i.amount*i.price;
            Suborder suborder = new Suborder();
            suborder.setAmount(i.amount);
            suborder.setUnitPrice(i.price);
            suborder.setIdItem(i.itemId);
            suborder.setIdOrder(order);
            order.getSuborderCollection().add(suborder);
        }
        order.setTotalPrice(totalPrice);
        
        
        //city id and balance check
        CityIdAndMoneyRequest cityAndMoneyRequest = new CityIdAndMoneyRequest(cityName,userId,totalPrice);
        SubSystem3.response.send(SubSystem3.sys1Requests, 
                SubSystem3.context.createObjectMessage(cityAndMoneyRequest));
        ObjectMessage cityAndMoneyReplyMsg = (ObjectMessage)SubSystem3.sys3Response.receive();
        CityIdAndMoneyRequest cityAndMoneyReply;
        try {
            cityAndMoneyReply = cityAndMoneyReplyMsg.getBody(CityIdAndMoneyRequest.class);
        } catch (JMSException ex) {
            try {
                cityAndMoneyReplyMsg = (ObjectMessage)SubSystem3.sys3Response.receive();
            cityAndMoneyReply = cityAndMoneyReplyMsg.getBody(CityIdAndMoneyRequest.class);
            } catch (JMSException e) {
            Logger.getLogger(PayCartRequest.class.getName()).log(Level.SEVERE, null, e);
            response="error";
            sendReply();
            return;
            }   
            
        }
        
        if(cityAndMoneyReply.cityId==-1){
            response="city doesnt exist";
            sendReply();
            return;
        }
        if (cityAndMoneyReply.money<0) {
            response="no money";
            sendReply();
            return;
        }
        
        
        
        //finishing order and creating transaction;
        order.setIdCity(cityAndMoneyReply.cityId);
        Transaction transaction = new Transaction();
        transaction.setIdOrder(order);
        Collection<Transaction> transactions = new LinkedList<>();
        order.setTransactionCollection(transactions);
        order.getTransactionCollection().add(transaction);
        transaction.setTotalPrice(totalPrice);
        
        
        //persisting order
        SubSystem3.em.getTransaction().begin();
        SubSystem3.em.persist(order);
        SubSystem3.em.getTransaction().commit();
        SubSystem3.em.refresh(order);
        
        
        //emptying cart
        EmptyCartRequest emptyCartRequest = new EmptyCartRequest(userId);
        SubSystem3.response.send(SubSystem3.sys2Requests,
                SubSystem3.context.createObjectMessage(emptyCartRequest));
        
        
        response="success";
        sendReply();
    }
}
