package services.impl.employee;

import models.Employee;
import repository.Repository;
import repository.impl.employee.EmployeeRepositoryJDBImpl;
import services.Service;

import java.util.List;

public class EmployeeServiceImpl implements Service<Employee> {
    private Repository<Employee> employeeRepository;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryJDBImpl();
    }

    @Override
    public void add(Employee employee) {

    }

    @Override
    public List<Employee> listAll() {
        return null;
    }
}
