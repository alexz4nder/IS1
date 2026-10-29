/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class PayCartRequest extends SubSystemRequest {
    int userId;
    String cityName;
    String address;
    
    public String response = "not processed";
    
    public PayCartRequest(int userId,String cityName,String address){
        this.cityName=cityName;
        this.userId=userId;
        this.address=address;
    }
    
    private static final long serialVersionUID = 1L;

}
