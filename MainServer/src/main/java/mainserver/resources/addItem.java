/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.LinkedList;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import mainserver.RequestHandler;
import mainserver.requests.AddItemRequest;
/**
 *
 * @author aco
 */
@Path("addItem")
public class addItem {
    @POST
    public List<String> addItem(@QueryParam("idUser")int id,@QueryParam("name")String name,
            @QueryParam("category")String category,@QueryParam("description")String description,
            @QueryParam("price")float price){
        
        AddItemRequest request= new AddItemRequest(id, name, category, description, price);
        AddItemRequest response = (AddItemRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        
        List<String> result = new LinkedList<>();
        result.add(response.response);
        
        return result;
    }
    
}
