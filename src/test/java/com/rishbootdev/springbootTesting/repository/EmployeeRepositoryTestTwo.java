package com.rishbootdev.springbootTesting.repository;


import com.rishbootdev.springbootTesting.dto.EmployeeDto;
import com.rishbootdev.springbootTesting.entity.Employee;
import com.rishbootdev.springbootTesting.testConfiguration.TestContainerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import java.util.List;


@Slf4j
@Import(TestContainerConfiguration.class)
//@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EmployeeRepositoryTestTwo {

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
        List<Employee> employeeList = employeeRepository.getByEmail(employee.getEmail());

        // Assert , Then
        Assertions.assertThat(employeeList).isNotNull()
                .isNotEmpty()
                .contains(employee);

    }

    @Test
    void test_getByEmail_whenEmail_isNotFound_thenReturnEmpty_EmployeeList() {

        // Given
        String email = "not present.123@gmail.com";

        // When
        List<Employee> employeeList = employeeRepository.getByEmail(email);

        // Then
        Assertions.assertThat(employeeList)
                .isNotNull();

    }
}
