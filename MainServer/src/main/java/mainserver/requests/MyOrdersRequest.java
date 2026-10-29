/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

import java.util.List;

/**
 *
 * @author aco
 */
public class MyOrdersRequest extends SubSystemRequest{
    int userId;
    
    public List<String> response;
    public MyOrdersRequest(int userId){
        this.userId=userId;
    }
    private static final long serialVersionUID = 1L;
}
