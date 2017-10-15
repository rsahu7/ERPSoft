package com.hrms.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.hrms.model.Customer;
import com.hrms.model.Employee;
import com.hrms.model.Item;
import com.hrms.model.NonInventoryItem;
import com.hrms.model.Project;
import com.hrms.model.RFQItem;
import com.hrms.model.Supplier;
import com.hrms.model.Warehouse;
import com.hrms.service.FormService;

public class ExcelBuilderMain extends AbstractExcelView{

	private FormService formService;

	@Autowired(required=true)
	@Qualifier(value="formService")
	public void setFormService(FormService ps){
		this.formService = ps;
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		
		// get data model which is passed by the Spring container
		
		String formName = (String)model.get("formName");
		if ("Supplier".equals(formName))
			setSupplierExcelData(model, workbook);
		else if ("Customer".equals(formName))
			setCustomerExcelData(model, workbook);
		else if ("Item".equals(formName))
			setItemExcelData(model, workbook);
		else if ("NonInventoryItem".equals(formName))
			setNonInventoryExcelData(model, workbook);
		else if ("Project".equals(formName))
			setProjectExcelData(model, workbook);
		else if ("Warehouse".equals(formName))
			setWarehouseExcelData(model, workbook);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + formName);
	
	}
	
	private void setSupplierExcelData(Map<String, Object> model, HSSFWorkbook workbook)
	{
		List<Supplier> w_supplierList = (List<Supplier>) model.get("listEmployee");
		
		// create a new Excel sheet
		HSSFSheet w_sheet = workbook.createSheet("Employee List");
		w_sheet.setDefaultColumnWidth(30);
		
		// create style for header cells
		CellStyle w_style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        w_style.setFillForegroundColor(HSSFColor.BLUE.index);
        w_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        w_style.setFont(font);
        
        //create header row
        List<String> w_columnHeaderList = Arrays.asList(Constants.SUPPLIER_HEADER_COLS.split(","));
  
        HSSFRow w_header = w_sheet.createRow(0);
        int w_col = 0;
        for (String w_columnheader : w_columnHeaderList)
        {
            w_header.createCell(w_col).setCellValue(w_columnheader);
            w_header.getCell(w_col++).setCellStyle(w_style);
        }        
        
        //create data rows
        int w_rowCount = 1;

        for (Supplier w_sup : w_supplierList)
        {
        	w_col = 0;      	   	 
        	
        	HSSFRow w_row = w_sheet.createRow(w_rowCount++);
        	w_row.createCell(w_col++).setCellValue(w_sup.getId());
        	w_row.createCell(w_col++).setCellValue(w_sup.getName());
        	w_row.createCell(w_col++).setCellValue(w_sup.getEmailid());
        	w_row.createCell(w_col++).setCellValue(w_sup.getContactno());
        	w_row.createCell(w_col++).setCellValue(w_sup.getDesignation());       	
        	w_row.createCell(w_col++).setCellValue(w_sup.getCountry());
        	w_row.createCell(w_col++).setCellValue(w_sup.getAddress());      
        	w_row.createCell(w_col++).setCellValue(w_sup.getZipcode());
        	w_row.createCell(w_col++).setCellValue(w_sup.getPayment());
        	w_row.createCell(w_col++).setCellValue(w_sup.getShortname());
        	w_row.createCell(w_col++).setCellValue(w_sup.getWebsite());
        	w_row.createCell(w_col++).setCellValue(w_sup.getRegion());
        	w_row.createCell(w_col++).setCellValue(w_sup.getTelephone());
        	w_row.createCell(w_col++).setCellValue(w_sup.getAccountcode());
        	w_row.createCell(w_col++).setCellValue(w_sup.getFax());
        	w_row.createCell(w_col++).setCellValue(w_sup.getContactperson());
        }
	}
	
	private void setCustomerExcelData(Map<String, Object> model, HSSFWorkbook workbook)
	{
		List<Customer> w_customerList = (List<Customer>) model.get("listEmployee");
		
		// create a new Excel sheet
		HSSFSheet w_sheet = workbook.createSheet("Customer List");
		w_sheet.setDefaultColumnWidth(30);
		
		// create style for header cells
		CellStyle w_style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        w_style.setFillForegroundColor(HSSFColor.BLUE.index);
        w_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        w_style.setFont(font);
        
        //create header row
        List<String> w_columnHeaderList = Arrays.asList(Constants.SUPPLIER_HEADER_COLS.split(","));
        
        HSSFRow w_header = w_sheet.createRow(0);
        int w_col = 0;
        for (String w_columnheader : w_columnHeaderList)
        {
            w_header.createCell(w_col).setCellValue(w_columnheader);
            w_header.getCell(w_col++).setCellStyle(w_style);
        }   
        
        //create data rows
        int w_rowCount = 1;

        for (Customer w_cus : w_customerList)
        {
        	w_col = 0;        	   	 
        	
        	HSSFRow w_row = w_sheet.createRow(w_rowCount++);
        	w_row.createCell(w_col++).setCellValue(w_cus.getId());
        	w_row.createCell(w_col++).setCellValue(w_cus.getName());
        	w_row.createCell(w_col++).setCellValue(w_cus.getEmailid());
        	w_row.createCell(w_col++).setCellValue(w_cus.getContactno());
        	w_row.createCell(w_col++).setCellValue(w_cus.getDesignation());       	
        	w_row.createCell(w_col++).setCellValue(w_cus.getCountry());
        	w_row.createCell(w_col++).setCellValue(w_cus.getAddress());      
        	w_row.createCell(w_col++).setCellValue(w_cus.getZipcode());
        	w_row.createCell(w_col++).setCellValue(w_cus.getPayment());
        	w_row.createCell(w_col++).setCellValue(w_cus.getShortname());
        	w_row.createCell(w_col++).setCellValue(w_cus.getWebsite());
        	w_row.createCell(w_col++).setCellValue(w_cus.getRegion());
        	w_row.createCell(w_col++).setCellValue(w_cus.getTelephone());
        	w_row.createCell(w_col++).setCellValue(w_cus.getAccountcode());
        	w_row.createCell(w_col++).setCellValue(w_cus.getFax());
        	w_row.createCell(w_col++).setCellValue(w_cus.getContactperson());
        	w_row.createCell(w_col++).setCellValue(w_cus.getCreditlimit());
        }
	}

	private void setItemExcelData(Map<String, Object> model, HSSFWorkbook workbook)
	{
		List<Item> w_itemList = (List<Item>) model.get("listEmployee");
		
		// create a new Excel sheet
		HSSFSheet w_sheet = workbook.createSheet("Customer List");
		w_sheet.setDefaultColumnWidth(30);
		
		// create style for header cells
		CellStyle w_style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        w_style.setFillForegroundColor(HSSFColor.BLUE.index);
        w_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        w_style.setFont(font);
        
        //create header row
        List<String> w_columnHeaderList = Arrays.asList(Constants.ITEM_HEADER_COLS.split(","));
        
        HSSFRow w_header = w_sheet.createRow(0);
        int w_col = 0;
        for (String w_columnheader : w_columnHeaderList)
        {
            w_header.createCell(w_col).setCellValue(w_columnheader);
            w_header.getCell(w_col++).setCellStyle(w_style);
        }   
        
        //create data rows
        int w_rowCount = 1;

        for (Item w_item : w_itemList)
        {
        	w_col = 0;       	   	 
        	
        	HSSFRow w_row = w_sheet.createRow(w_rowCount++);
        	w_row.createCell(w_col++).setCellValue(w_item.getId());
        	w_row.createCell(w_col++).setCellValue(w_item.getDescription());
        	w_row.createCell(w_col++).setCellValue(w_item.getCustomerid());
        	w_row.createCell(w_col++).setCellValue(w_item.getBrandname());
        	w_row.createCell(w_col++).setCellValue(w_item.getAvgcost());       	
        	w_row.createCell(w_col++).setCellValue(w_item.getUom());
        	w_row.createCell(w_col++).setCellValue(w_item.getPrimaryunit());      
        	w_row.createCell(w_col++).setCellValue(w_item.getCostprice());
        	w_row.createCell(w_col++).setCellValue(w_item.getRetailprice());
        	w_row.createCell(w_col++).setCellValue(w_item.getWholesaleprice());
        	w_row.createCell(w_col++).setCellValue(w_item.getCurrentstock());
        	w_row.createCell(w_col++).setCellValue(w_item.getReservedstock());
        	w_row.createCell(w_col++).setCellValue(w_item.getMinimumstock());
        	w_row.createCell(w_col++).setCellValue(w_item.getRetailmarkup());
        	w_row.createCell(w_col++).setCellValue(w_item.getWholesalemarkup());
        	w_row.createCell(w_col++).setCellValue(w_item.getBarcode());
        	w_row.createCell(w_col++).setCellValue(w_item.getCustomerbarcode());
        	w_row.createCell(w_col++).setCellValue(w_item.getVolume());
        	w_row.createCell(w_col++).setCellValue(w_item.getPackingsize());
        	w_row.createCell(w_col++).setCellValue(w_item.getReorderlevel());
        }
	}
	
	private void setNonInventoryExcelData(Map<String, Object> model, HSSFWorkbook workbook)
	{
		List<NonInventoryItem> w_itemList = (List<NonInventoryItem>) model.get("listEmployee");
		
		// create a new Excel sheet
		HSSFSheet w_sheet = workbook.createSheet("NonInventory Item List");
		w_sheet.setDefaultColumnWidth(30);
		
		// create style for header cells
		CellStyle w_style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        w_style.setFillForegroundColor(HSSFColor.BLUE.index);
        w_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        w_style.setFont(font);
        
        //create header row
        List<String> w_columnHeaderList = Arrays.asList(Constants.NOVINVENTORYITEM_HEADER_COLS.split(","));
        
        HSSFRow w_header = w_sheet.createRow(0);
        int w_col = 0;
        for (String w_columnheader : w_columnHeaderList)
        {
            w_header.createCell(w_col).setCellValue(w_columnheader);
            w_header.getCell(w_col++).setCellStyle(w_style);
        }   
        
        //create data rows
        int w_rowCount = 1;

        for (NonInventoryItem w_item : w_itemList)
        {
        	w_col = 0;       	   	 
        	
        	HSSFRow w_row = w_sheet.createRow(w_rowCount++);
        	w_row.createCell(w_col++).setCellValue(w_item.getId());
        	w_row.createCell(w_col++).setCellValue(w_item.getDescription());
        	w_row.createCell(w_col++).setCellValue(w_item.getGroupname());
        }
	}

	private void setProjectExcelData(Map<String, Object> model, HSSFWorkbook workbook)
	{
		List<Project> w_projectList = (List<Project>) model.get("listEmployee");
		
		// create a new Excel sheet
		HSSFSheet w_sheet = workbook.createSheet("NonInventory Item List");
		w_sheet.setDefaultColumnWidth(30);
		
		// create style for header cells
		CellStyle w_style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        w_style.setFillForegroundColor(HSSFColor.BLUE.index);
        w_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        w_style.setFont(font);
        
        //create header row
        List<String> w_columnHeaderList = Arrays.asList(Constants.NOVINVENTORYITEM_HEADER_COLS.split(","));
        
        HSSFRow w_header = w_sheet.createRow(0);
        int w_col = 0;
        for (String w_columnheader : w_columnHeaderList)
        {
            w_header.createCell(w_col).setCellValue(w_columnheader);
            w_header.getCell(w_col++).setCellStyle(w_style);
        }   
        
        //create data rows
        int w_rowCount = 1;

        for (Project w_project : w_projectList)
        {
        	w_col = 0;       	   	 
        	
        	HSSFRow w_row = w_sheet.createRow(w_rowCount++);
        	w_row.createCell(w_col++).setCellValue(w_project.getId());
        	w_row.createCell(w_col++).setCellValue(w_project.getDescription());
        	w_row.createCell(w_col++).setCellValue(w_project.getProjectno());
        	w_row.createCell(w_col++).setCellValue(w_project.getAddress());
        }
	}
	
	private void setWarehouseExcelData(Map<String, Object> model, HSSFWorkbook workbook)
	{
		List<Warehouse> w_warehouseList = (List<Warehouse>) model.get("listEmployee");
		
		// create a new Excel sheet
		HSSFSheet w_sheet = workbook.createSheet("WareHouse List");
		w_sheet.setDefaultColumnWidth(30);
		
		// create style for header cells
		CellStyle w_style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        w_style.setFillForegroundColor(HSSFColor.BLUE.index);
        w_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        w_style.setFont(font);
        
        //create header row
        List<String> w_columnHeaderList = Arrays.asList(Constants.WAREHOUSE_HEADER_COLS.split(","));
        
        HSSFRow w_header = w_sheet.createRow(0);
        int w_col = 0;
        for (String w_columnheader : w_columnHeaderList)
        {
            w_header.createCell(w_col).setCellValue(w_columnheader);
            w_header.getCell(w_col++).setCellStyle(w_style);
        }   
        
        //create data rows
        int w_rowCount = 1;

        for (Warehouse w_warehouse : w_warehouseList)
        {
        	w_col = 0;       	   	 
        	
        	HSSFRow w_row = w_sheet.createRow(w_rowCount++);
        	w_row.createCell(w_col++).setCellValue(w_warehouse.getId());
        	w_row.createCell(w_col++).setCellValue(w_warehouse.getDescription());
        	w_row.createCell(w_col++).setCellValue(w_warehouse.getWarehouseno());
        	w_row.createCell(w_col++).setCellValue(w_warehouse.getAddress1());
        	w_row.createCell(w_col++).setCellValue(w_warehouse.getAddress2());
        	w_row.createCell(w_col++).setCellValue(w_warehouse.getAddress3());
        }
	}
	
	public static Supplier getSupplierObjectFromExcel(Row w_row, Supplier a_sup, boolean a_addFlag)
	{
		int w_idx = 1;
		//Sets the read data to the model class.
		Date w_currentDate = new Date();
		DataFormatter w_dataformat = new DataFormatter();
		try
		{
			a_sup.setName(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setEmailid(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setContactno(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setDesignation(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setCountry(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setAddress(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setZipcode(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setPayment(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setShortname(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setWebsite(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setRegion(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));			
			a_sup.setTelephone(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setAccountcode(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setFax(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_sup.setContactperson(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			
			if (a_addFlag)
				a_sup.setCreationdate(w_currentDate);//skip creationdate column
		}
		catch (Exception a_ex)
		{
			a_ex.printStackTrace();
		}
		return a_sup;
	}
	
	public static Customer getCustomerObjectFromExcel(Row w_row, Customer a_cus, boolean a_addFlag)
	{
		int w_idx = 1;
		//Sets the read data to the model class.
		Date w_currentDate = new Date();
		DataFormatter w_dataformat = new DataFormatter();
		try
		{
			a_cus.setName(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setEmailid(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setContactno(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setDesignation(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setCountry(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setAddress(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setZipcode(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setPayment(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setShortname(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setWebsite(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setRegion(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));			
			a_cus.setTelephone(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setAccountcode(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setFax(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setContactperson(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_cus.setCreditlimit(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			
			if (a_addFlag)
				a_cus.setCreationdate(w_currentDate);//skip creationdate column
		}
		catch (Exception a_ex)
		{
			a_ex.printStackTrace();
		}
		return a_cus;
	}
	
	public static RFQItem getRFQItemObjectFromExcel(Row w_row, RFQItem a_item, boolean a_addFlag)
	{
		int w_idx = 1;
		//Sets the read data to the model class.
		Date w_currentDate = new Date();
		DataFormatter w_dataformat = new DataFormatter();
		try
		{
			a_item.setDescription(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_item.setEmail(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_item.setQuantity(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
			a_item.setUnit(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));

			if (a_addFlag)
				a_item.setCreationdate(w_currentDate);//skip creationdate column
		}
		catch (Exception a_ex)
		{
			a_ex.printStackTrace();
		}
		return a_item;
	}
	
}
