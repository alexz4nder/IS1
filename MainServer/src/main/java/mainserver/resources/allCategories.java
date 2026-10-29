/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import mainserver.RequestHandler;
import mainserver.requests.AllCategoriesRequest;
import java.util.List;
/**
 *
 * @author aco
 */
@Path("allCategories")
public class allCategories {
    
    @GET
    public List<String> allCategories(){
        AllCategoriesRequest request = new AllCategoriesRequest();
        AllCategoriesRequest response =(AllCategoriesRequest)RequestHandler.getInstance()
                .sendRequest(request,RequestHandler.getInstance().getSys2() );
        return response.response;
    }
}
