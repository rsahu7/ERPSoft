package com.hrms.service;

import java.util.List;

import javax.transaction.Transactional;

import com.hrms.dao.FormDAO;
import com.hrms.helper.Constants;
import com.hrms.model.Employee;

public class FormServiceImpl implements FormService {

	private FormDAO formDAO;

	public void setFormDAO(FormDAO formDAO) {
		this.formDAO = formDAO;
	}

	@Override
	@Transactional
	public List<Object> getFormListing(String formName) {
		// TODO Auto-generated method stub
		return this.formDAO.getFormListing(formName);
	}
	
	@Override
	@Transactional
	public Object getFormDatafromId(String formName, int id) {
		return this.formDAO.getFormDatafromId(formName, id);
	}
	
	@Override
	@Transactional
	public void addForm(Object obj) {
		this.formDAO.addForm(obj);
	}

	@Override
	@Transactional
	public void updateForm(Object p) {
		this.formDAO.updateForm(p);
	}
	
	@Override
	@Transactional
	public void deleteForm(int id, String formName) {
		this.formDAO.deleteForm(id, formName);
	}

	@Override
	@Transactional
	public int getMaxSequenceNumber(String formName) {
		return this.formDAO.getMaxSequenceNumber(formName);
		
	}

	@Override
	public String getItemPrefix(String formName) {
		String itemPrefix = "";
		if ("Supplier".equalsIgnoreCase(formName))
			itemPrefix = Constants.SUPPLIER_PREFIX_CODE;
		else if ("Customer".equalsIgnoreCase(formName))
			itemPrefix = Constants.SUPPLIER_PREFIX_CODE;
		else if ("Project".equalsIgnoreCase(formName))
			itemPrefix = Constants.SUPPLIER_PREFIX_CODE;
		
		return itemPrefix;
	}
	
}
