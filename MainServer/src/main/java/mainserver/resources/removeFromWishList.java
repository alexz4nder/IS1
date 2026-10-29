/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import mainserver.RequestHandler;
import mainserver.requests.RemoveFromWishListRequest;

/**
 *
 * @author aco
 */
@Path("removeFromWishList")
public class removeFromWishList {
    @DELETE
    public List<String>RemoveFromWishListRequest(@QueryParam("userId")int userId
            ,@QueryParam("itemName")String itemName){
        RemoveFromWishListRequest request = new RemoveFromWishListRequest(userId, itemName);
        RemoveFromWishListRequest response =(RemoveFromWishListRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        List<String> result = new LinkedList<>();
        result.add(response.response);
        
        return result;
    }
}
