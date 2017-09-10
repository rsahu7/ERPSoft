package com.hrms.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrms.model.Employee;
import com.hrms.model.Interview;

public interface EmployeeDAO {

	public void addEmployee(Employee p);
	public void updateEmployee(Employee p);
	public void addInterview(Interview p);
	public void updateInterview(Interview p);
	public List<Employee> listEmployees();
	public List<Employee> searchEmployees(HttpServletRequest request, HttpServletResponse response);
	public Employee getEmployeeById(int id);
	public void removeEmployee(int id);
	public Interview getInterviewById(int id);
	public int checkEmail(String email);
}
