/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import mainserver.RequestHandler;
import mainserver.requests.AllItemsRequest;
import mainserver.requests.AllTransactionsRequest;

/**
 *
 * @author aco
 */
@Path("allTransactions")
public class allTransactions {
    @GET
    public List<String> allTransactions(){
        AllTransactionsRequest request = new AllTransactionsRequest();
        AllTransactionsRequest response =(AllTransactionsRequest)RequestHandler.getInstance()
                .sendRequest(request, RequestHandler.getInstance().getSys3());
        
        return response.response;
    }
}
