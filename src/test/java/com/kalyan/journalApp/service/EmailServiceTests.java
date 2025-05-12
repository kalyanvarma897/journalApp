package com.kalyan.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    public void testSendMail(){
        emailService.sendEmail("yaswanthvarma897@gmail.com",
                "Testing Java Mail Sender",
                "motu how are you ");


    }
}
