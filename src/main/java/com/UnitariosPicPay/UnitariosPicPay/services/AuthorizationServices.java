package com.UnitariosPicPay.UnitariosPicPay.services;

import com.UnitariosPicPay.UnitariosPicPay.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AuthorizationServices {
    @Autowired
    RestTemplate restTemplate;

    public boolean authorizeTransaction(User user, BigDecimal valude){

        ResponseEntity<Map> responseTransaction = this.restTemplate.getForEntity("monckytransaction", Map.class);

        try {
            if(responseTransaction.getStatusCode() == HttpStatus.OK){
                String message = (String) responseTransaction.getBody().get("message");
                return "Autorizado".equalsIgnoreCase(message);
            }else return false;
        } catch (Exception e) {
            return true;
        }

    }


}
