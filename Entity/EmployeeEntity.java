package com.firstproject.firstproject.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employee")
public class EmployeeEntity {
    public void setId(Long id) {
        this.id = id;
    }
    @ Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private int age;
    private String role;
    private LocalDate dateofjoining;
    private Double salary;
    private Boolean isactive;


}
