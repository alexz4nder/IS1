/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.requests;

/**
 *
 * @author aco
 */
public class EmptyCartRequest extends SubSystemRequest{
    int userId;
    
    public EmptyCartRequest(int userId){
        this.userId=userId;
    }
    private static final long serialVersionUID = 1L;

}
