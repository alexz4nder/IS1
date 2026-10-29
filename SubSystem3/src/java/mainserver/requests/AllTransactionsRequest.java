/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.TypedQuery;
import subsystem3.SubSystem3;
import entities.Transaction;
/**
 *
 * @author aco
 */
public class AllTransactionsRequest extends SubSystemRequest{
    public List<String> response=null;
    
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void processRequest(){
        TypedQuery<Transaction> query = SubSystem3.em.createNamedQuery("Transaction.findAll",Transaction.class);
        
        
        List<Transaction> transactions = query.getResultList();
        response=new LinkedList<String>();
        if (transactions.isEmpty()) {
            response.add("No transactions");
            sendReply();
            return;
        }
        for(Transaction i:transactions){
            String str ="";
            str+="id:" + i.getIdTransaction().toString();
            str+="|idOrder:"+i.getIdOrder().toString();
            str+="|totalPrice:"+Float.toString(i.getTotalPrice());
            str+="|time:"+i.getTime().toString();
            response.add(str);
        }
        sendReply();
    }
}
