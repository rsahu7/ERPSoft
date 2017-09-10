package com.hrms.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.model.Employee;
import com.hrms.model.Interview;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addEmployee(Employee p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Employee saved successfully, Employee Details="+p);
	}

	@Override
	public void updateEmployee(Employee p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Employee updated successfully, Employee Details="+p);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployees() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee> employeesList = session.createQuery("from Employee order by date(creationdate) desc").list();
		//Sort date -- latest first
		Collections.sort(employeesList, new Comparator<Employee>() {
	        public int compare(Employee e1, Employee e2) {
	        	return e2.getCreationdate().compareTo(e1.getCreationdate());
	        }
	    });
		logger.info("Employee List::"+employeesList);
		return employeesList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		logger.info("Employee loaded successfully, Employee details="+p);
		return p;
	}

	@Override
	public void removeEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Employee deleted successfully, Employee details="+p);
	}

	@Override
	public void addInterview(Interview p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Employee saved successfully, Interview Details="+p);

	}

	@Override
	public void updateInterview(Interview p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Employee updated successfully, Interview Details="+p);

	}

	@Override
	public Interview getInterviewById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Interview p = (Interview) session.load(Interview.class, new Integer(id));
		logger.info("Employee loaded successfully, Employee details="+p);
		return p;
	}

	@Override
	public List<Employee> searchEmployees(HttpServletRequest request, HttpServletResponse response) {
		Session session = this.sessionFactory.getCurrentSession();

		Criteria crit = session.createCriteria(Employee.class);
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            //To add from and to date condition
            if(paramName.equals("fromdate")||paramName.equals("todate")){
            	String[] fromdate = request.getParameterValues("fromdate");
            	String[] todate = request.getParameterValues("todate");
            	if(fromdate.length > 1 || !fromdate[0].equals("")||todate.length > 1 || !todate[0].equals("")){
            		try {
                		String  fromDate = fromdate[0] ;
                		String	toDate = todate[0];
                		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                		if(fromDate.equals("") && !toDate.equals("")){
                			crit.add(Restrictions.lt("creationdate",df.parse(toDate)));
                			continue;
                		}else if(toDate.equals("") && !fromDate.equals("")){
                			crit.add(Restrictions.gt("creationdate",df.parse(fromDate)));
                			continue;
                		}
						crit.add(Restrictions.between("creationdate",df.parse(fromDate),df.parse(toDate)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
            	}
            	//skip loop on date encounter
            	continue;
             }

          //traverse through parameters to add to criteria
            String[] paramValues = request.getParameterValues(paramName);
            if(paramValues.length > 1 || !paramValues[0].equals("")){
            	for (int i = 0; i < paramValues.length; i++) {
                    String paramValue = paramValues[i];
                    //if condition added to convert to double data type and add
                    if(paramName.equals("exprience")||paramName.equals("salary")){
                    	double d = Double.parseDouble(paramValue);
                    	crit.add(Restrictions.eq(paramName,d));
                    }else{
                    	crit.add(Restrictions.eq(paramName,paramValue));
                    }
                }
            }
        }
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Employee> employeesList = crit.list();

		//Sort date -- latest first
		Collections.sort(employeesList, new Comparator<Employee>() {
	        public int compare(Employee e1, Employee e2) {
	        	return e2.getCreationdate().compareTo(e1.getCreationdate());
	        }
	    });
		return employeesList;
	}

	@Override
	public int checkEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query  query = session.createSQLQuery("SELECT * FROM EMPLOYEE WHERE email like :email").addEntity(Employee.class);
		List<Employee> results = query.setString("email", email).list();
		logger.info("Employee with emaiId : "+" email "+", Employee details="+results);
		if(!results.isEmpty()){
			return results.get(0).getId();
		}else{
			return 0;
		}
	}

}
