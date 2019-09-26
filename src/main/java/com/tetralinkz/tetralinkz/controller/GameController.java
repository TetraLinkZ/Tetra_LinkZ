package com.tetralinkz.tetralinkz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import com.tetralinkz.tetralinkz.models.Message;
import com.tetralinkz.tetralinkz.models.User;
import com.tetralinkz.tetralinkz.services.GameService;
import com.tetralinkz.tetralinkz.services.MainService;

@Controller
public class GameController {
	private final GameService gameService;
	private final MainService mainService;
	
	public GameController(GameService gameService, MainService mainService) {
		this.gameService = gameService;
		this.mainService = mainService;
	}
	
	@GetMapping("/game/play")
	public String showGamePage(HttpSession session, Model model) {
		
		Long userId = (Long) session.getAttribute("user");
		User currentUser = mainService.findUserById(userId);
		model.addAttribute("user", currentUser);
		return "game.jsp";
	}
	
	@MessageMapping("/message")
	@SendTo("/game/play")//call in js
	public String sendMessage(Message message) throws Exception{
		System.out.println(message);
		System.out.println(message.getContent());
        return  HtmlUtils.htmlEscape(message.messageOut());

        
		//return message;
	}
}
