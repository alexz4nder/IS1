/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.LinkedList;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import mainserver.RequestHandler;
import mainserver.requests.AddCityRequest;
import java.util.List;

/**
 *
 * @author aco
 */
@Path("addCity")
public class addCity {
    @POST
    public List<String> addCity(@QueryParam("cityName") String cityName){
        System.out.println("===============addCity===================");
        System.out.println("mainserver.resources.addCity.addCity()  cityName = "+cityName);
        AddCityRequest request = new AddCityRequest(cityName);
        AddCityRequest response = (AddCityRequest)RequestHandler.getInstance().sendRequest(request,
                RequestHandler.getInstance().getSys1());
        
        List<String> result = new LinkedList<>();
        result.add(response.success);
        return result;
    }
}
