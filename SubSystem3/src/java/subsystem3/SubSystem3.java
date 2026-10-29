/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package subsystem3;

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
public class SubSystem3 {

    @Resource(lookup ="jms/__defaultConnectionFactory")
    private static ConnectionFactory conFac;
    
    @Resource(lookup="Sys3Requests")
    private static Queue sys3Requests;
    
    @Resource(lookup="SubSystemResponse")
    public static Queue sysResponse;
    
    @Resource(lookup="Sys3Response")
    public static Queue sys3ResponseQueue;
    public static JMSConsumer sys3Response;
   
    @Resource(lookup="Sys1Requests")
    public static Queue sys1Requests ;
    @Resource(lookup="Sys2Requests")
    public static Queue sys2Requests ;
    
    public static EntityManager em;
    public static JMSProducer response;
    public static JMSContext context;
    
    public static void main(String[] args) throws JMSException {
System.out.println("============= starting SubSystem3 ===============");
        if (conFac==null) {
            System.out.println("==============conFac is null===================");
     
        }
        if (sys3Requests==null) {
            System.out.println("=================sys3Request is null====================");
    
        }
        context = conFac.createContext();
        JMSConsumer consumer = context.createConsumer(sys3Requests);
        sys3Response=context.createConsumer(sys3ResponseQueue);
        response = context.createProducer();
        System.out.println("=================sys3Request initialized====================");
        
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SubSystem3PU");
        em = emf.createEntityManager();
        
        if (em==null) {
            System.out.println("============== em je null =================");
        }
        else{
            System.out.println("============== em nije null =================");
        }
        
        while (true) {            
            Message msg = consumer.receive();
            System.out.println("===================SubSystem3 msg recieved====================");
            if (msg instanceof ObjectMessage) {
                ObjectMessage objMsg = (ObjectMessage)msg;
                System.out.println("object type: "+objMsg.getObject().getClass());
                SubSystemRequest request = (SubSystemRequest)objMsg.getObject();
                request.processRequest();
            }
        }
        
    }
    
}
