package iuh.fit.se.controllers;

import iuh.fit.se.entities.Employee;
import iuh.fit.se.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable int id) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("status", HttpStatus.OK.value());
        response.put("data", employeeService.findById(id));

        logger.info("Action: getEmployeeById with id: {}", id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/employees")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Employee employee) {
        Map<String, Object> response = new LinkedHashMap<>();
        
        logger.info("Action: save employee: {}", employee);
        
        Employee savedEmployee = employeeService.save(employee);
        response.put("status", HttpStatus.CREATED.value());
        response.put("data", savedEmployee);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
