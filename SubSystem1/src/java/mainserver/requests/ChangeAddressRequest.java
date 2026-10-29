/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import entities.Korisnik;
import subsystem1.SubSystem1;
import entities.City;
/**
 *
 * @author aco
 */
public class ChangeAddressRequest extends SubSystemRequest{
    public String username;
    public String newCity;
    public String newAddress;
    
    public String respones = "not processed";
    
    public ChangeAddressRequest(String username,String newCity,String newAddress){
        this.username=username;
        this.newCity=newCity;
        this.newAddress=newAddress;
    }
    private static final long serialVersionUID = 1L;

    @Override
    public void processRequest() {
        TypedQuery<Korisnik> findUser=SubSystem1.em.createNamedQuery("Korisnik.findByUsername",Korisnik.class)
                .setParameter("username", username);
        Korisnik k;
        try {
            k=findUser.getSingleResult();
        } catch (NoResultException e) {
            respones="user doesnt exist";
            sendReply();
            return;
        }
        
        TypedQuery<City> findCity = SubSystem1.em.createNamedQuery("City.findByCityName",City.class)
                .setParameter("cityName", newCity);
        City c;
        try {
            c=findCity.getSingleResult();
        } catch (NoResultException e) {
            respones="city doesnt exist";
            sendReply();
            return;
        }

        k.setIdCity(c);
        k.setAdress(newAddress);
        SubSystem1.em.getTransaction().begin();
        SubSystem1.em.persist(k);
        SubSystem1.em.getTransaction().commit();
        respones="Successfully changed address";
        sendReply();
    }

    
    
}
