package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferService {
    public String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    public User[] getUsers(){
        User[] user = null;
        try{
            user = restTemplate.exchange(baseUrl + "users",HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
        }catch(RestClientResponseException e ){
            System.out.println("Error getting users");
        }
        return user;
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity a = new HttpEntity<>(headers);
        return a;
    }
}
