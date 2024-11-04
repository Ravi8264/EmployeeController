package com.firstproject.firstproject.dto;

import com.firstproject.firstproject.annonation.EmployeeRoleValidation;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Employee name is required")
    @Size(min = 1, max = 100, message = "Employee name must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    private String email;

    @Min(value = 20, message = "Age must be at least 20")
    @Max(value = 80, message = "Age must not exceed 80")
    private int age;

    @NotNull(message = "Role must not be null")
    @EmployeeRoleValidation
    @Size(min = 4, max = 20, message = "Role must be between 4 and 20 characters")
    private String role;

    @PastOrPresent(message = "Date of joining must be in the past or present")
    private LocalDate dateofjoining;

    @Positive(message = "Salary must be a positive number")
    private Double salary;

    @NotNull(message = "Active status must be specified")
    private Boolean isactive;

}
