package com.yuen.controller;


import com.yuen.domain.User;
import com.yuen.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@ModelAttribute("user")
	public User user(){
		return userDetailsService.getCurrentUser();
	}

	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/admin") 
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/khongcoquyen")
	public String accessDenied() {
		return "403";
	}
	
	@GetMapping("/login") 
	public String getLogin() {
		return "login";
	}

	@GetMapping("/dangnhapdi")
	public String dangnhapdi(){
		System.out.println(user().getEmail());
		return "demo";
	}
	
}
