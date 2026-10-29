/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.LinkedList;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import javax.ws.rs.PUT;
import mainserver.RequestHandler;
import mainserver.requests.AddToCartRequest;
/**
 *
 * @author aco
 */
@Path("addToCart")
public class addToCart {
    @PUT
    public List<String>addToCart(@QueryParam("userId")int userId
            ,@QueryParam("itemName")String itemName,@QueryParam("amount")int amount){
        AddToCartRequest request = new AddToCartRequest(userId, itemName, amount);
        AddToCartRequest response =(AddToCartRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        List<String> result = new LinkedList<>();
        result.add(response.response);
        
        return result;
    }
}
