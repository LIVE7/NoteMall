package com.project.notemall.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model m) {
		System.out.println("main¿äÃ» µé¾î¿È");
		return "main";
	}
	
	/**
	 * localhost:9090/jobs/home
	 */
	@GetMapping("/home")
	public String home2(Model m) {
		System.out.println("home¿äÃ» µé¾î¿È");
		return "main";
	}
	
	@GetMapping("/admin/home")
	public String homeAdmin(Model m) {
		System.out.println("home¿äÃ» µé¾î¿È");
		return "main";
	}
	
	@GetMapping("/user/home")
	public String homeUser(Model m) {
		System.out.println("home¿äÃ» µé¾î¿È");
		return "main";
	}
	
	@RequestMapping("/top")
	public void showTop() {
		
	}
	
	@RequestMapping("/foot")
	public void showFoot() {
		
	}
	
	/**contact*/
	@GetMapping("/contact")
	public String contact(Model m) {
		System.out.println("contact¿äÃ» µé¾î¿È");
		return "contact";
	}
	
	/**about*/
	@GetMapping("/about")
	public String about(Model m) {
		System.out.println("about¿äÃ» µé¾î¿È");
		return "about";
	}
	
	/**notice*/
	@GetMapping("/notice")
	public String notice(Model m) {
		System.out.println("notice¿äÃ» µé¾î¿È");
		return "notice";
	}
	
	/**event*/
	@GetMapping("/event")
	public String event(Model m) {
		System.out.println("event¿äÃ» µé¾î¿È");
		return "event";
	}
	
	//////////////////////////////
	
}
