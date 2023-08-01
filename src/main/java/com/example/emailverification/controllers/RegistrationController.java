package com.example.emailverification.controllers;

import com.example.emailverification.event.RegistrationCompleteEvent;
import com.example.emailverification.models.User;
import com.example.emailverification.registration.RegistrationRequest;
import com.example.emailverification.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/register")
public class RegistrationController {

    private final UserService userService;
    private ApplicationEventPublisher publisher;

    @PostMapping(path = "/")
    public String registerUser(RegistrationRequest registrationRequest, final HttpServletRequest request){
        User user = this.userService.registerUser(registrationRequest);

        //publish registration event

        publisher.publishEvent(new RegistrationCompleteEvent(user, appicationUrl(request)));
        return "registration success..! check you email for registration confirmation";
    }

    public String appicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
