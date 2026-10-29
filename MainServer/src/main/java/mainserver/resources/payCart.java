/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.LinkedList;
import mainserver.RequestHandler;
import mainserver.requests.PayCartRequest;
/**
 *
 * @author aco
 */
@Path("payCart")
public class payCart {
    @POST
    public List<String> payCart(@QueryParam("userId")int userId,
            @QueryParam("cityName")String cityName,
            @QueryParam("address")String address){
        
        PayCartRequest request = new PayCartRequest(userId, cityName, address);
        PayCartRequest response =(PayCartRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys3());
        
        List<String> result = new LinkedList<String>();
        result.add(response.response);
        
        return result;
    }
}
