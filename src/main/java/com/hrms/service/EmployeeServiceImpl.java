package com.hrms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.dao.EmployeeDAO;
import com.hrms.model.Employee;
import com.hrms.model.Interview;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public void addEmployee(Employee p) {
		this.employeeDAO.addEmployee(p);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee p) {
		this.employeeDAO.updateEmployee(p);
	}

	@Override
	@Transactional
	public List<Employee> listEmployees() {
		return this.employeeDAO.listEmployees();
	}

	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		return this.employeeDAO.getEmployeeById(id);
	}

	@Override
	@Transactional
	public void removeEmployee(int id) {
		this.employeeDAO.removeEmployee(id);
	}

	@Override
	@Transactional
	public void addInterview(Interview p) {
		this.employeeDAO.addInterview(p);

	}

	@Override
	@Transactional
	public void updateInterview(Interview p) {
		this.employeeDAO.updateInterview(p);

	}

	@Override
	@Transactional
	public Interview getInterviewById(int id) {
		return this.employeeDAO.getInterviewById(id);
	}

	@Override
	@Transactional
	public List<Employee> searchEmployees(HttpServletRequest request, HttpServletResponse response) {
		return this.employeeDAO.searchEmployees(request,response);
	}

	@Override
	@Transactional
	public int checkEmail(String email) {
		return this.employeeDAO.checkEmail(email);
	}


}
