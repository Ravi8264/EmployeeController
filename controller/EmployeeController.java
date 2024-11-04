package com.firstproject.firstproject.controller;
import com.firstproject.firstproject.Exception.ResourceNotFoundException;
import com.firstproject.firstproject.dto.EmployeeDto;
import com.firstproject.firstproject.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeId(@PathVariable(name = "id") Long id) throws NoSuchFieldException {
        EmployeeDto employeeDto = employeeService.findById(id);
        if (employeeDto != null) {
            return ResponseEntity.ok(employeeDto);
        } else {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
    }
    @GetMapping
   public ResponseEntity<List<EmployeeDto>> getAllEmployee(
        @RequestParam(required = false) Integer age,
        @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.findAll());
   }
    @PostMapping
    public ResponseEntity<EmployeeDto> postMappingValue(@RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.save(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeDto);
    }
    @PutMapping(path="/{employeeid}")
    public EmployeeDto updateEmployee(@PathVariable Long employeeid, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployeeId(employeeDto, employeeid);
    }
    @DeleteMapping("/{employeeid}")
    public ResponseEntity<Void> delete(@PathVariable Long employeeid) {
        boolean isDeleted = employeeService.delete(employeeid);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{employeeid}")
    public ResponseEntity<EmployeeDto> updateValue(@PathVariable Long employeeid, @RequestBody Map<String, Object> update) {
        EmployeeDto updatedEmployee = employeeService.updateEmployeeIdBypatchVal(employeeid, update);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }
}
