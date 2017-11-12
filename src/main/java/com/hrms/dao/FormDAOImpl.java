package com.hrms.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.hrms.model.RFQSupplier;
import com.hrms.model.Warehouse;

@Repository
public class FormDAOImpl implements FormDAO {

	private static final Logger logger = LoggerFactory.getLogger(FormDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Object> getFormListing(String formName) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Object> formListData = session.createQuery("from " + formName + " order by creationdate desc").list();
		// Sort date -- latest first
		/*
		 * Collections.sort(employeesList, new Comparator<Employee>() { public int
		 * compare(Employee e1, Employee e2) { return
		 * e2.getCreationdate().compareTo(e1.getCreationdate()); } });
		 */
		logger.info("Employee List::" + formListData);
		return formListData;
	}

	@Override
	public Object getFormDatafromId(String formName, int id) {

		Session session = this.sessionFactory.getCurrentSession();
		Object obj = null;
		try {
			Class c = Class.forName("com.hrms.model." + formName);
			obj = (Object) session.load(c, new Integer(id));
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Employee loaded successfully, Employee details=" + obj);
		return obj;
	}

	@Override
	public void addForm(Object obj) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(obj);
		logger.info("Employee saved successfully, Employee Details=" + obj);
	}

	@Override
	public void updateForm(Object p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Employee updated successfully, Employee Details=" + p);

	}

	@Override
	public int getMaxSequenceNumber(String formName) {
		// TODO Auto-generated method stub
		int maxNum = 0;
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select max(code) from " + formName);
		List results = query.list();
		if (!results.isEmpty()) {
			String seqNum = (String) results.get(0);
			if (!(seqNum == null || seqNum == "" || seqNum.isEmpty()))
				maxNum = Integer.parseInt(seqNum);
		}

		return ++maxNum;
	}

	@Override
	public void deleteForm(int id, String formName) {
		Session session = this.sessionFactory.getCurrentSession();
		Object obj = null;
		try {
			Class c = Class.forName("com.hrms.model." + formName);
			obj = (Object) session.load(c, new Integer(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null) {
			session.delete(obj);
		}
		logger.info("Employee deleted successfully, Employee details=" + obj);
	}

	@Override
	public ArrayList getRfqItems() {
		ArrayList ja = new ArrayList();
		try {
			InputStream ExcelFileToRead = new FileInputStream("D:/ERP/toroshan/4_P_1.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

			XSSFSheet sheet = wb.getSheetAt(3);
			XSSFRow row;
			XSSFCell cell;

			ArrayList c = new ArrayList();
			ArrayList h = new ArrayList();
			ArrayList main = new ArrayList();
			Iterator<Row> rows = sheet.rowIterator();
			// ArrayList<String> header=new ArrayList<String>();

			int i = 0;
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					cell = (XSSFCell) cells.next();
					if (i != 0) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							c.add(cell.getStringCellValue());
						} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							c.add(cell.getNumericCellValue());
						} else if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							System.out.println("blank recvd");
							c.add("demo");
						} else {
						}
					} else {

						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							h.add(cell.getStringCellValue());
						} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							h.add(cell.getNumericCellValue());
						} else if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							System.out.println("blank recvd");
							h.add("demo");
						} else {

						}
					}
				}
				i++;
			}
			int hlen = h.size();
			System.out.println("i =" + i + "hlen" + hlen);
			// main.add(h);
			main.add(c);
			System.out.print(c);
			System.out.println(c.size());
			int p = 0;

			while (p < c.size() - hlen) {
				HashMap dj = new HashMap();
				dj.put("ItemReference", c.get(p));
				dj.put("Description", c.get(p + 2));
				dj.put("UnitofMeasurement", c.get(p + 3));
				dj.put("Quantity", c.get(p + 4));
				dj.put("Unitprice", c.get(p + 5));
				dj.put("MinimumUnitPrice", c.get(p + 6));
				dj.put("MaximumUnitprice", c.get(p + 7));
				dj.put("Price", c.get(p + 8));
				ja.add(dj);

				p = p + hlen;
				System.out.println(p);
			}
		} catch (Exception r) {
			r.printStackTrace();
		}
		return ja;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> listSupplier() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Supplier> supplierList = session.createQuery("from Supplier").list();
		logger.info("supplierList::" + supplierList);
		return supplierList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RFQSupplier> listRFQSupplier(String rFQId, String rFQItemId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from RFQSupplier s where s.rfqId=:p1 and s.rfqItemId=:p2");
		q.setParameter("p1",Integer.parseInt(rFQId));
		q.setParameter("p2",Integer.parseInt(rFQItemId));
	    List<RFQSupplier> listRFQSupplier = q.list();
		logger.info("RFQSupplier List::" + listRFQSupplier);
		return listRFQSupplier;
	}

	@Override
	public void updateRfqSupplierId(RFQSupplier rfqSupplier) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(rfqSupplier);
		logger.info("rfqSupplier updated successfully, rfqSupplier Details="+rfqSupplier);
		
	}

	@Override
	public void addRfqSupplierId(RFQSupplier rfqSupplier) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(rfqSupplier);
		logger.info("rfqSupplier saved successfully, rfqSupplier Details="+rfqSupplier);
		
	}

	@Override
	public void removeRfqSupp(int rfqSupplierId) {
		Session session = this.sessionFactory.getCurrentSession();
		RFQSupplier p = (RFQSupplier) session.load(RFQSupplier.class, new Integer(rfqSupplierId));
		if(null != p){
			session.delete(p);
		}
	}
}
