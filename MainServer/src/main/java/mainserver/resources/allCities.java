/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import mainserver.RequestHandler;
import mainserver.requests.AllCitiesRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 *
 * @author aco
 */

@Path("allCities")
public class allCities {
    
    
    @GET
    public List<String> getAllCities(){
        System.out.println("==============================================allCities===================================");
        AllCitiesRequest request = new AllCitiesRequest();
        AllCitiesRequest response =(AllCitiesRequest) RequestHandler.getInstance().sendRequest(request,
                RequestHandler.getInstance().getSys1());
        
        
        
        
        return response.result;
    }
    
}
