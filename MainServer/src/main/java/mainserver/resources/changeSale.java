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
import mainserver.requests.ChangeSaleRequest;

/**
 *
 * @author aco
 */
@Path("changeSale")
public class changeSale {
    @PUT
    public List<String> changePrice(@QueryParam("userId")int id
            ,@QueryParam("itemName")String name,@QueryParam("newSale")float newSale){
        
        ChangeSaleRequest request = new ChangeSaleRequest(id, name, newSale);
        ChangeSaleRequest response =(ChangeSaleRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        
        List<String> result = new LinkedList<>();
        result.add(response.response);
        
        return result;
    }
}
