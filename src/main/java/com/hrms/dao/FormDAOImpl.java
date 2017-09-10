package com.hrms.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.model.Customer;
import com.hrms.model.Employee;
import com.hrms.model.Supplier;
import com.hrms.model.Item;
import com.hrms.model.NonInventoryItem;
import com.hrms.model.Project;
import com.hrms.model.Warehouse;

@Repository
public class FormDAOImpl implements FormDAO
{
	
	private static final Logger logger = LoggerFactory.getLogger(FormDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public List<Object> getFormListing(String formName) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Object> formListData = session.createQuery("from " + formName + " order by creationdate desc").list();
		//Sort date -- latest first
		/*Collections.sort(employeesList, new Comparator<Employee>() {
	        public int compare(Employee e1, Employee e2) {
	        	return e2.getCreationdate().compareTo(e1.getCreationdate());
	        }
	    });*/
		logger.info("Employee List::"+formListData);
		return formListData;
	}
	
	@Override
	public Object getFormDatafromId(String formName, int id) {

		Session session = this.sessionFactory.getCurrentSession();
		Object obj = null;
		try
		{
			Class c = Class.forName("com.hrms.model." + formName);
			obj = (Object) session.load(c, new Integer(id));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		logger.info("Employee loaded successfully, Employee details="+obj);
		return obj;
	}
	
	@Override
	public void addForm(Object obj) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(obj);
		logger.info("Employee saved successfully, Employee Details="+obj);
	}

	@Override
	public void updateForm(Object p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Employee updated successfully, Employee Details="+p);

	}

	@Override
	public int getMaxSequenceNumber(String formName) {
		// TODO Auto-generated method stub
		int maxNum = 0;
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select max(code) from " + formName);
		List results = query.list();
		if (!results.isEmpty())
		{
			String seqNum = (String)results.get(0);
			if (!(seqNum == null || seqNum == "" || seqNum.isEmpty()))
				maxNum = Integer.parseInt(seqNum);
		}
		
		return ++maxNum;
	}
	
	@Override
	public void deleteForm(int id, String formName) {
		Session session = this.sessionFactory.getCurrentSession();
		Object obj = null;
		try
		{
			Class c = Class.forName("com.hrms.model." + formName);
			obj = (Object) session.load(c, new Integer(id));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(obj != null){
			session.delete(obj);
		}
		logger.info("Employee deleted successfully, Employee details="+obj);
	}
}
