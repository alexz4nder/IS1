/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import entities.City;
import subsystem1.SubSystem1;


/**
 *
 * @author aco
 */
public class AllCitiesRequest extends SubSystemRequest{
        List<String> result;
private static final long serialVersionUID = 2L;

    @Override
    public void processRequest() {
        TypedQuery<City> query = SubSystem1.em.createNamedQuery("City.findAll", City.class);
               
        List<City> cities = query.getResultList();
        result = new LinkedList<>();
        for(City c:cities){
            result.add("name:"+c.getCityName()+" id:"+c.getIdCity().toString());
        }
        
        SubSystem1.response.send(SubSystem1.sys1Response,
                SubSystem1.context.createObjectMessage(this));

    }

        
}
