package com.hrms.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.hrms.model.Employee;

public class ExcelBuilder extends AbstractExcelView {
	
	@SuppressWarnings("deprecation")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
		HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		// get data model which is passed by the Spring container
		List<Employee> w_employeeList = (List<Employee>) model.get("listEmployee");
		
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
        
        HSSFRow w_header = w_sheet.createRow(0);
        int w_col = 0;
        w_header.createCell(w_col).setCellValue("FirstName");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("MiddleName");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("LastName");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Creationdate");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Gender");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Birthdate");
        w_header.getCell(w_col++).setCellStyle(w_style); 
        
        w_header.createCell(w_col).setCellValue("Email");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Contact1");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Contact2");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Address");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("City");
        w_header.getCell(w_col++).setCellStyle(w_style); 
        
        w_header.createCell(w_col).setCellValue("State");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("MaritialStatus");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("SpouseName");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Education");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Exprience");
        w_header.getCell(w_col++).setCellStyle(w_style); 
        
        w_header.createCell(w_col).setCellValue("Designation");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Department");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Industry");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("Salary");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("WorkNature");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("AreaofWork");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("PrefWorkInterest");
        w_header.getCell(w_col++).setCellStyle(w_style); 
        
        w_header.createCell(w_col).setCellValue("PrefWorkLoc");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        w_header.createCell(w_col).setCellValue("ExpectedSal");
        w_header.getCell(w_col++).setCellStyle(w_style);

        w_header.createCell(w_col).setCellValue("Reference");
        w_header.getCell(w_col++).setCellStyle(w_style);
        
        //create data rows
        int w_rowCount = 1;

        for (Employee w_emp : w_employeeList)
        {
        	w_col = 0;
        	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        	Date w_birthDate = w_emp.getBirthdate();
        	if (w_birthDate == null)
        		w_birthDate = formatter.parse("01/01/1970");
        	String w_birthdate = formatter.format(w_birthDate);      	 
        	
        	HSSFRow w_row = w_sheet.createRow(w_rowCount++);
        	w_row.createCell(w_col++).setCellValue(w_emp.getName());
        	w_row.createCell(w_col++).setCellValue(w_emp.getMiddlename());
        	w_row.createCell(w_col++).setCellValue(w_emp.getLastname());
        	w_row.createCell(w_col++).setCellValue(w_emp.getCreationdate());       	
        	w_row.createCell(w_col++).setCellValue(w_emp.getGender());
        	w_row.createCell(w_col++).setCellValue(w_birthdate);      
        	w_row.createCell(w_col++).setCellValue(w_emp.getEmail());
        	w_row.createCell(w_col++).setCellValue(w_emp.getContact1());
        	w_row.createCell(w_col++).setCellValue(w_emp.getContact2());
        	w_row.createCell(w_col++).setCellValue(w_emp.getAddress());
        	w_row.createCell(w_col++).setCellValue(w_emp.getCity());
        	w_row.createCell(w_col++).setCellValue(w_emp.getState());
        	w_row.createCell(w_col++).setCellValue(w_emp.getMaritialstatus());
        	w_row.createCell(w_col++).setCellValue(w_emp.getSpousename());
        	w_row.createCell(w_col++).setCellValue(w_emp.getEducation());
        	w_row.createCell(w_col++).setCellValue(w_emp.getExprience());
        	w_row.createCell(w_col++).setCellValue(w_emp.getDesignation());
        	w_row.createCell(w_col++).setCellValue(w_emp.getDepartment());
        	w_row.createCell(w_col++).setCellValue(w_emp.getIndustry());
        	w_row.createCell(w_col++).setCellValue(w_emp.getSalary());
        	w_row.createCell(w_col++).setCellValue(w_emp.getWorknature());
        	w_row.createCell(w_col++).setCellValue(w_emp.getAreaofwork());
        	w_row.createCell(w_col++).setCellValue(w_emp.getPrefworkinterest());
        	w_row.createCell(w_col++).setCellValue(w_emp.getPrefworkloc());
        	w_row.createCell(w_col++).setCellValue(w_emp.getExpectedsal());
        	w_row.createCell(w_col++).setCellValue(w_emp.getReference());

        }
	}
	
	
}
