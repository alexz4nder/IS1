/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package subsystem2;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mainserver.requests.SubSystemRequest;

/**
 *
 * @author aco
 */
public class SubSystem2 {

    @Resource(lookup ="jms/__defaultConnectionFactory")
    private static ConnectionFactory conFac;
    
    @Resource(lookup="Sys2Requests")
    private static Queue sys2Requests ;
    
    @Resource(lookup="SubSystemResponse")
    public static Queue sysResponse ;
    
    @Resource(lookup ="Sys3Response")
    public static Queue Sys3ResponseQueue;
    
    public static EntityManager em;
    public static JMSProducer response;
    public static JMSContext context;
    
    public static void main(String[] args) throws JMSException {
System.out.println("============= starting SubSystem2 ===============");
        if (conFac==null) {
            System.out.println("==============conFac is null===================");
     
        }
        if (sys2Requests==null) {
            System.out.println("=================sys2Request is null====================");
    
        }
        context = conFac.createContext();
        JMSConsumer consumer = context.createConsumer(sys2Requests);
        response = context.createProducer();
        System.out.println("=================sys2Request initialized====================");
        
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SubSystem2PU");
        em = emf.createEntityManager();
        
        if (em==null) {
            System.out.println("============== em je null =================");
        }
        else{
            System.out.println("============== em nije null =================");
        }
        
        while (true) {            
            Message msg = consumer.receive();
            System.out.println("===================SubSystem2 msg recieved====================");
            if (msg instanceof ObjectMessage) {
                ObjectMessage objMsg = (ObjectMessage)msg;
                System.out.println("object type: "+objMsg.getObject().getClass());
                SubSystemRequest request = (SubSystemRequest)objMsg.getObject();
                request.processRequest();
            }
        }
        
    }
    
}
