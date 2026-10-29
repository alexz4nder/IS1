/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import mainserver.RequestHandler;
import mainserver.requests.AllItemsRequest;
/**
 *
 * @author aco
 */
@Path("allItems")
public class allItems {
    @GET
    public List<String>allItems(@QueryParam("userId")int userId){
        AllItemsRequest request = new AllItemsRequest(userId);
        AllItemsRequest response =(AllItemsRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        return response.response;
    }
}
