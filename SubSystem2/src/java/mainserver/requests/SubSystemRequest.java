/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import java.io.Serializable;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import subsystem2.SubSystem2;



/**
 *
 * @author aco
 */
public class SubSystemRequest implements Serializable{
    private static final long serialVersionUID = 1L;

    Semaphore sem = new Semaphore(0);
    int id = -1;
    public void waitResponse(){
        try {
            sem.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(SubSystemRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void signalArrival(){
        if (sem==null) {
            System.out.println("===================semaphore null===================");
        }
        sem.release();
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    
    public void processRequest(){
    }
    
    public void sendReply(){
        SubSystem2.response.send(SubSystem2.sysResponse,
                SubSystem2.context.createObjectMessage(this));

    }
}
