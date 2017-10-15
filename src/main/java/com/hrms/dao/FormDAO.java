package com.hrms.dao;

import java.util.ArrayList;
import java.util.List;

public interface FormDAO {

	public List<Object> getFormListing(String formName);
	public Object getFormDatafromId(String formName, int id);
	public void addForm(Object obj);
	public void updateForm(Object p);
	public void deleteForm(int id, String formName);
	public int getMaxSequenceNumber(String formName);
	public ArrayList getRfqItems();
}
