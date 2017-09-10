package com.hrms.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hrms.model.LoginBean;
import com.hrms.service.EmployeeService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class JQueryDatatableController {

    private static final Logger logger = LoggerFactory.getLogger(JQueryDatatableController.class);

    private EmployeeService employeeService;

	@Autowired
	private ApplicationContext _applicationContext;

	@Autowired(required=true)
	@Qualifier(value="employeeService")
	public void setEmployeeService(EmployeeService ps){
		this.employeeService = ps;
	}

    /**
     * Simply selects the home view to render by returning its name.
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model,HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
        logger.info("Welcome home! The client locale is {}.", locale);

       LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
       if(!objA.isLogin()){
            return "redirect:/login";
        }

        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("employeeList", mapper.writeValueAsString(this.employeeService.listEmployees()));
        request.getSession().setAttribute("prevURL", request.getSession().getAttribute("currURL"));
        request.getSession().setAttribute("currURL", "");
        return "home";
    }

}