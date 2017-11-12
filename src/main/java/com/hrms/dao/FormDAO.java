package com.hrms.dao;

import java.util.ArrayList;
import java.util.List;

import com.hrms.model.RFQSupplier;
import com.hrms.model.Supplier;

public interface FormDAO {

	public List<Object> getFormListing(String formName);
	public Object getFormDatafromId(String formName, int id);
	public void addForm(Object obj);
	public void updateForm(Object p);
	public void deleteForm(int id, String formName);
	public int getMaxSequenceNumber(String formName);
	public ArrayList<?> getRfqItems();
	public List<Supplier> listSupplier();
	public List<RFQSupplier> listRFQSupplier(String rFQId, String rFQItemId);
	public void updateRfqSupplierId(RFQSupplier rfqSupplier);
	public void addRfqSupplierId(RFQSupplier rfqSupplier);
	public void removeRfqSupp(int id);
}
