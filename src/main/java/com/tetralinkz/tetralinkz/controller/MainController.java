package com.tetralinkz.tetralinkz.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tetralinkz.tetralinkz.models.User;
import com.tetralinkz.tetralinkz.services.MainService;

@Controller
@RequestMapping("/")
public class MainController {
	//DEPENDENCY INJECTION
	@Autowired
	private MainService mainService;
	
	public MainController (MainService mainService) {
		this.mainService = mainService;
	}
	
	// RESTFUL ROUTES //
	
	// // // / //
	// USERS  //
	// // // //
	
	// Registration || Login page render
	@GetMapping("/landing")
	public String landingRender(@ModelAttribute("user")User user) {
		return "index.jsp";
	}
	
	// Show admin page
	@GetMapping("/admin")
	public String adminPage() {
		return "admin.jsp";
	}
	
	// Show Dashboard
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard.jsp";
	}

	// CREATE NEW USER //
	@PostMapping("/users/register")
	public String registerUser(
			@Valid @ModelAttribute("newUser")User user,
			BindingResult result,
			HttpSession session
			) {
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			String pw1 = user.getPassword();
			String pw2 = user.getPasswordConfirm();
			boolean checkPw = (pw1 == pw2);
			if(checkPw == false) {
				return "redirect:/users/register/errConfirm";
			} else {
				mainService.registerUser(user);
				session.setAttribute("user", user);
				return "redirect:/dashboard";
			}
		}
		
	}
	
	// PASSWORD CONFIRMATION ERROR
	@GetMapping("/users/register/errConfirm")
	public String flashMessagePW(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorPw", "Passwords do not match!");
        return "redirect:/landing";
	}
	
	// LOGIN -- Existing User
	@PostMapping("/users/login")
	public String login(
			@RequestParam("email")String email,
			@RequestParam("password")String password,
			HttpSession session
			) {
		if(mainService.authenticateUser(email, password)) {
			User user = mainService.findByEmail(email);
			session.setAttribute("user", user);
			return "redirect:/dashboard";
		} else {
			return "redirect:/users/loginErr";
		}
	}
	
	//LOGIN ERROR
	@GetMapping("/users/loginErr")
	public String flashMessageLogin(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorLogin", "Username and/or Password are invalid");
        return "redirect:/";
	}
	
	// END CONTROLLER
}
