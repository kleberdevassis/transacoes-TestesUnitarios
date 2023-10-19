package com.UnitariosPicPay.UnitariosPicPay.repositories;

import com.UnitariosPicPay.UnitariosPicPay.DTOs.UserDTO;
import com.UnitariosPicPay.UnitariosPicPay.domain.users.User;
import com.UnitariosPicPay.UnitariosPicPay.domain.users.UserType;
import com.UnitariosPicPay.UnitariosPicPay.services.UserService;
import jakarta.persistence.EntityManager;
import org.hibernate.engine.spi.Managed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;


    @Test
    @DisplayName("if User will fonded")
    void findUserByDocumentCase1() {
        String document = "68655643456";
        UserDTO userDTO = new UserDTO("kleber","marga",document,
                "passou@gmail.com","345", new BigDecimal(10), UserType.COMMON);

        this.CreateUser(userDTO);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("if User will not fonded")
    void findUserByDocumentCase2() {
        String document = "68655643456";
        UserDTO userDTO = new UserDTO("kleber","marga",document,
                "passou@gmail.com","345", new BigDecimal(10), UserType.COMMON);

        this.CreateUser(userDTO);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();

    }
    public User CreateUser(UserDTO userDTO){
        User user = new User(userDTO);
        this.entityManager.persist(user);
        return user;
    }
}