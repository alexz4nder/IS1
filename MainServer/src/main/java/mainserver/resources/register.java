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
import mainserver.requests.RegisterUserRequest;
import java.util.List;
/**
 *
 * @author aco
 */
@Path("register")
public class register {
    @POST
    public List<String> register(@QueryParam("username")String username,@QueryParam("password")String password,
            @QueryParam("name")String name,@QueryParam("surname")String surname,
            @QueryParam("city")String city,@QueryParam("address")String address){
        RegisterUserRequest request = new RegisterUserRequest(username, password,name,surname,city,address);
        RegisterUserRequest response = (RegisterUserRequest)RequestHandler.getInstance().sendRequest(request,
                RequestHandler.getInstance().getSys1());
        List<String> result = new LinkedList<>();
        result.add(response.response);
        
        return result;
    }
}
