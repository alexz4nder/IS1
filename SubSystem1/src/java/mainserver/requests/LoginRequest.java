/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import entities.Korisnik;
import subsystem1.SubSystem1;


/**
 *
 * @author aco
 */
public class LoginRequest extends SubSystemRequest{
    String username;
    String password;
    public Integer userId =-1;
    public Boolean isAdmin=false;
private static final long serialVersionUID = 1L;
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void processRequest() {
        //super.processRequest(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

        TypedQuery<Korisnik> query = SubSystem1.em.createNamedQuery("Korisnik.userLogin", Korisnik.class)
                .setParameter("username", username)
                .setParameter("password", password);
        Korisnik k;
        try {
            k = query.getSingleResult();
            this.userId=k.getIdUser();
            this.isAdmin=k.getIsAdmin();
        } catch (NoResultException e) {
        }
        
        
        
        SubSystem1.response.send(SubSystem1.sys1Response,
                SubSystem1.context.createObjectMessage(this));
    }
    
    
    
}
