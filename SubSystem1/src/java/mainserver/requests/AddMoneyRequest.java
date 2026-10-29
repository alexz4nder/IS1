/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import entities.Korisnik;
import subsystem1.SubSystem1;
/**
 *
 * @author aco
 */
public class AddMoneyRequest extends SubSystemRequest {
    public String username;
    public float money;
    
    public String response="not processed";
    
    public AddMoneyRequest (String username,float money){
        this.username=username;
        this.money=money;
    }
    
    private static final long serialVersionUID = 1L;

    @Override
    public void processRequest() {
        TypedQuery<Korisnik> query = SubSystem1.em.createNamedQuery("Korisnik.findByUsername",Korisnik.class)
                .setParameter("username", username);
        
        try {
            Korisnik k = query.getSingleResult();
            k.setMoney(k.getMoney()+money);
            SubSystem1.em.getTransaction().begin();
            SubSystem1.em.persist(k);
            SubSystem1.em.getTransaction().commit();
            response="successfully added money";

        } catch (NoResultException e) {
            response="username doesnt exist";
        }
        
        SubSystem1.response.send(SubSystem1.sys1Response,
                SubSystem1.context.createObjectMessage(this));
    }

    
}
