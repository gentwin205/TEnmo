package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class TransferService {
    public String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    public TransferService( String url, AuthenticatedUser currentUser){
         this.currentUser = currentUser;
         baseUrl = url;
    }

    public Transfer[] listTransfer(){
        Transfer[] transfer = null;
       return null;

    }

    public User[] getUsers(){
        User[] user = null;
        try{
            user = restTemplate.exchange(baseUrl + "users", HttpMethod.GET, makeAuthEntity(), User[].class ).getBody();

        }catch(RestClientResponseException e ){
            System.out.println("Error getting users");
        }
        return user;
    }

    public void transferBucks(Transfer transfer, int senderId, int RecipientId) {
        try {


        } catch (RestClientResponseException e) {

        } catch (ResourceAccessException e) {

        }
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity a = new HttpEntity<>(headers);
        return a;
    }
}
