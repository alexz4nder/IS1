/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import mainserver.RequestHandler;
import mainserver.requests.AllUsersRequest;
/**
 *
 * @author aco
 */
@Path("allUsers")
public class allUsers {
    @GET
    public List<String> allUsers(){
        AllUsersRequest request = new AllUsersRequest();
        AllUsersRequest response = (AllUsersRequest)RequestHandler.getInstance().sendRequest(request,
                RequestHandler.getInstance().getSys1());
        
        return response.response;
    }
}
