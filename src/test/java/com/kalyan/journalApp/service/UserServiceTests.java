package com.kalyan.journalApp.service;

import com.kalyan.journalApp.entity.JournalEntry;
import com.kalyan.journalApp.entity.User;
import com.kalyan.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }






    @Disabled
    @ParameterizedTest
    @CsvSource( {
            "1,1,2",
            "2,10,12",
            "1,3,3"
    }
    )
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b);
    }
}
