package com.smart.controlloer;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.Userreposistry;
import com.smart.entities.user;
@Controller
public class HomeController {
	@Autowired
	private Userreposistry userreposistry;
	
	//Home handler
	@RequestMapping("/")
	public String home(Model model) 
	{
		model.addAttribute("title","Home - Smart Contact Manager");
		return "home";	
	}
	
	
	//About Handler
	@RequestMapping("/about")
	public String about(Model model)
	{
	model.addAttribute("title","About - Smart Contact Manager");
		return "about";
	}
	

	@RequestMapping("/signup")
	public String signup(Model model)
	{
	model.addAttribute("title","Register - Smart Contact Manager");
	model.addAttribute("user",new user());
		return "signup";
	}
	//handler for
	@RequestMapping(value = "/do_register",method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") user User,@RequestParam(value = "agreement",defaultValue = "false")boolean agreement,Model model) 
	{
		if(!agreement) {
			System.out.println("if you are not agreed the terms and conditions");
			
		}
		
		User.setRole("ROLE_USER");
		User.setEnabled(true);
		User.setImageUrl("default.png");
		
		
		System.out.println("Agreement"+agreement);
		System.out.println("USER"+User);
		user result =this.userreposistry.save(User);
		
		model.addAttribute("user",result);
		return"signup";
	}
	
	
}

