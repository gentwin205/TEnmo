package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferPackage;
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

    public Transfer[] listTransfers(int id){
        Transfer[] transfer = null;

        try {
            transfer = restTemplate.exchange(baseUrl + "transfers/" + id, HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
        } catch (RestClientResponseException e) {
            System.out.println("Error getting transfers.");
        }

        return transfer;
    }

    public User[] getUsers(){
        User[] user = null;
        try{
            user = restTemplate.exchange(baseUrl + "users", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();

        }catch(RestClientResponseException e ){
            System.out.println("Error getting users");
        }
        return user;
    }

    public Transfer getTransferById(int id) {
        Transfer t = new Transfer();

        try {
            t = restTemplate.exchange(baseUrl + "transfer/" + id, HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
        } catch (RestClientResponseException e) {
            System.out.println("Error getting transfer object");
        }

        return t;
    }

    public TransferPackage transferBucks(int senderId, int recipientId, BigDecimal amountGiven) {
        TransferPackage tp = new TransferPackage(senderId, recipientId, amountGiven);
        HttpEntity<TransferPackage> entity = makeAuthEntity(tp);
        TransferPackage returnedPackage = null;

        try {
            returnedPackage = restTemplate.postForObject(baseUrl + "transfers", entity, TransferPackage.class);

        } catch (RestClientResponseException e) {
            System.out.println(e.getMessage());
        } catch (ResourceAccessException e) {
            System.out.println("No Response.");
        }

        return returnedPackage;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity a = new HttpEntity<>(headers);
        return a;
    }

    private HttpEntity<TransferPackage> makeAuthEntity(TransferPackage tp) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<TransferPackage> a = new HttpEntity<>(tp, headers);
        return a;
    }
}
