package com.rishbootdev.springbootTesting.dto;


import lombok.*;

@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String email;
    private String name;
    private Long salary;

}
