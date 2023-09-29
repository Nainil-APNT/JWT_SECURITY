package com.login.employee;

import java.util.List;


public interface EmployeeService {

	Employee createEmployee(Employee employee);

	List<Employee> getAllEmployee();

	Employee getemployeeById(Integer employeeId);

	Employee updateEmployee(Employee employee);

	void deleteEmployee(Integer employeeId);

	//AQLResponse<Object> getFilteredAndPaginatedEmployees(AQLRequest request);
	
	

}
