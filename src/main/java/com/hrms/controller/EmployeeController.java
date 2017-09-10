package com.hrms.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hrms.model.Employee;
import com.hrms.model.Interview;
import com.hrms.model.LoginBean;
import com.hrms.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	private ApplicationContext _applicationContext;

	@Autowired(required=true)
	@Qualifier(value="employeeService")
	public void setEmployeeService(EmployeeService ps){
		this.employeeService = ps;
	}

	@RequestMapping(value ="/checkEmail", method = RequestMethod.GET)
	public @ResponseBody String checkEmail(@RequestParam String email) {
	    int a = this.employeeService.checkEmail(email);
	    if (a != 0) {
	        return "true";
	    } else {
	        return "false";
	    }
	}

	@RequestMapping(value = "/contactUS", method = RequestMethod.GET)
	public String listEmployees(Model model) {
		if(!checkLoginStatus()) return "redirect:/login" ;
		/*model.addAttribute("employee", new Employee());
		model.addAttribute("listEmployees", this.employeeService.listEmployees());*/
		return "contactUS";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchEmployees(Model model) {
		String modelName  = (checkLoginStatus()) ? "search" :"redirect:/login" ;
		return modelName;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchEmployee(Model model,HttpServletRequest request, HttpServletResponse response) {
		if(!checkLoginStatus()) return "redirect:/login" ;
		List<Employee> listEmployees = this.employeeService.searchEmployees(request,response);
		StringBuilder sb = new StringBuilder();
		Enumeration<String> parameterNames = request.getParameterNames();
		int loop = 1;
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if(paramValues.length > 1 || !paramValues[0].equals("")){
				if (loop == 1) {sb.append(" Searched for : ");} else {sb.append(" , ");}
            	for (int i = 0; i < paramValues.length; i++) {
                    sb.append(paramName + " = " + paramValues[i]);
            	}
            	loop++;
            }
        }
        if(listEmployees.isEmpty()){
        	model.addAttribute("errmsg", "No search results found !!");
        }
        model.addAttribute("msg", sb);
		model.addAttribute("listEmployees", listEmployees);
		return "search";
	}

	@RequestMapping(value = "/addemployee", method = RequestMethod.GET)
	public String addDetails(Model model) {
		if(!checkLoginStatus()) return "redirect:/login" ;
		model.addAttribute("employee", new Employee());
		model.addAttribute("listEmployees", this.employeeService.listEmployees());
		return  "employeedetail";
	}

	//For add and update person both
	@RequestMapping(value= "/employee/add", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employee") Employee p,HttpServletRequest request, HttpServletResponse response){
		if(!checkLoginStatus()) return "redirect:/login" ;
		String modelName ;
		if(p.getId() == 0){
			//new person, add it
			this.employeeService.addEmployee(p);
			modelName = "redirect:/";
			request.getSession().setAttribute("message", "Data saved successfully !!!");
		}else{
			//existing person, call update
			this.employeeService.updateEmployee(p);
			modelName = "redirect:/edit/"+p.getId();
			request.getSession().setAttribute("message", "Data updated successfully !!!");
		}

		return modelName;

	}

	//For add and update person both
	@RequestMapping(value = "/interview/add", method = RequestMethod.POST)
	public String addInterview(@ModelAttribute("interview") Interview p, BindingResult result,HttpServletRequest request, HttpServletResponse response) {

		p.setEmployee(this.employeeService.getEmployeeById(p.getEmpId()));
		if(p.getInterviewId()!= null){
			p.setId(p.getInterviewId());
		}
		if (p.getId() == 0) {
			// new interview, add it
			this.employeeService.addInterview(p);
		} else {
			// existing interview, call update
			this.employeeService.updateInterview(p);
		}

		return "redirect:/edit/"+p.getEmpId()+"#job" ;

	}

	@RequestMapping("/remove/{id}")
    public String removeEmployee(@PathVariable("id") int id,HttpServletRequest request, HttpServletResponse response){
		if(!checkLoginStatus()) return "redirect:/login" ;
        this.employeeService.removeEmployee(id);

        // Creating the directory to store file
   		String rootPath = System.getProperty("catalina.home");
   		//String rootPath = request.getSession().getServletContext().getRealPath("/");
   		File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + "E" + id + "_Files");
   		try
   		{
   			FileUtils.deleteDirectory(dir);
   		}
   		catch(Exception e){}
        request.getSession().setAttribute("message", "Record deleted !!!");
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") int id, Model model, HttpServletRequest request, HttpServletResponse response){
    	if(!checkLoginStatus()) return "redirect:/login" ;
    	model.addAttribute("interview", new Interview());
        model.addAttribute("employee", this.employeeService.getEmployeeById(id));
        model.addAttribute("listEmployees", this.employeeService.listEmployees());
        request.getSession().setAttribute("prevURL", request.getSession().getAttribute("currURL"));
        request.getSession().setAttribute("currURL", "edit/" + id);
        
        return "employeedetail";
    }

    @RequestMapping("/view/{id}")
    public String viewEmployee(@PathVariable("id") int id, Model model){
    	if(!checkLoginStatus()) return "redirect:/login" ;
        model.addAttribute("employee", this.employeeService.getEmployeeById(id));
        return  "viewemployee";
    }

    @RequestMapping(value="/importexcel", method = RequestMethod.GET)
    public String HelloWorld1(Model model)
    {
    	return "importexcel";
    }

    @RequestMapping(value="/processExcel", method = RequestMethod.POST)
    public String processExcel(Model model, @RequestParam("excelfile") MultipartFile a_excelFile)
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

    		while (w_rowIterator.hasNext())
    		{
    			Row w_row = w_rowIterator.next();

    			//Create an object for Employee model.
    			Employee w_empObj = null;
    			Date w_bdate = null;

    			if(w_row.getRowNum()==0){
    			   continue; //just skip the rows if row number is 0
    			 }
	    		int w_idx = 0;
				//Sets the read data to the model class.
	    		Date w_currentDate = new Date();
	    		DataFormatter w_dataformat = new DataFormatter();

	    		try
	    		{
	    			String w_email = w_dataformat.formatCellValue(w_row.getCell(6));
	    			int w_employeeId = this.employeeService.checkEmail(w_email);
	    			if (w_employeeId > 0)
	    				w_empObj = this.employeeService.getEmployeeById(w_employeeId);
	    			else
	    				w_empObj = new Employee();

	    			for (String formatString : formatStrings)
    			    {
    			        try
    			        {
    			        	String dateStr = w_dataformat.formatCellValue(w_row.getCell(5));
    			        	w_bdate = new SimpleDateFormat(formatString).parse(dateStr);
    			        }
    			        catch (ParseException e) {}
    			    }

					w_empObj.setName(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setMiddlename(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setLastname(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_idx++;//skip creationdate column
					w_empObj.setGender(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setBirthdate(w_bdate); w_idx++;
					w_empObj.setEmail(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setContact1(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setContact2(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setAddress(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setCity(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setState(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setMaritialstatus(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setSpousename(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setEducation(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setExprience(Double.valueOf(w_dataformat.formatCellValue(w_row.getCell(w_idx++))));
					w_empObj.setDesignation(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setDepartment(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setIndustry(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setSalary(Double.valueOf(w_dataformat.formatCellValue(w_row.getCell(w_idx++))));
					w_empObj.setWorknature(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setAreaofwork(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setPrefworkinterest(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setPrefworkloc(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));
					w_empObj.setExpectedsal(Double.valueOf(w_dataformat.formatCellValue(w_row.getCell(w_idx++))));
					w_empObj.setReference(w_dataformat.formatCellValue(w_row.getCell(w_idx++)));


					if (w_employeeId > 0)
					{
						this.employeeService.updateEmployee(w_empObj);
						w_modify++;
					}
					else
					{
						w_empObj.setCreationdate(w_currentDate);
						this.employeeService.addEmployee(w_empObj);

						w_add++;
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

    public boolean checkLoginStatus(){
    	LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
        return objA.isLogin();
    }

    @RequestMapping(value="/edit/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile[] files, @RequestParam("empId") String empId,
    		@RequestParam("type") String type, HttpServletRequest request,
    		HttpServletResponse response, Model model) throws MaxUploadSizeExceededException
    {
    	//model.addAttribute("message", "Welcome to String MVC Framework");

    	Employee w_employee = this.employeeService.getEmployeeById(Integer.parseInt(empId));
		String message = "";
		String w_attachFiles = w_employee.getDocdetails();
		int w_fileCount = 0;
		if (w_attachFiles == null)
			w_attachFiles = "";

		String w_profilepic = "";
		for (int i = 0; i < files.length; i++)
		{
			MultipartFile w_file = files[i];
			String w_filedetails = "";
			String w_filename1 = w_file.getOriginalFilename();
			if ("profile".equals(type))
				w_filename1 = "E" + empId + "_profile.png";

			boolean w_alreadyExists = false;
			try
			{
				byte[] bytes = w_file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				//String rootPath = request.getSession().getServletContext().getRealPath("/");
				File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + "E" + empId + "_Files");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + w_filename1);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				w_profilepic = serverFile.getPath();
				w_profilepic = serverFile.getAbsolutePath();
				if (w_attachFiles != null)
				{
					ArrayList<String> w_fileList = new ArrayList<String>(Arrays.asList(w_attachFiles.split("@@@")));
					for (String w_fileData : w_fileList)
					{
						if (w_filename1.equals(w_fileData))
						{
							w_alreadyExists = true;
							break;
						}
					}
				}

				if (!w_alreadyExists)
				{
					w_filedetails = w_filename1; //+ "!!!" + w_file.getSize();
					w_attachFiles += w_filedetails + "@@@";
				}
				w_fileCount++;
			}
			catch (Exception e)
			{
				//return "You failed to upload " + w_filename1 + " => " + e.getMessage();
				e.printStackTrace();
			}
		}
		//w_attachFiles = w_attachFiles.substring(0, w_attachFiles.length() - 3);
		if ("profile".equals(type))
		{
			w_employee.setPhoto(w_profilepic);
			message = w_profilepic;
		}
		else
		{
			message = w_fileCount + (w_fileCount > 1 ? " files" : " file" ) + " has been uploaded successfuly";
			w_employee.setDocdetails(w_attachFiles);
		}
		this.employeeService.updateEmployee(w_employee);
		return message + "@@@" + w_attachFiles;
    }

    @RequestMapping(value="/edit/deletefile", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFile(@RequestParam("filename") String filename, @RequestParam("empId") String empId,
    		HttpServletResponse response, Model model)
    {
    	Employee w_employee = this.employeeService.getEmployeeById(Integer.parseInt(empId));
    	String w_attachFiles = w_employee.getDocdetails();
    	String w_currAttachFiles = "";
    	String message = "";
		if (w_attachFiles == null)
			w_attachFiles = "";

		// Creating the directory to store file
		String rootPath = System.getProperty("catalina.home");
		File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + "E" + empId + "_Files");

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath()
				+ File.separator + filename);

		try
		{
			if (serverFile.delete())
			{
				message = filename + " is deleted";
				ArrayList<String> w_fileList = new ArrayList<String>(Arrays.asList(w_attachFiles.split("@@@")));
				for (String w_fileData : w_fileList)
				{
					if (!filename.equals(w_fileData))
					{
						w_currAttachFiles += w_fileData + "@@@";
					}
				}
			}
			else
			{
				message = "Delete operation failed";
				w_currAttachFiles = w_attachFiles;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		w_employee.setDocdetails(w_currAttachFiles);
		this.employeeService.updateEmployee(w_employee);
    	return message + "@@@" + w_currAttachFiles;
    }

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel()
    {
    	List<Employee> w_employeeList = this.employeeService.listEmployees();
    	return new ModelAndView("excelView", "listEmployee", w_employeeList);
    }

    @RequestMapping(value = "/edit/imageController/{empId}")
    @ResponseBody
    public byte[] imageProcess(@PathVariable int empId)
    {
    	Employee w_employee = this.employeeService.getEmployeeById(empId);
    	byte[] imageBytes = null;
    	try
    	{
	    	File fnew = new File(w_employee.getPhoto());
	    	BufferedImage orgImage = ImageIO.read(fnew);
	    	ByteArrayOutputStream baos =  new ByteArrayOutputStream();
	    	ImageIO.write(orgImage, "jpg", baos);
	    	baos.flush();
	    	imageBytes = baos.toByteArray();
	    	baos.close();

    	}
    	catch(Exception e)
    	{}

    	return imageBytes;
    }

    @RequestMapping(value="exportData", method = RequestMethod.POST)
    public ModelAndView exportData(HttpServletRequest request, HttpServletResponse response)
    {
    	//String name = request.getParameter("name");
    	List<Employee> listEmployees = this.employeeService.searchEmployees(request,response);
    	response.setHeader("Content-Disposition", "attachment; filename=CandidateLists.xls");
    	response.setContentType("application/x-download");

    	ModelAndView w_excelfile =  new ModelAndView("excelView", "listEmployee", listEmployees);
    	return w_excelfile;
    }

    @RequestMapping("/edit/downloadfile/{id}/{filename}/")
    public void downloadFile(@PathVariable("id") int empId,@PathVariable("filename") String filename,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {

    	 int BUFFER_SIZE = 4096;
    	// Creating the directory to store file
    			String rootPath = System.getProperty("catalina.home");
    			File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + "E" + empId + "_Files");

    			// Create the file on server
    			File serverFile = new File(dir.getAbsolutePath()
    					+ File.separator + filename);
    			FileInputStream inputStream = new FileInputStream(serverFile);

    			// get MIME type of the file
    	        String mimeType  = "application/octet-stream";

    	        //set content attributes for response
    	        response.setContentType(mimeType);
    	        response.setContentLength((int)serverFile.length());

    	        //set headers for response
    	        String headerKey = "Content-Disposition";
    	        String headerValue = String.format("attachment; filename=\"%s\"",
    	        		serverFile.getName());
    	        response.setHeader(headerKey, headerValue);

    	        // get output stream of the response
    	        OutputStream outStream = response.getOutputStream();

    	        byte[] buffer = new byte[BUFFER_SIZE];
    	        int bytesRead = -1;

    	        // write bytes read from the input stream into the output stream
    	        while ((bytesRead = inputStream.read(buffer)) != -1) {
    	            outStream.write(buffer, 0, bytesRead);
    	        }

    	        inputStream.close();
    	        outStream.close();

    }
}
