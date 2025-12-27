package com.rishbootdev.springbootTesting.repository;

import com.rishbootdev.springbootTesting.dto.EmployeeDto;
import com.rishbootdev.springbootTesting.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        log.info("setting up a database connection and creating all the objects to test");
        employee = Employee.builder()
                .id(2L)
                .name("Rishabh")
                .email("rishbootdev@gmail.com")
                .salary(235000L)
                .build();

    }

    @AfterEach
    void tearDown() {
        log.info("Gracefully tearing down the process");
    }

    /*
    the return type of the java tests are always void and they do not
    return anything
     */
    @Test
    void test_getByEmail_whenEmailIsValid_thenReturnEmployee() {

        // Arrange or Given
        employeeRepository.save(employee);

        // Act , When
        List<Employee> employeeList=employeeRepository.getByEmail(employee.getEmail());

        // Assert , Then
        Assertions.assertThat(employeeList).isNotNull()
                        .isNotEmpty()
                                .contains(employee);

    }


    @Test
    void test_getByEmail_whenEmail_isNotFound_thenReturnEmpty_EmployeeList(){

        // Given
        String email="not present.123@gmail.com";

        // When
        List<Employee> employeeList = employeeRepository.getByEmail(email);

        // Then
        Assertions.assertThat(employeeList)
                .isNotNull()
                .isEmpty();
    }
}

/*
    By default, it will use my mySql database, but we will use
    the H2 database for the testing purposes and to connect it
    we have this annotation  ---> @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)

    since in this file we are only testing the springboot data persistence, and we are not
    looking for spinning up web server during the execution of the test cases then we will use
    another important annotation :

    @SpringDataTest
    This makes it ideal for testing repository methods and their interactions
    with the database;

    Since H2 database is used for the initial setup for the testing purposes but now
    since the developers don't rely on whether the operations are valid in the real database or not
    That's why instead of H2 to test the application as per the productions we configure a real
    testing database such as mysql or postgres.

    And that is possible with the use of Test containers
 */