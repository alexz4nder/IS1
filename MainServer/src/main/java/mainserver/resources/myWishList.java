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
import mainserver.requests.MyWishListRequest;

/**
 *
 * @author aco
 */
@Path("myWishList")
public class myWishList {
    @GET
    public List<String>myWishList(@QueryParam("userId")int userId){
        MyWishListRequest request = new MyWishListRequest(userId);
        MyWishListRequest response =(MyWishListRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        return response.response;
    }
}
