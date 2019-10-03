package com.tetralinkz.tetralinkz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import com.tetralinkz.tetralinkz.models.Match;
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
		Long currentUserId = (Long) session.getAttribute("user");
		User user = mainService.findUserById(currentUserId);
		model.addAttribute("user", user);
		// TEMP //
		Match currentMatch = gameService.findMatchById(Long.valueOf(2));
		// END TEMP //
		
		List<User> players = currentMatch.getPlayers();
		System.out.println(players.get(0).getName());
		
		user.setCurrentMatch(currentMatch);
//		User playerOne = players.get(0);
//		User playerTwo = players.get(1);
		if(players.size()<1) {
			model.addAttribute("playerOne", user);	
			mainService.updateUser(user);
		} else if(players.size() == 1) {
			model.addAttribute("playerTwo", user);	
			mainService.updateUser(user);
		}
		model.addAttribute("match", currentMatch);
		System.out.println("Your current match board: " + currentMatch.getBoard());
		return "game.jsp";
	}
	
	@MessageMapping("/message")
	@SendTo("/game/play")//call in js
	public String sendMessage(Message message) throws Exception{
		System.out.println(message);
		System.out.println(message.getContent());
        return  HtmlUtils.htmlEscape(message.messageOut());
	}
	
	@MessageMapping("/message/avatar")
	@SendTo("/game/play")
	public String getAvatar(Message message) throws Exception{
		return message.avatarSrc();
	}
	
}