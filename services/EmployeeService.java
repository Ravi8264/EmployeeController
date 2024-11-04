package com.firstproject.firstproject.services;
import com.firstproject.firstproject.Entity.EmployeeEntity;
import com.firstproject.firstproject.Repository.EmployeeRepository;
import com.firstproject.firstproject.dto.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;

        this.modelMapper = modelMapper;
    }
    public EmployeeDto updateEmployeeId(EmployeeDto employeeDto, Long employeeid) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setId(employeeid);
        EmployeeEntity saveemployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(saveemployeeEntity, EmployeeDto.class);
    }
    public EmployeeDto save(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity, EmployeeDto.class);
    }
    public EmployeeDto findById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity == null) {
            return null;
        }
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }
    public List<EmployeeDto> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }
    public boolean delete(Long employeeid) {
        if (!employeeRepository.existsById(employeeid)) return false;
        employeeRepository.deleteById(employeeid);
        return true;
    }
    public EmployeeDto updateEmployeeIdBypatchVal(Long employeeid, Map<String, Object> update) {
        if (!employeeRepository.existsById(employeeid)) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeid).orElse(null);
        if (employeeEntity == null) return null;
        update.forEach((key, value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(EmployeeEntity.class, key);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, employeeEntity, value);
        });
        EmployeeEntity updatedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEntity, EmployeeDto.class);
    }
}