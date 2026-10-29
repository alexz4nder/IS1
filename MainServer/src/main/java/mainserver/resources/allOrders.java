/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import mainserver.RequestHandler;
import mainserver.requests.AllOrdersRequest;

/**
 *
 * @author aco
 */
@Path("allOrders")
public class allOrders {
    @GET
    public List<String> allIOrders(){
        System.out.println("==============allOrders===============");
        AllOrdersRequest request = new AllOrdersRequest();
        AllOrdersRequest response =(AllOrdersRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys3());
        return response.response;
    }
}
