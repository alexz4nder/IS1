/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.TypedQuery;
import entities.Korisnik;
import subsystem1.SubSystem1;
/**
 *
 * @author aco
 */
public class AllUsersRequest extends SubSystemRequest{
    List<String> response;
    private static final long serialVersionUID = 1L;

    @Override
    public void processRequest() {
        TypedQuery<Korisnik> query = SubSystem1.em.createNamedQuery("Korisnik.findAll",Korisnik.class);
        List<Korisnik> korisnici = query.getResultList();
        
        response = new LinkedList<>();
        for(Korisnik k:korisnici){
            String korisnikStr = "";
            korisnikStr+="id:"+k.getIdUser().toString();
            korisnikStr+="|username:"+k.getUsername();
            korisnikStr+="|name:"+k.getName();
            korisnikStr+="|surname:"+k.getSurname();
            korisnikStr+="|city:"+k.getIdCity().getCityName();
            korisnikStr+="|address:"+k.getAdress();
            korisnikStr+="|money:"+Float.toString(k.getMoney());
            korisnikStr+="|isAdmin:"+Boolean.toString(k.getIsAdmin());

            response.add(korisnikStr);
        }
        
        sendReply();
    }

    
}
