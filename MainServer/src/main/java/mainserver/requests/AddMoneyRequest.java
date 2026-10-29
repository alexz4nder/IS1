/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

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

    
}
