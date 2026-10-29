/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import entities.City;
import subsystem1.SubSystem1;
/**
 *
 * @author aco
 */
public class AddCityRequest extends SubSystemRequest{
    public String cityName;
    public String success="not processed";
    
    private static final long serialVersionUID = 3L;

    
    public AddCityRequest(String cityName){
        this.cityName=cityName;
    }

    @Override
    public void processRequest() {
        TypedQuery<City> query = SubSystem1.em.createNamedQuery("City.findByCityName", City.class)
                .setParameter("cityName", cityName);
        
        try {
            query.getSingleResult();
            success="City alredy exists";
        } catch (NoResultException e) {
            City newCity = new City();
            System.out.println(newCity.getIdCity());
            newCity.setCityName(cityName);
            System.out.println("mainserver.requests.AddCityRequest.processRequest() "+cityName);
            System.out.println("mainserver.requests.AddCityRequest.processRequest() newCity.name"+newCity.getCityName());
            SubSystem1.em.getTransaction().begin();
            SubSystem1.em.persist(newCity);
            SubSystem1.em.getTransaction().commit();
            success="City successfully added";
        }
        
        SubSystem1.response.send(SubSystem1.sys1Response,
                SubSystem1.context.createObjectMessage(this));
    }
    
    
}
