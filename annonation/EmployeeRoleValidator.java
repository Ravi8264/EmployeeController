package com.firstproject.firstproject.annonation;

import com.firstproject.firstproject.annonation.EmployeeRoleValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
public class EmployeeRoleValidator implements
        ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        if(inputRole == null) return false;
        List<String> roles = List.of("user", "admin");
        return roles.contains(inputRole);
    }
}
