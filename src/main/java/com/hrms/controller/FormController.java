package com.hrms.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hrms.helper.ExcelBuilderMain;
import com.hrms.model.Customer;
import com.hrms.model.Employee;
import com.hrms.model.Item;
import com.hrms.model.LoginBean;
import com.hrms.model.NonInventoryItem;
import com.hrms.model.Project;
import com.hrms.model.Supplier;
import com.hrms.model.Warehouse;
import com.hrms.service.EmployeeService;
import com.hrms.service.FormService;

@Controller
public class FormController {
	
	private FormService formService;

	@Autowired
	private ApplicationContext _applicationContext;

	@Autowired(required=true)
	@Qualifier(value="formService")
	public void setFormService(FormService ps){
		this.formService = ps;
	}

	@RequestMapping(value = "/form/list/{formName}", method = RequestMethod.GET)
	 public String home(Locale locale, Model model, @PathVariable("formName") String formName, 
			 HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
        
       LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
       if(!objA.isLogin()){
            return "redirect:/login";
        } 

        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute(formName+"List", mapper.writeValueAsString(this.formService.getFormListing(formName)));
        request.getSession().setAttribute("prevURL", request.getSession().getAttribute("currURL"));
        request.getSession().setAttribute("currURL", "/form/list/"+formName);
        request.getSession().setAttribute("formContext", formName);
        return "home";
    }
	
	@RequestMapping(value = "/add/{formName}", method = RequestMethod.GET)
	 public String formDetails(Model model, @PathVariable("formName") String formName, 
			 HttpServletRequest request)
	{
       
	      LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
	      if(!objA.isLogin()){
	           return "redirect:/login";
	       }
      
	     
	      String redirectPage = "";
	      if ("Supplier".equals(formName))
	      {
	    	  model.addAttribute(formName, new Supplier());
	    	  redirectPage = "supplierdetails";
	      }
	      else if ("Customer".equals(formName))
	      {
	    	  model.addAttribute(formName, new Customer());
	    	  redirectPage = "customerdetails";
	      }
	      else if ("Project".equals(formName))
	      {
	    	  model.addAttribute(formName, new Project());
	    	  redirectPage = "projectdetails";
	      }
	      else if ("Item".equals(formName))
	      {
	    	  model.addAttribute(formName, new Item());
	    	  redirectPage = "itemdetails";
	      }
	      else if ("NonInventoryItem".equals(formName))
	      {
	    	  model.addAttribute(formName, new NonInventoryItem());
	    	  redirectPage = "noninventoryitemdetails";
	      }
	      else if ("Warehouse".equals(formName))
	      {
	    	  model.addAttribute(formName, new Warehouse());
	    	  redirectPage = "warehousedetails";
	      }
	      else
	      {
	    	  redirectPage = "home";
	      }
	      
          //request.getSession().setAttribute("prevURL", request.getSession().getAttribute("currURL"));
          request.getSession().setAttribute("currURL", "/add/"+formName);
          request.getSession().setAttribute("prevURL","/form/list/"+formName);
	      
	       return redirectPage;   
       }
	
	@RequestMapping(value = "/add/Supplier", method = RequestMethod.POST)
	 public String saveSupplierForm(@ModelAttribute("Supplier") Supplier obj, 
			 HttpServletRequest request, HttpServletResponse response)
	{
      
	      LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
	      if(!objA.isLogin()){
	           return "redirect:/login";
	       }
	      String formName = "Supplier";

				//new person, add it
	      if (obj.getId() == 0)
	      {
		      int maxSeqNum = formService.getMaxSequenceNumber(formName);	   
		      obj.setCode(String.valueOf(maxSeqNum));
	    	  this.formService.addForm(obj);
	      }
	      else
	      {
	    	  obj.setCode(obj.getCode().replace("SUP", ""));
	    	  this.formService.updateForm(obj);
	      }
			String redirectPage = "";
	      if ("Supplier".equals(formName))
	    	  redirectPage = "supplierdetails";
	      else if ("Customer".equals(formName))
	    	  redirectPage = "customerdetails";
	      else if ("Projects".equals(formName))
	    	  redirectPage = "projectdetails";
	      else
	    	  redirectPage = "home";
	      
	       return redirectPage;   
      }
	
	@RequestMapping(value = "/add/Customer", method = RequestMethod.POST)
	 public String saveCustomerForm(@ModelAttribute("Customer") Customer obj, 
			 HttpServletRequest request, HttpServletResponse response)
	{
     
	      LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
	      if(!objA.isLogin()){
	           return "redirect:/login";
	       }
	      String formName = "Customer";
	      if (obj.getId() == 0)
	      {
		      int maxSeqNum = formService.getMaxSequenceNumber(formName);	   
		      obj.setCode(String.valueOf(maxSeqNum));
	    	  this.formService.addForm(obj);
	      }
	      else
	      {
	    	  obj.setCode(obj.getCode().replace("CUS", ""));
	    	  this.formService.updateForm(obj);
	      }
		String redirectPage = "customerdetails";
     
	       return redirectPage;   
     }
	
