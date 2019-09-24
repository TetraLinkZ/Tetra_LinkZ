package com.tetralinkz.tetralinkz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tetralinkz.tetralinkz.models.Avatar;
import com.tetralinkz.tetralinkz.models.Token;
import com.tetralinkz.tetralinkz.models.User;
import com.tetralinkz.tetralinkz.services.MainService;

@Controller
@RequestMapping("/")
public class MainController {
	// DEPENDENCY INJECTION
	@Autowired
	private MainService mainService;

	public MainController(MainService mainService) {
		this.mainService = mainService;
	}

	// RESTFUL ROUTES //

	// // // / //
	// USERS //
	// // // //

	// Registration || Login page render
	@GetMapping("/landing")
	public String landingRender(@ModelAttribute("newUser") User newUser, @ModelAttribute("login") User user) {
		return "landing.jsp";
	}

	// Show admin page
	@GetMapping("/admin")
	public String adminPage() {
		return "admin.jsp";
	}

	// Show Dashboard
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");

    	model.addAttribute("userInfo", user);
		return "dashboard.jsp";
	}

	// CREATE NEW USER //
	@PostMapping("/users/register")
	public String registerUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "landing.jsp";
		} else {
			String pw1 = user.getPassword();
			String pw2 = user.getPasswordConfirm();
			boolean checkPw = (pw1.equals(pw2));
			System.out.println("p1:"+ pw1 +"p2:"+ pw2);
			if (checkPw == false) {
				System.out.println("pw false");

				return "redirect:/users/register/errConfirm";
			} else {
				System.out.println("pw true");
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
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		if (mainService.authenticateUser(email, password)) {
			User user = mainService.findByEmail(email);
			session.setAttribute("user", user);
			return "redirect:/dashboard";
		} else {
			return "redirect:/users/loginErr";
		}
	}

	// LOGIN ERROR
	@GetMapping("/users/loginErr")
	public String flashMessageLogin(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorLogin", "Username and/or Password are invalid");
		return "redirect:/";
	}

	// The Items Page
	@GetMapping("/items")
	public String showItems() {
		return "items.jsp";
	}
	
	// The Ranking page
	@GetMapping("/ranking")
	public String showRanking() {
		return "ranking.jsp";
	}
	
	// //  // //
	// ADMIN //
	// // / //
	
	// Index all Avatars
	@GetMapping("/admin/avatars")
	public String indexAvatars(Model model) {
		List<Avatar> avatars = mainService.allAvatars();
		model.addAttribute("avatars", avatars);
		return "adminPages/avatars.jsp";
	}
	
	// Index all Tokens
	@GetMapping("/admin/tokens")
	public String indexTokens(Model model) {
		List<Token> tokens = mainService.allTokens();
		model.addAttribute("tokens", tokens);
		return "adminPages/tokens.jsp";
	}
	
	// Edit Avatar Admin
	@GetMapping("/admin/avatars/{id}/edit")
	public String editAvatar(@PathVariable(value="id")Long id, Model model) {
		Avatar avatar = mainService.getAvatarById(id);
		model.addAttribute("avatar", avatar);
		return "/adminPages/editAvatar.jsp";
	}
	
	//Edit Token Admin
	@GetMapping("/admin/tokens/{id}/edit")
	public String editToken(@PathVariable(value="id")Long id, Model model) {
		Token token = mainService.getTokenById(id);
		model.addAttribute("token", token);
		return "/adminPages/editToken.jsp";
	}
	
	//Edit User Admin
	@GetMapping("/admin/user/{id}/edit")
	public String editUser() {
		return "/adminPages/editUser.jsp";
	}
	
	//New Avatar Admin
	@GetMapping("/admin/newAvatar")
	public String newAvatar(@ModelAttribute("avatar")Avatar avatar){
		return "/adminPages/newAvatar.jsp";
	}
	
	//New Token Admin
	@GetMapping("/admin/newToken")
	public String newToken(@ModelAttribute("token")Token token) {
		return "/adminPages/newToken.jsp";
	}
	
	// ADMIN - POST && PUT CONTROLS //
	
	@PostMapping("/admin/tokens")
	public String createToken(
			@Valid @ModelAttribute("token") Token token,
			BindingResult result
			) {
		if(result.hasErrors()) {
			return "/adminPages/newToken.jsp";
		} else {
			mainService.createOrUpdateToken(token);
			return "redirect:/admin/tokens";
		}
	}
	
	@PostMapping("/admin/avatars")
	public String createAvatar(
			@Valid @ModelAttribute("avatar") Avatar avatar,
			BindingResult result
			) {
		if(result.hasErrors()) {
			return "/adminPages/newAvatar.jsp";
		} else {
			mainService.createOrUpdateAvatar(avatar);
			return "redirect:/admin/avatars";
		}
	}
	// UPDATE AVATAR
	@PutMapping("/admin/avatars/{id}/update")
	public String updateAvatar(
			@Valid @ModelAttribute("avatar") Avatar avatar,
			@PathVariable(value="id")Long id,
			BindingResult result
			) {
		if(result.hasErrors()) {
			return "/adminPages/editAvatar.jsp";
		} else {
			mainService.createOrUpdateAvatar(avatar);
			return "redirect:/admin/avatars";
		}
	}
	// UPDATE TOKEN
	@PutMapping("/admin/tokens/{id}/update")
	public String updateToken(
			@Valid @ModelAttribute("token") Token token,
			@PathVariable(value="id")Long id,
			BindingResult result
			) {
		if(result.hasErrors()) {
			return "/adminPages/editToken.jsp";
		} else {
			mainService.createOrUpdateToken(token);
			return "redirect:/admin/tokens";
		}
	}
	// // // // // // //
	// END CONTROLLER
}
