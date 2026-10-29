/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.TypedQuery;
import entities.City;
import entities.Korisnik;
import javax.jms.ObjectMessage;
import javax.persistence.NoResultException;
import subsystem1.SubSystem1;
/**
 *
 * @author aco
 */
public class CityIdAndMoneyRequest extends SubSystemRequest{
    String cityName;
    int userId;
    public int cityId=-1;
    public float money;
    
    public CityIdAndMoneyRequest(String cityName,int userId,float money){
        this.cityName=cityName;
        this.userId=userId;
        this.money=money;
    }
    private static final long serialVersionUID = 1L;

    @Override
    public void processRequest(){
        TypedQuery<City> query = SubSystem1.em
                .createNamedQuery("City.findByCityName",City.class)
                .setParameter("cityName", cityName);
        try{
            City c = query.getSingleResult();
            cityId=c.getIdCity();
        }
        catch(NoResultException e){    
        }
        
        
        TypedQuery<Korisnik> userQuery = SubSystem1.em
                .createNamedQuery("Korisnik.findByIdUser",Korisnik.class)
                .setParameter("idUser", userId);
        try{
            Korisnik k =userQuery.getSingleResult();
            money=k.getMoney()-money;
            if (money>=0) {
                k.setMoney(money);
                SubSystem1.em.getTransaction().begin();
                SubSystem1.em.persist(k);
                SubSystem1.em.getTransaction().commit();
            }
        }
        catch(NoResultException e){
            money=-1;
        }
        
        
        
        SubSystem1.response.send(SubSystem1.Sys3ResponseQueue, 
                SubSystem1.context.createObjectMessage(this));
        
    }
    
}
