package com.tetralinkz.tetralinkz.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

//	@PostMapping("/game/play")
//	public Match createBoard() {
//		Match m = new Match();
//		gameService.clearBoard(m);
//		System.out.println("match : " + m);
//		return gameService.newMatch(m);
//	}

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
	
	@PostMapping("/game/play/move")
	public String gameAction(
			Match match, 
			@RequestParam("row")int row, 
			@RequestParam("column")int column,
			@RequestParam("user") User user,
			@RequestParam("player")int oneOrTwo,
			@RequestParam("win") boolean win
			) {
		
		//current player token is player one or two;
		
		// Retrieve the match's current board
		String board = match.getBoard();
		// Start board index at 0 for iteration
		int strBoardIdx = 0;
		// Instantiate matrix for board
		int[][] matrix = new int[7][7];	
		// iterate through the board string and add elements to matrix
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				matrix[i][j] = Character.getNumericValue(board.charAt(strBoardIdx));
				strBoardIdx++;
			}
		}
		
		// place the player one or two token into the row and column;
		matrix[row][column] =  oneOrTwo;
		
		StringBuilder stringMatrix = new StringBuilder();
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 7; y++) {
				stringMatrix.append(matrix[x][y]);
			}
		}
		match.setBoard(stringMatrix.toString());
		gameService.newMatch(match);
		
		
		System.out.println("board after move:" + stringMatrix.toString());
		return stringMatrix.toString();
	}
	
	@PutMapping("/game/play/clear")
	public String clearBoard(Match match) {
		gameService.clearBoard(match);
		return "BOARD CLEARED";
	}
	
	@PostMapping("/post/test")
	public String postTest() {
		System.out.println("TEST POST");
		return "POST tEST!";
	}
	
	@PostMapping("api/credits")
	public void credits(@RequestParam("userId")Long id, Model model) {
		User user = mainService.findUserById(id);
		Integer credits = user.getCredits();
		credits++;
		System.out.println("credits: " + credits);
		user.setCredits(credits++);
		mainService.updateUser(user);
		return;
	}

	@GetMapping("/game/play/board")
	public String gameBoardGet() {
		Match match = gameService.findMatchById(Long.valueOf(2));
		return match.getBoard();
	}
}
