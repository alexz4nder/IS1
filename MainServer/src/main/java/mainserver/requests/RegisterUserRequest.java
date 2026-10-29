/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

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
}
