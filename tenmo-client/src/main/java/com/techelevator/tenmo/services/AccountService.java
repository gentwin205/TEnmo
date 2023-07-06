package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {
    private  String baseUrl;
    private  RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    public AccountService(String url, AuthenticatedUser currentUser){
        baseUrl = url;
        this.currentUser = currentUser;
    }

        public BigDecimal newBalance(){
            BigDecimal balance = new BigDecimal(0);
            try {
                balance = restTemplate.exchange(baseUrl + "balance/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), BigDecimal.class ).getBody();
                System.out.println("Your current account balance is: $" + balance);
            }catch(RestClientResponseException e){
                System.out.println("Error getting balance");
            }
            return balance;
        }
    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity a = new HttpEntity<>(headers);
        return a;
    }
}
