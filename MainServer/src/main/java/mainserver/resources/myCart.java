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
import mainserver.requests.MyCartRequest;

/**
 *
 * @author aco
 */

@Path("myCart")
public class myCart {
    @GET
    public List<String>myCart(@QueryParam("userId")int userId){
        MyCartRequest request = new MyCartRequest(userId);
        MyCartRequest response =(MyCartRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        return response.response;
    }
}
