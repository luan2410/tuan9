package iuh.fit.se.services.impl;

import iuh.fit.se.entities.Employee;
import iuh.fit.se.exceptions.ValidationException;
import iuh.fit.se.repositories.EmployeeRepository;
import iuh.fit.se.services.EmployeeService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Validator validator;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public Employee findById(int id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Page<Employee> findAllWithPaging(Pageable pageable) {
        return null;
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        if (!violations.isEmpty()) {
            Map<String, Object> errors = new LinkedHashMap<>();
            violations.forEach(violation -> {
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            });
            throw new ValidationException("An error occurred while adding the employee", errors);
        } else {
            employeeRepository.save(employee);
        }

        return employee;
    }


    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Employee> search(String keyword) {
        return null;
    }
}
