/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class CityIdAndMoneyRequest extends SubSystemRequest{
    String cityName;
    int userId;
    public int cityId=-1;
    public float money;
    
    public CityIdAndMoneyRequest(String cityName,int userId,float money){
        this.cityName=cityName;
        this.userId=userId;
        this.money=money;
    }
    private static final long serialVersionUID = 1L;

}
