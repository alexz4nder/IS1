/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.LinkedList;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;
import javax.ws.rs.QueryParam;
import mainserver.RequestHandler;
import mainserver.requests.AddMoneyRequest;

/**
 *
 * @author aco
 */
@Path("addMoney")
public class addMoney {
    @PUT
    public List<String> addMoney(@QueryParam("username")String username,@QueryParam("money")float money){
        AddMoneyRequest request = new AddMoneyRequest(username, money);
        AddMoneyRequest respones =(AddMoneyRequest)RequestHandler.getInstance().sendRequest(request,
                RequestHandler.getInstance().getSys1());
        List<String> result =new LinkedList<>();
        result.add(respones.response);
        
        return result;
    }
        
}
