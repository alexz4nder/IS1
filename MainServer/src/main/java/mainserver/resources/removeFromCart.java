/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import mainserver.RequestHandler;
import mainserver.requests.RemoveFromCartRequest;

/**
 *
 * @author aco
 */
@Path("removeFromCart")
public class removeFromCart {
    @PUT
    public List<String>removeFromCart(@QueryParam("userId")int userId
            ,@QueryParam("itemName")String itemName,@QueryParam("amount")int amount){
        RemoveFromCartRequest request = new RemoveFromCartRequest(userId, itemName, amount);
        RemoveFromCartRequest response =(RemoveFromCartRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        List<String> result = new LinkedList<>();
        result.add(response.response);
        
        return result;
    }
}
