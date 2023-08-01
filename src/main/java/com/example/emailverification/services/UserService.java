package com.example.emailverification.services;

import com.example.emailverification.exception.UserAlreadyExistException;
import com.example.emailverification.models.User;
import com.example.emailverification.registration.RegistrationRequest;
import com.example.emailverification.repos.UserRepo;
import com.example.emailverification.repos.UserServiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserServiceRepo {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {

        Optional<User> user = this.findByEmail(request.email());

        if(user.isPresent()){
            throw new UserAlreadyExistException(
                    "User with email" + request.email() + " is already exist");
        }

        var newUser = new User();
        newUser.setFirstName(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());

        return userRepo.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
