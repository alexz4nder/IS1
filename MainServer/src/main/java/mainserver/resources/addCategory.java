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
import mainserver.requests.AddCategoryRequest;
/**
 *
 * @author aco
 */
@Path("addCategory")
public class addCategory {
    @POST
    public List<String> addCategory(@QueryParam("name")String name,@QueryParam("parentName")String parentName){
        AddCategoryRequest request = new AddCategoryRequest(name,parentName);
        AddCategoryRequest response =(AddCategoryRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys2());
        
        List<String> result = new LinkedList<>();
        result.add(response.response);
        
        return result;
    }
}
