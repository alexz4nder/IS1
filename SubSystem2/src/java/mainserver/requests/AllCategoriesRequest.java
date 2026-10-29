/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import java.util.List;
import entities.Category;
import java.util.LinkedList;
import javax.persistence.TypedQuery;
import subsystem2.SubSystem2;
/**
 *
 * @author aco
 */
public class AllCategoriesRequest extends SubSystemRequest{
    public List<String> response;
    private static final long serialVersionUID = 1L;

    @Override
    public void processRequest() {
        TypedQuery<Category> query = SubSystem2.em.createNamedQuery("Category.findAll",Category.class);
        List<Category> allCategories=query.getResultList();
        
        response=new LinkedList<>();
        for(Category c:allCategories){
            String str ="IdCat:" +c.getIdCat().toString()+"|name:"+c.getName();
            if (c.getIdParent()!=null) {
                str+="|parent:"+c.getIdParent().getName();
            }
            response.add(str);
        }
        
        sendReply();
    }
    
}
