/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver;

import mainserver.requests.SubSystemRequest;
import com.sun.tools.javac.util.Pair;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author aco
 */
public class RequestHandler extends Thread{
    static Integer requestId=0;
    static synchronized int getNewId(){
        requestId+=1;
        return requestId;
    }
    
    //@Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory conFac;
    
    //@Resource(lookup="Sys1Requests")
    private Queue sys1Requests;
    public Queue getSys1(){return sys1Requests;}
    private Queue sys2Requests;
    public Queue getSys2(){return sys2Requests;}
    private Queue sys3Requests;
    public Queue getSys3(){return sys3Requests;}
    private Queue subSystemResponse;
    
    static Hashtable<Integer, SubSystemRequest> subSystemRequests= new Hashtable<>();

    private RequestHandler() {
        try {
            InitialContext initialContext = new InitialContext();
            conFac = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
            sys1Requests = (Queue) initialContext.lookup("Sys1Requests");
            sys2Requests = (Queue) initialContext.lookup("Sys2Requests");
            sys3Requests = (Queue) initialContext.lookup("Sys3Requests");
            subSystemResponse = (Queue) initialContext.lookup("SubSystemResponse");
        } catch (NamingException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
        
        System.out.println("==============================================RequestHandler Constructor===================================");

    }
    
    private static RequestHandler instance=null;
    public static RequestHandler getInstance(){
        if (instance==null) {
            instance= new RequestHandler();
            instance.start();
        }
        return instance;
    }

    @Override
    public void run() {
        JMSContext context = conFac.createContext();
        JMSConsumer consumer = context.createConsumer(subSystemResponse);
        
        while(consumer.receiveNoWait()!=null);
        System.out.println("====================================================Request handler running====================================================");

        
        while (true) {            
            Message msg = consumer.receive();
            System.out.println("=========Main server recieved message=============");

            if (msg instanceof ObjectMessage) {
                try {
                    ObjectMessage oMsg =(ObjectMessage)msg;
                    System.out.println(oMsg.getObject().getClass());
                    SubSystemRequest response = (SubSystemRequest)oMsg.getObject();
                    SubSystemRequest old_request = subSystemRequests.get(response.getId());
                    subSystemRequests.replace(response.getId(), response);
                    if (old_request==null) {
                        continue;
                    }
                    old_request.signalArrival();
                    System.out.println("=========Signaled semaphore=============");
                } catch (JMSException ex) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }
        //super.run(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
    public SubSystemRequest sendRequest(SubSystemRequest request,Destination dest){
        System.out.println("===================================================send request ===================================================================");
        System.out.println(request.getClass().toString());
        
        Integer id = getNewId();
        request.setId(id);
        subSystemRequests.put(id,request);
        
        if(conFac == null){
            System.out.print("===================================================conFac null ===================================================================");
        }
        if(sys1Requests == null){
            System.out.print("===================================================sys1Requests null ===================================================================");
        }
        
        JMSContext context = conFac.createContext();
        JMSProducer producer = context.createProducer();
        
        System.out.print("===================================================created context and producer ===================================================================");

        
        ObjectMessage msg = context.createObjectMessage(request);
        producer.send(dest, msg);
        
        request.waitResponse();
        System.out.println("====================sendRequesr ended wait=====================");
        SubSystemRequest response = subSystemRequests.get(id);
        subSystemRequests.remove(id);
        context.close();
        return response;
    }
}
