/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class AddCategoryRequest extends SubSystemRequest{
    public String name;
    public String parentName;
    
    public String response="not processed";
    
    public AddCategoryRequest(String name,String parentName){
        this.name=name;
        this.parentName=parentName;
    }
    private static final long serialVersionUID = 1L;
}
