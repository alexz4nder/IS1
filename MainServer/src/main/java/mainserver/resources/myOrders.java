/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import mainserver.RequestHandler; 
import mainserver.requests.MyOrdersRequest;

/**
 *
 * @author aco
 */

@Path("myOrders")
public class myOrders {
    @GET
    public List<String>myOrders(@QueryParam("userId")int userId){
        MyOrdersRequest request = new MyOrdersRequest(userId);
        MyOrdersRequest response =(MyOrdersRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys3());
        return response.response;
    }
}
