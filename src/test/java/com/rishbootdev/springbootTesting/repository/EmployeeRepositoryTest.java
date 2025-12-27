package com.rishbootdev.springbootTesting.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*
    the return type of the java tests are always void and they do not
    return anything
     */
    @Test
    void getByEmail_whenEmailIsValid_thenReturnEmployee() {

    }


    @Test
    void getByEmail_whenEmail_isNotFound_thenReturnEmpty_EmployeeList(){

    }
}

/*
    By default it will use my mySql database but we will use
    the H2 database for the testing purposes and to connect it
    we have this annotation  ---> @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
 */