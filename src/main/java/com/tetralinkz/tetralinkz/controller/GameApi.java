package com.tetralinkz.tetralinkz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tetralinkz.tetralinkz.models.Match;
import com.tetralinkz.tetralinkz.models.User;
import com.tetralinkz.tetralinkz.services.GameService;
import com.tetralinkz.tetralinkz.services.MainService;

@RestController
public class GameApi {
	private final GameService gameService;
	private final MainService mainService;

	public GameApi(GameService gameService, MainService mainService) {
		super();
		this.gameService = gameService;
		this.mainService = mainService;
	}

	@PostMapping("/game/play")
	public Match createBoard() {
		Match m = new Match();
		System.out.println("match : " + m);
		return gameService.newMatch(m);
	}

	@PutMapping("/game/play")
	public void setCurrentMatch(User u) {
		User u1 = mainService.findUserById(Long.valueOf(1));
		User u2 = mainService.findUserById(Long.valueOf(2));
		Match m = gameService.findMatchById(Long.valueOf(1));
		u1.setCurrentMatch(m);
		u2.setCurrentMatch(m);
		mainService.updateUser(u1);
		mainService.updateUser(u2);
		return;
	}

}