	@RequestMapping(value = "/add/Project", method = RequestMethod.POST)
	 public String saveProjectForm(@ModelAttribute("Project") Project obj, 
			 HttpServletRequest request, HttpServletResponse response)
	{
    
	      LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
	      if(!objA.isLogin()){
	           return "redirect:/login";
	       }
	      String formName = "Project";
	      if (obj.getId() == 0)
	      {
		      int maxSeqNum = formService.getMaxSequenceNumber(formName);	   
		      obj.setCode(String.valueOf(maxSeqNum));
	    	  this.formService.addForm(obj);
	      }
	      else
	      {
	    	  obj.setCode(obj.getCode().replace("PRJ", ""));
	    	  this.formService.updateForm(obj);
	      }
		  String redirectPage = "projectdetails";
	      
	       return redirectPage;   
    }
	
	@RequestMapping(value = "/add/Item", method = RequestMethod.POST)
	 public String saveItemForm(@ModelAttribute("Item") Item obj, 
			 HttpServletRequest request, HttpServletResponse response)
	{
   
	      LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
	      if(!objA.isLogin()){
	           return "redirect:/login";
	       }
	      String formName = "Item";
	      if (obj.getId() == 0)
	      {
		      int maxSeqNum = formService.getMaxSequenceNumber(formName);	   
		      obj.setCode(String.valueOf(maxSeqNum));
	    	  this.formService.addForm(obj);
	      }
	      else
	      {
	    	  obj.setCode(obj.getCode().replace("ITM", ""));
	    	  this.formService.updateForm(obj);
	      }
		  String redirectPage = "itemdetails";
	      
	       return redirectPage;   
   }
	
	@RequestMapping(value = "/add/NonInventItem", method = RequestMethod.POST)
	 public String saveNonInventoryItemForm(@ModelAttribute("NonInventoryItem") NonInventoryItem obj, 
			 HttpServletRequest request, HttpServletResponse response)
	{
  
	      LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
	      if(!objA.isLogin()){
	           return "redirect:/login";
	       }
	      String formName = "NonInventoryItem";
	      if (obj.getId() == 0)
	      {
		      int maxSeqNum = formService.getMaxSequenceNumber(formName);	   
		      obj.setCode(String.valueOf(maxSeqNum));
	    	  this.formService.addForm(obj);
	      }
	      else
	      {
	    	  obj.setCode(obj.getCode().replace("NITM", ""));
	    	  this.formService.updateForm(obj);
	      }
		  String redirectPage = "noninventoryitemdetails";
	      
	       return redirectPage;   
  }
	
	@RequestMapping(value = "/add/Warehouse", method = RequestMethod.POST)
	 public String saveWarehouseForm(@ModelAttribute("Warehouse") Warehouse obj, 
			 HttpServletRequest request, HttpServletResponse response)
	{
 
	      LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
	      if(!objA.isLogin()){
	           return "redirect:/login";
	       }
	      String formName = "Warehouse";
	      if (obj.getId() == 0)
	      {
		      int maxSeqNum = formService.getMaxSequenceNumber(formName);	   
		      obj.setCode(String.valueOf(maxSeqNum));
	    	  this.formService.addForm(obj);
	      }
	      else
	      {
	    	  obj.setCode(obj.getCode().replace("NITM", ""));
	    	  this.formService.updateForm(obj);
	      }
		  String redirectPage = "warehousedetails";
	      
	       return redirectPage;   
 }
	
	
	@RequestMapping(value = "/edit/{formName}/{Id}", method = RequestMethod.GET)
	 public String formDetails(Model model, @PathVariable("formName") String formName, @PathVariable("Id") int Id,
			 HttpServletRequest request, HttpServletResponse response)
	{
      
	      LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
	      if(!objA.isLogin()){
	           return "redirect:/login";
	       }
	      	    
	           
	      String redirectPage = "";
	      if ("Supplier".equals(formName))
	      {
	    	  model.addAttribute(formName, (Supplier)this.formService.getFormDatafromId(formName, Id));
	    	  redirectPage = "supplierdetails";
	      }
	     else if ("Customer".equals(formName))
	      {
	    	  model.addAttribute(formName, (Customer)this.formService.getFormDatafromId(formName, Id));
	    	  redirectPage = "customerdetails";
	      }
	      else if ("Project".equals(formName))
	      {
	    	  model.addAttribute(formName, (Project)this.formService.getFormDatafromId(formName, Id));
	    	  redirectPage = "projectdetails";
	      }
	      else
	      {
	    	  redirectPage = "home";
	      }
	      
          //request.getSession().setAttribute("prevURL", request.getSession().getAttribute("currURL"));
          request.getSession().setAttribute("currURL", "/edit/"+formName+"/"+Id);
          request.getSession().setAttribute("prevURL","/form/list/"+formName);
	      
	       return redirectPage;   
      }

	@RequestMapping("/delete/{formName}/{id}")
    public String deleteForm(@PathVariable("id") int id,@PathVariable("formName") String formName,
    		HttpServletRequest request, HttpServletResponse response){
		
		 LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
	      if(!objA.isLogin()){
	           return "redirect:/login";
	       }
        this.formService.deleteForm(id, formName);

        request.getSession().setAttribute("message", "Record deleted !!!");
        return "redirect:/form/list/"+formName;
    }
	
