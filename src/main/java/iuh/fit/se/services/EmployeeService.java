package iuh.fit.se.services;

import iuh.fit.se.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Employee findById(int id);
    List<Employee> findAll();
    Page<Employee> findAllWithPaging(Pageable pageable);
    Employee save(Employee employee);
    boolean delete(int id);
    List<Employee> search(String keyword);
}
