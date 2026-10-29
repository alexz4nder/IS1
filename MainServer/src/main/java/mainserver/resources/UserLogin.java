/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import mainserver.RequestHandler;
import mainserver.requests.SubSystemRequest;
import mainserver.requests.LoginRequest;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Map;
import java.util.Hashtable;
/**
 *
 * @author aco
 */

@Path("userLogin")
public class UserLogin {
    

    @GET
    @Produces
    public Map<String,String> userLogin(@QueryParam("username") String username,@QueryParam("password") String password){
        
        LoginRequest loRequest = new LoginRequest(username,password);
       
        LoginRequest response =(LoginRequest) RequestHandler.getInstance().sendRequest(loRequest,
                RequestHandler.getInstance().getSys1());
        
        Map<String,String> res = new Hashtable<>();
        res.put("id", response.userId.toString());
        res.put("isAdmin", response.isAdmin.toString());
        
        return res;
        //return response;
    }
    
}
