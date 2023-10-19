package com.UnitariosPicPay.UnitariosPicPay.services;

import com.UnitariosPicPay.UnitariosPicPay.DTOs.UserDTO;
import com.UnitariosPicPay.UnitariosPicPay.domain.users.User;
import com.UnitariosPicPay.UnitariosPicPay.domain.users.UserType;
import com.UnitariosPicPay.UnitariosPicPay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void validateUserTransaction(User user, BigDecimal value) throws Exception {

        if (user.getUserType() == UserType.MERCHANT) {
            throw new Exception("Merchant is not authorized to transactions");
        }
        if (user.getBalance().compareTo(value) < 0) {
            throw new Exception("balance is not sufficient to transaction");
        }
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(()-> new Exception("User not found"));
    }


}
