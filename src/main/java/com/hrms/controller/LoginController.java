package com.hrms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hrms.model.LoginBean;
import com.hrms.service.UserServiceImpl;



@Controller
public class LoginController
{
		@Autowired
		private UserServiceImpl loginDelegate;

		@Autowired
		private ApplicationContext _applicationContext;



		@RequestMapping(value="/login",method=RequestMethod.GET)
		public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
		{
			ModelAndView model = new ModelAndView("login");
			//LoginBean loginBean = new LoginBean();
			model.addObject("loginBean", loginBean);
			return model;
		}
		@RequestMapping(value="/login",method=RequestMethod.POST)
		public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean)
		{
				ModelAndView model= null;
				try
				{
						boolean isValidUser = loginDelegate.isValidUser(loginBean.getUsername(), loginBean.getPassword());
						if(isValidUser)
						{
								request.setAttribute("loggedInUser", loginBean.getUsername());
								setLoginDetails(true);
								model = new ModelAndView("redirect:/");
						}
						else
						{
								model = new ModelAndView("login");
								request.setAttribute("message", "Invalid credentials!!");
						}

				}
				catch(Exception e)
				{
						e.printStackTrace();
				}

				return model;
		}

		@RequestMapping(value="/logout",method=RequestMethod.GET)
		public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
		{
			ModelAndView model = new ModelAndView("redirect:/login");
			//LoginBean loginBean = new LoginBean();
			setLoginDetails(false);
			request.setAttribute("message", "Logged out successfully!!");
			model.addObject("loginBean", loginBean);
			return model;
		}

		public void setLoginDetails(boolean loginStatus){
			LoginBean objA = (LoginBean) _applicationContext.getBean("loginDetails");
		    objA.setLogin(loginStatus);
		}
}
