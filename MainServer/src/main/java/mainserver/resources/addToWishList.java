/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import mainserver.RequestHandler;
import mainserver.requests.AddToWishListRequest;

/**
 *
 * @author aco
 */
@Path("addToWishList")
public class addToWishList {
    @POST
    public List<String>AddToWishRequestList(@QueryParam("userId")int userId
            ,@QueryParam("itemName")String itemName){
        AddToWishListRequest request = new AddToWishListRequest(userId, itemName);
        AddToWishListRequest response =(AddToWishListRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        List<String> result = new LinkedList<>();
        result.add(response.response);
        
        return result;
    }
}
