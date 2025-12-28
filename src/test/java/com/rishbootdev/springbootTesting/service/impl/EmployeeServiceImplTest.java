package com.rishbootdev.springbootTesting.service.impl;

import com.rishbootdev.springbootTesting.dto.EmployeeDto;
import com.rishbootdev.springbootTesting.entity.Employee;
import com.rishbootdev.springbootTesting.repository.EmployeeRepository;
import com.rishbootdev.springbootTesting.testConfiguration.TestContainerConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import(TestContainerConfiguration.class)

@ExtendWith(MockitoExtension.class)  // important annotation from Mockito library
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee mockemployee;
    private EmployeeDto mockEmployeeDto;

    @Spy
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        Long id=1L;
        Employee mockemployee= Employee.builder()
                .id(id)
                .name("Rishabh")
                .email("rishfhdfhjdfdhjf@gmail.com")
                .build();

        mockEmployeeDto = modelMapper.map(mockemployee, EmployeeDto.class );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test(){

    }

    @Test
    void getEmployeeById_when_then_return_EmployeeDto() {
        employeeService.getEmployeeById(1L);

        // assign
        Long id=mockemployee.getId();
        when(employeeRepository.findById(id)).
                thenReturn(Optional.of(mockemployee));  // stubbing
        // act
        EmployeeDto employeeDto=employeeService.getEmployeeById(id);

        // assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getId()).isEqualTo(id);
        assertThat(employeeDto.getEmail()).isEqualTo(mockemployee.getEmail());

        // verify
        verify(employeeRepository).findById(id);
    }

    @Test
    void createNewEmployee_WhenValidEmployee_ThenCreateNewEmployee() {

        // assign
        when(employeeRepository.getByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockemployee);

        // act
        EmployeeDto employeeDto=employeeService.createNewEmployee(mockEmployeeDto);

        // assert
        assertThat(employeeDto).isNotNull()
                .isEqualTo(mockEmployeeDto);
        ArgumentCaptor<Employee> employeeArgumentCaptor=ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee captured=employeeArgumentCaptor.getValue();
        assertThat(captured.getEmail()).isEqualTo(mockemployee.getEmail());

    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}