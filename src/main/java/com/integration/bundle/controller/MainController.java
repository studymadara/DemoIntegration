package com.integration.bundle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController 
{
	//this file will have 3 things
	//1.will show page when no session is detected
	//2.call to another page which requires login
	//3.in another page give logout button after that back to step 1
	
	@GetMapping("/loginPage")
	public String showPage()
	{
		return "loginPage.html";
	}
	
	@GetMapping("/shadyPage")
	public String shadyPage()
	{
		return "shadyPage.html";
	}

}
