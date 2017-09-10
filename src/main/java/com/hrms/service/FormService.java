package com.hrms.service;

import java.util.List;


public interface FormService {

	public List<Object> getFormListing(String formName);
	public Object getFormDatafromId(String formName, int id);
	public void addForm(Object obj);
	public void updateForm(Object p);
	public int getMaxSequenceNumber(String formName);
	public String getItemPrefix(String formName);
	public void deleteForm(int id, String formName);
}
