package com.tetralinkz.tetralinkz.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

import com.tetralinkz.tetralinkz.models.Message;
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
	public String showGamePage() {
		return "game.jsp";
	}
	
	@MessageMapping("/message")
	@SendTo("/game/play")//call in js
	public String sendMessage(Message message) throws Exception{
		System.out.println(message);
		System.out.println(message.getContent());
        return  HtmlUtils.htmlEscape(message.getContent());


		//return message;
	}
}
