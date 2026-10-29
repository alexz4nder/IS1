/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

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

    
}
