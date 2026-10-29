/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;
import java.util.List;
import entities.*;
import java.util.LinkedList;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import subsystem2.SubSystem2;
/**
 *
 * @author aco
 */
public class AllItemsRequest extends SubSystemRequest{
    int userId;
    
    public List<String> response;
    public AllItemsRequest(int userId){
        this.userId=userId;
    }
    private static final long serialVersionUID = 1L;

    @Override
    public void processRequest() {
        TypedQuery<Item> query = SubSystem2.em.createNamedQuery("Item.findByIdUser",Item.class)
                .setParameter("idUser", userId);
        
        
        List<Item> items = query.getResultList();
        response=new LinkedList<>();
        if (items.isEmpty()) {
            response.add("User has no items");
            sendReply();
            return;
        }
        for(Item i:items){
            String str ="";
            str+="id:" + i.getIdItem().toString();
            str+="|name:"+i.getName();
            str+="|category:"+i.getIdCat().getName();
            str+="|description:"+i.getDescription();
            str+="|price:"+Float.toString(i.getPrice());
            str+="|sale:"+Float.toString(i.getSale()*100);
            response.add(str);
        }
        sendReply();
        
    }

    
}
