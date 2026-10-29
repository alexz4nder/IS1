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
public class RegisterUserRequest extends SubSystemRequest{
    public String username;
    public String password;
    public String name;
    public String surname;
    public String city;
    public String address;
    
    public String response="Not processed";
    
    private static final long serialVersionUID = 1L;

    public RegisterUserRequest(String username,String password,
            String name,String surname,String city,String address){
        this.username=username;
        this.password=password;
        this.name=name;
        this.surname = surname;
        this.city = city;
        this.address = address;
    }

    @Override
    public void processRequest() {        
        try {
            TypedQuery<Korisnik> checkIfUsernameExists = SubSystem1.em.createNamedQuery("Korisnik.findByUsername",Korisnik.class)
                .setParameter("username", username);
            checkIfUsernameExists.getSingleResult();
            response ="username alredy exists";
            SubSystem1.response.send(SubSystem1.sys1Response,
                SubSystem1.context.createObjectMessage(this));
            return;
            
        } catch (NoResultException e) {
        }
        
        City cityId;
        
        try {
            TypedQuery<City> getCityId = SubSystem1.em.createNamedQuery("City.findByCityName",City.class)
                    .setParameter("cityName", city);
            cityId=getCityId.getSingleResult();
        } catch (NoResultException e) {
            response ="city doesnt exists";
            SubSystem1.response.send(SubSystem1.sys1Response,
                SubSystem1.context.createObjectMessage(this));
            return;
        }
        
        Korisnik newK = new Korisnik();
        newK.setUsername(username);
        newK.setPassword(password);
        newK.setSurname(surname);
        newK.setName(name);
        newK.setSurname(surname);
        newK.setIdCity(cityId);
        newK.setAdress(address);
        newK.setMoney(0.0f);
        newK.setIsAdmin(false);
        
        SubSystem1.em.getTransaction().begin();
        SubSystem1.em.persist(newK);
        SubSystem1.em.getTransaction().commit();

        response="successfully registered";
        SubSystem1.response.send(SubSystem1.sys1Response,
                SubSystem1.context.createObjectMessage(this));
    }
    
    
}
