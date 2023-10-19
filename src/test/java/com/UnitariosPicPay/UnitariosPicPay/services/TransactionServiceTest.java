package com.UnitariosPicPay.UnitariosPicPay.services;

import com.UnitariosPicPay.UnitariosPicPay.DTOs.TransactionDTO;
import com.UnitariosPicPay.UnitariosPicPay.domain.users.User;
import com.UnitariosPicPay.UnitariosPicPay.domain.users.UserType;
import com.UnitariosPicPay.UnitariosPicPay.repositories.TransactionsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    UserService userService;

    @Mock
    TransactionsRepository transactionsRepository;

    @Mock
    AuthorizationServices authorizationServices;

    @Mock
    NotificationService notificationService;
    @Autowired
    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("should create transaction successfully when everything is ok")
    void createTransactioncase1() throws Exception {
        User sender = new User(1L, "kleber", "margarido", "30549403456", "passou@gmail.com", "keid833k", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Bob", "Alisons", "24234234523", "bob@gmail.com", "2342vd", new BigDecimal(10), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authorizationServices.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(1L, 2L, new BigDecimal(10));
        transactionService.createTransaction(request);

        verify(transactionsRepository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(20));
        verify(userService, times(1)).saveUser(receiver);

        verify(notificationService, times(1)).sendNotification(sender, "transaction done successfully");
        verify(notificationService, times(1)).sendNotification(receiver, "transaction received successfully");

    }

    @Test
    @DisplayName("should throw exception when exception is not allowed")
    void createTransactioncase2() throws Exception {

        User sender = new User(1L, "kleber", "margarido", "30549403456", "passou@gmail.com", "keid833k", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Bob", "Alisons", "24234234523", "bob@gmail.com", "2342vd", new BigDecimal(10), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authorizationServices.authorizeTransaction(any(), any())).thenReturn(false);

        Exception thrown =Assertions.assertThrows(Exception.class,()-> {
            TransactionDTO request = new TransactionDTO(1L,2L,new BigDecimal(10));
            transactionService.createTransaction(request);
        });

        Assertions.assertEquals("transaction not authorized",thrown.getMessage());

    }
}