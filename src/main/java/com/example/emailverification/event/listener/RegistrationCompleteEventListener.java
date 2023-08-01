package com.example.emailverification.event.listener;

import com.example.emailverification.event.RegistrationCompleteEvent;
import com.example.emailverification.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. newly registered user
        User theUser = event.getUser();

        // 2.create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();


        // 3.save verification token for user



        // 4.build the verification url to send the user
        String url = event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;

        // 5.sent a email
        log.info("click the link to verify your registration:", url);
    }
}
