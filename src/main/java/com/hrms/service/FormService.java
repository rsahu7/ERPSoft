package com.hrms.service;

import java.util.List;

import com.hrms.model.RFQSupplier;
import com.hrms.model.Supplier;


public interface FormService {

	public List<Object> getFormListing(String formName);
	public Object getFormDatafromId(String formName, int id);
	public void addForm(Object obj);
	public void updateForm(Object p);
	public int getMaxSequenceNumber(String formName);
	public String getItemPrefix(String formName);
	public void deleteForm(int id, String formName);
	public List<Supplier> listSupplier();
	public List<RFQSupplier> listRFQSupplier(String rFQId, String rFQItemId);
	public void addRfqSupplierId(RFQSupplier rfqSupplier);
	public void updateRfqSupplierId(RFQSupplier rfqSupplier);
	public void removeRfqSupp(int id);
}
