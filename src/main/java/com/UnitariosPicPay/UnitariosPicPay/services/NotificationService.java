package com.UnitariosPicPay.UnitariosPicPay.services;

import com.UnitariosPicPay.UnitariosPicPay.DTOs.NotificationDTO;
import com.UnitariosPicPay.UnitariosPicPay.domain.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NotificationService {

    RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {

        String email = user.getEmail();
        NotificationDTO notificationDTO = new NotificationDTO(email, message);

      //  ResponseEntity<String> validEmail = this.restTemplate.postForEntity("monckyemail",notificationDTO,String.class);

       // if(!(validEmail.getStatusCode() == HttpStatus.OK)){
        //    throw new Exception("message not sent");
        //}
    }
}
