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
    private AuthenticatedUser user;

    public AccountService(String url, AuthenticatedUser user){
        baseUrl = url;
        this.user = user;
    }

        public BigDecimal newBalance(){
            BigDecimal balance = new BigDecimal(0);
            try {
                balance = restTemplate.exchange(baseUrl + "balance" + user.getUser().getId(), HttpMethod.GET, makeAuthEntity(), BigDecimal.class ).getBody();
            }catch(RestClientResponseException e){
                System.out.println("Error getting balance");
            }
            return balance;
        }
    private HttpEntity<Void> makeAuthEntity() {
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        return new HttpEntity<>(headers);
    }
}
