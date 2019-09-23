package com.tetralinkz.tetralinkz.controller;

import org.springframework.stereotype.Controller;

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
}