    @RequestMapping(value = "/downloadExcel/{formName}", method = RequestMethod.GET)
    public ModelAndView downloadExcel(@PathVariable("formName") String formName)
    {
   		Object w_employeeList = this.formService.getFormListing(formName);
    	ModelAndView w_excelfile =  new ModelAndView("excelDownloadView", "listEmployee", w_employeeList);
    	w_excelfile.addObject("formName", formName);
    	return w_excelfile;
    }
    
    @RequestMapping(value="/processExcelData", method = RequestMethod.POST)
    public String processExcel(Model model, @RequestParam("excelfile") MultipartFile a_excelFile, @RequestParam("formName") String a_formName)
    {
		int w_add = 0;
		int w_fail = 0;
		int w_modify = 0;
		String w_failmsg = "";
    	try
    	{
    		String[] formatStrings = {"M-d-y","M/d/y"};
    		boolean excelXLSX = false;

    		Iterator<Row> w_rowIterator = null;
    		if (a_excelFile.getSize() == 0 )
    		{
    	    	model.addAttribute("message", "No files uploaded. Please upload excel to file.");
    	    	return "importexcel";
    		}
    		if (!(a_excelFile.getOriginalFilename().endsWith(".xlsx") || a_excelFile.getOriginalFilename().endsWith(".xls")))
    		{
    	    	model.addAttribute("message", "Incorrect file format " + a_excelFile.getOriginalFilename() + ". Please upload Excel file");
    	    	return "importexcel";
    		}
    		if (a_excelFile.getOriginalFilename().endsWith(".xlsx"))
    			excelXLSX = true;

    		if (excelXLSX)
    		{
    			XSSFWorkbook w_wrkbook = new XSSFWorkbook(a_excelFile.getInputStream());
    			XSSFSheet w_wrkSheet = w_wrkbook.getSheetAt(0);
    			w_rowIterator = w_wrkSheet.iterator();
    		}
    		else
    		{
    			HSSFWorkbook w_wrkbook = new HSSFWorkbook(a_excelFile.getInputStream());
    			HSSFSheet w_wrkSheet = w_wrkbook.getSheetAt(0);
    			w_rowIterator = w_wrkSheet.iterator();
    		}

    		Date w_currentDate = new Date();
    		while (w_rowIterator.hasNext())
    		{
    			Row w_row = w_rowIterator.next();
    			boolean w_addFlag = true;
    		
    			try
    			{
    			//Create an object for Employee model.
    			Object w_formObj = null;
    			Date w_bdate = null;

    			if(w_row.getRowNum()==0){
    			   continue; //just skip the rows if row number is 0
    			 }
    			
    			DataFormatter w_dataformat = new DataFormatter();
    			int w_itemId = Integer.parseInt(("".equals(w_dataformat.formatCellValue(w_row.getCell(0))) || w_dataformat.formatCellValue(w_row.getCell(0)) == null) ? "0" : w_dataformat.formatCellValue(w_row.getCell(0)));
    			if ("Supplier".equals(a_formName))
    			{
    				Supplier w_supp = null;
    				if (w_itemId > 0)
    				{
    					w_addFlag = false;
    					w_supp = (Supplier)this.formService.getFormDatafromId(a_formName, w_itemId);
    				}
    				else
    				{
    					w_supp = new Supplier();
    				    int maxSeqNum = formService.getMaxSequenceNumber(a_formName);
    				    w_supp.setCode(String.valueOf(maxSeqNum));
    					
    				}
    				w_formObj = ExcelBuilderMain.getSupplierObjectFromExcel(w_row, w_supp, w_addFlag);
    			}
    			else if ("Customer".equals(a_formName))
    			{
    				Customer w_cus = null;
    				if (w_itemId > 0)
    				{
    					w_addFlag = false;
    					w_cus = (Customer)this.formService.getFormDatafromId(a_formName, w_itemId);
    				}
    				else
    				{
    					w_cus = new Customer();
    				    int maxSeqNum = formService.getMaxSequenceNumber(a_formName);
    				    w_cus.setCode(String.valueOf(maxSeqNum));
    					
    				}
    				w_formObj = ExcelBuilderMain.getCustomerObjectFromExcel(w_row, w_cus, w_addFlag);
    			}
				if (w_addFlag)
				{
					this.formService.addForm(w_formObj);
					w_add++;
				}
				else
				{
					this.formService.updateForm(w_formObj);
					w_modify++;
				}
    			}
				catch(Exception ex)
				{
					ex.printStackTrace();
					w_fail++;
					w_failmsg += w_row.getRowNum() + " , ";
				}

    		}
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
        	model.addAttribute("message", "Some problem while importing... Please upload correct file");
    	}
    	String w_message = "<br>Data imported sucessfully<br>Add : "+ w_add +
				"  Modify : " + w_modify + "  Fail : " + w_fail;
    	if (w_fail > 0)
    		w_message += "<br>Error in following rows No: " + w_failmsg;

    	model.addAttribute("message", w_message);
    	return "importexcel";
    }


}
