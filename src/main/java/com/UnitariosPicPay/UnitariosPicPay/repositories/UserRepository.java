package com.UnitariosPicPay.UnitariosPicPay.repositories;

import com.UnitariosPicPay.UnitariosPicPay.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByDocument(String document);

    Optional<User> findUserById(Long id);
}
