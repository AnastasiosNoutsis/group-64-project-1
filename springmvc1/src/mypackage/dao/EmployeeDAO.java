package mypackage.dao;

import java.util.List;

import mypackage.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> getEmployees();

	public void saveEmployee(Employee employee);

	public Employee getEmployee(String username);

	public void deleteEmployee(String username);

}
