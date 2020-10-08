package com.integration.bundle.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController 
{
	//this file will have 3 things
	//1.will show page when no session is detected
	//2.call to another page which requires login
	//3.in another page give logout button after that back to step 1
	
	@GetMapping("/loginPage")
	public ModelAndView showPage()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("loginPage");
		return modelAndView;
	}

	@GetMapping("/shadyPage")
	public ModelAndView shadyPage(@AuthenticationPrincipal OAuth2User user){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("finalShadyPage");
		modelAndView.addObject("username",user.getAttributes().get("name"));
		modelAndView.addObject("emailId",user.getAttributes().get("email"));
		return modelAndView;
	}
}
