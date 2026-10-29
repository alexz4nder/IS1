/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class AddCityRequest extends SubSystemRequest{
    public String cityName;
    public String success="not processed";
    
    private static final long serialVersionUID = 3L;

    
    public AddCityRequest(String cityName){
        this.cityName=cityName;
    }
}
