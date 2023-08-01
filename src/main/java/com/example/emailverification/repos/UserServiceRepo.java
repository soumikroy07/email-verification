package com.example.emailverification.repos;

import com.example.emailverification.models.User;
import com.example.emailverification.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface UserServiceRepo {
    
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    
}
