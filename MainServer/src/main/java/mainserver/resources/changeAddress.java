/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.LinkedList;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import mainserver.RequestHandler;
import mainserver.requests.ChangeAddressRequest;
/**
 *
 * @author aco
 */

@Path("changeAddress")
public class changeAddress {
    @PUT
    public List<String> changeAddress(@QueryParam("username")String username,
            @QueryParam("newCity")String newCity,@QueryParam("newAddress")String newAddress){
        
        ChangeAddressRequest request = new ChangeAddressRequest(username, newCity, newAddress);
        ChangeAddressRequest response =(ChangeAddressRequest)RequestHandler.getInstance().sendRequest(request,
                RequestHandler.getInstance().getSys1());
        List<String> result = new LinkedList<>();
        result.add(response.respones);
        
        return result;
    }
}
