/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import javax.persistence.TypedQuery;



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
    
    
    
}
