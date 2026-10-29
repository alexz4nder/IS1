/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userapp.Commands;

import mainserver.requests.LoginRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author aco
 */
public interface HttpRequests {
    @GET("userLogin")
    public Call<LoginRequest> login(@Query("username") String username,@Query("password") String password);
    
}
