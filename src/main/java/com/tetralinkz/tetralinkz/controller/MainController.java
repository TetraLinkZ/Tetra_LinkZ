package com.tetralinkz.tetralinkz.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tetralinkz.tetralinkz.models.Avatar;
import com.tetralinkz.tetralinkz.models.PrivateMessage;
import com.tetralinkz.tetralinkz.models.Token;
import com.tetralinkz.tetralinkz.models.User;
import com.tetralinkz.tetralinkz.models.UserAvatar;
import com.tetralinkz.tetralinkz.models.UserToken;
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
	    Long id = (Long) session.getAttribute("user");
	    if(id!= null) {
		User user = mainService.findUserById(id);
		List<User> friendList = mainService.allfriend(user);
		model.addAttribute("userInfo", user);
		model.addAttribute("friendList", friendList);
			return "dashboard.jsp"; 
		} else {
		    return "redirect:/landing";
		}		
	}

	// Show friend's profile
	@PostMapping("/showProfile")
	public String showProfile(@RequestParam("friendId") Long fid, Model model) {
		User user = mainService.findUserById(fid);
		model.addAttribute("userInfo", user);
		return "showProfile.jsp";
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
			System.out.println("p1:" + pw1 + "p2:" + pw2);
			if (checkPw == false) {
				System.out.println("pw false");

				return "redirect:/users/register/errConfirm";
			} else {
				System.out.println("pw true");
				mainService.registerUser(user);
				Avatar defaultAvatar = mainService.findAvatar(Long.valueOf(1));
				Token defaultToken = mainService.findToken(Long.valueOf(1));
				mainService.defaultAvatar(user, defaultAvatar);
				mainService.defaultToken(user, defaultToken);
				mainService.updateCredit(user, 300);
				Random r = new Random();
				int randomCode = r.nextInt(1000000);
				mainService.generateCode(user, randomCode);
				session.setAttribute("user", user.getId());
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
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		if (mainService.authenticateUser(email, password)) {
			User user = mainService.findByEmail(email);
			session.setAttribute("user", user.getId());
			return "redirect:/dashboard";
		} else {
			return "redirect:/users/loginErr";
		}
	}

	// LOGIN ERROR
	@GetMapping("/users/loginErr")
	public String flashMessageLogin(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorLogin", "Username and/or Password are invalid");
		return "redirect:/landing";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/landing";
	}

	// The Items Page
	@GetMapping("/items")
	public String showItems(Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			List<UserAvatar> ownedAvatar = mainService.userOwnedAvatar(user);
			List<UserToken> ownedToken = mainService.userOwnedToken(user);
			model.addAttribute("userInfo", user);
			model.addAttribute("ownedAvatar", ownedAvatar);
			model.addAttribute("ownedToken", ownedToken);
			return "items.jsp";
		} else {
		    return "redirect:/landing";
		}		
	}

	// Change the current Avatar
	@PostMapping("/setCurrentAvatar")
	public String setCurrentAvatar(@RequestParam("avatarId") Long aid, HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {			
			User user = mainService.findUserById(uid);
			Avatar avatar = mainService.getAvatarById(aid);
			mainService.setCurrentAvatar(user, avatar);
			return "redirect:/dashboard";
		} else {
		    return "redirect:/landing";
		}		
	}

	// Change the current Token
	@PostMapping("/setCurrentToken")
	public String setCurrentToken(@RequestParam("tokenId") Long tid, HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			Token token = mainService.getTokenById(tid);
			mainService.setCurrentToken(user, token);
			return "redirect:/dashboard";
		} else {
		    return "redirect:/landing";
		}		
	}

	// The Gacha Function
	@PostMapping("/gacha")
	@ResponseBody
	public String gacha(HttpSession session) {
		String newItem = "";
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			if (user.getCredits() >= 100) {
				Random avatarOrToken = new Random();
				int aot = avatarOrToken.nextInt(2);
				if (aot == 0) {
					List<Avatar> al = mainService.allAvatars();
					int avatarPool = al.size();
					Random randomAvatar = new Random();
					int rA = randomAvatar.nextInt(avatarPool) + 1;
					Avatar newAvatar = mainService.findAvatar(Long.valueOf(rA));
					mainService.gachaAvatar(user, newAvatar);
					mainService.updateCredit(user, -100);
					mainService.boxBought(user);
					newItem = newAvatar.getUrl();
				} else if (aot == 1) {
					List<Token> tl = mainService.allTokens();
					int tokenPool = tl.size();
					Random randomToken = new Random();
					int rT = randomToken.nextInt(tokenPool) + 1;
					Token newToken = mainService.findToken(Long.valueOf(rT));
					mainService.gachaToken(user, newToken);
					mainService.updateCredit(user, -100);
					mainService.boxBought(user);
					newItem = newToken.getUrl();
				}
			}
			return newItem;
		} else {
		    return "redirect:/landing";
		}		
	}

	// The Ranking page
	@GetMapping("/ranking")
	public String showRanking(Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			model.addAttribute("userInfo", user);
			return "ranking.jsp";
		} else {
		    return "redirect:/landing";
		}		
	}

	// Add Friends
	@PostMapping("/addFriend")
	public String addFriend(@RequestParam("add") String code, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			try {
		        int theCode = Integer.parseInt(code);
		        mainService.addFriend(user, theCode);
		        return "redirect:/dashboard";
		    }
		    catch(NumberFormatException e) {
		        return "redirect:/dashboard";
		    }
		} else {
		    return "redirect:/landing";
		}
	}

	// the shop
	@GetMapping("/shop")
	public String showShop(HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			List<Avatar> allAvatar = mainService.allAvatars();
			List<Token> allToken = mainService.allTokens();
			model.addAttribute("allAvatar", allAvatar);
			model.addAttribute("allToken", allToken);
			model.addAttribute("userInfo", user);
			return "shop.jsp";
		} else {
		    return "redirect:/landing";
		}		
	}

	// Buy an new Avatar
	@PostMapping("/buyAvatar")
	public String buyAnAvatar(@RequestParam("avatarId") Long aid, HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			Avatar avatar = mainService.findAvatar(aid);
			Integer cost = avatar.getCost() * -1;
			mainService.gachaAvatar(user, avatar);
			mainService.updateCredit(user, cost);
			return "redirect:/items";
		} else {
		    return "redirect:/landing";
		}	
	}

	// Buy an new Token
	@PostMapping("/buyToken")
	public String buyAnToken(@RequestParam("tokenId") Long tid, HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			Token token = mainService.findToken(tid);
			Integer cost = token.getCost() * -1;
			mainService.gachaToken(user, token);
			mainService.updateCredit(user, cost);
			return "redirect:/items";
		} else {
		    return "redirect:/landing";
		}		
	}

	// sell an Avatar
	@DeleteMapping("/sellAvatar")
	public String sellAvatar(@RequestParam("avatarId") Long aid, @RequestParam("userAvatarId") Long id,
			HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			UserAvatar userAvatar = mainService.findUserAvatar(id);
			Avatar avatar = mainService.findAvatar(aid);
			Integer cost = avatar.getCost();
			mainService.deleteAvatar(userAvatar);
			mainService.updateCredit(user, cost/2);
			return "redirect:/items";
		} else {
		    return "redirect:/landing";
		}		
	}

	// sell an Token
	@DeleteMapping("/sellToken")
	public String sellToken(@RequestParam("tokenId") Long tid, @RequestParam("userTokenId") Long id,
			HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			UserToken userToken = mainService.findUserToken(id);
			Token token = mainService.findToken(tid);
			Integer cost = token.getCost();
			mainService.deleteToken(userToken);
			mainService.updateCredit(user, cost/2);
			return "redirect:/items";
		} else {
		    return "redirect:/landing";
		}		
	}

	// PRIVATE MESSAGING
	@GetMapping("/chat/{friendId}")
	public String privateChat(@PathVariable("friendId") Long id, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("user");
		if(uid != null) {
			User user = mainService.findUserById(uid);
			User friend = mainService.findUserById(id);
			List<PrivateMessage> messages = mainService.findMessages(user, friend);
			model.addAttribute("messages", messages);
			model.addAttribute("friend", friend);
			model.addAttribute("user", user);	
			return "message.jsp";
		} else {
		    return "redirect:/landing";
		}
	}

	@PostMapping("/chat/{friendId}")
	@ResponseBody
	public void sendPrivateMessage(@PathVariable("friendId") Long id, HttpSession session,
			@RequestParam("user_id")String uId, @RequestParam("friend_id") Long fId,
			@RequestParam("message") String message) {
		User user = mainService.findUserById(Long.valueOf(uId));
		User friend = mainService.findUserById(Long.valueOf(fId));
		PrivateMessage finalMessage  = mainService.createPrivateMessage(user, friend, message);
		List<PrivateMessage> messages = mainService.findMessages(user, friend);
		return;
	}


	// // // //
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
	public String editAvatar(@PathVariable(value = "id") Long id, Model model) {
		Avatar avatar = mainService.getAvatarById(id);
		model.addAttribute("avatar", avatar);
		return "/adminPages/editAvatar.jsp";
	}

	// Edit Token Admin
	@GetMapping("/admin/tokens/{id}/edit")
	public String editToken(@PathVariable(value = "id") Long id, Model model) {
		Token token = mainService.getTokenById(id);
		model.addAttribute("token", token);
		return "/adminPages/editToken.jsp";
	}

	// Edit User Admin
	@GetMapping("/admin/user/{id}/edit")
	public String editUser() {
		return "/adminPages/editUser.jsp";
	}

	// New Avatar Admin
	@GetMapping("/admin/newAvatar")
	public String newAvatar(@ModelAttribute("avatar") Avatar avatar) {
		return "/adminPages/newAvatar.jsp";
	}

	// New Token Admin
	@GetMapping("/admin/newToken")
	public String newToken(@ModelAttribute("token") Token token) {
		return "/adminPages/newToken.jsp";
	}

	// ADMIN - POST && PUT CONTROLS //

	@PostMapping("/admin/tokens")
	public String createToken(@Valid @ModelAttribute("token") Token token, BindingResult result) {
		if (result.hasErrors()) {
			return "/adminPages/newToken.jsp";
		} else {
			mainService.createOrUpdateToken(token);
			return "redirect:/admin/tokens";
		}
	}

	@PostMapping("/admin/avatars")
	public String createAvatar(@Valid @ModelAttribute("avatar") Avatar avatar, BindingResult result) {
		if (result.hasErrors()) {
			return "/adminPages/newAvatar.jsp";
		} else {
			mainService.createOrUpdateAvatar(avatar);
			return "redirect:/admin/avatars";
		}
	}

	// UPDATE AVATAR
	@PutMapping("/admin/avatars/{id}/update")
	public String updateAvatar(@Valid @ModelAttribute("avatar") Avatar avatar, @PathVariable(value = "id") Long id,
			BindingResult result) {
		if (result.hasErrors()) {
			return "/adminPages/editAvatar.jsp";
		} else {
			mainService.createOrUpdateAvatar(avatar);
			return "redirect:/admin/avatars";
		}
	}

	// UPDATE TOKEN
	@PutMapping("/admin/tokens/{id}/update")
	public String updateToken(@Valid @ModelAttribute("token") Token token, @PathVariable(value = "id") Long id,
			BindingResult result) {
		if (result.hasErrors()) {
			return "/adminPages/editToken.jsp";
		} else {
			mainService.createOrUpdateToken(token);
			return "redirect:/admin/tokens";
		}
	}
	// // // // // // //
	// END CONTROLLER
}
