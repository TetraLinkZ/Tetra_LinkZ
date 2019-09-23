package com.tetralinkz.tetralinkz.services;

import org.springframework.stereotype.Service;

import com.tetralinkz.tetralinkz.models.MatchHistory;
import com.tetralinkz.tetralinkz.repositories.AvatarRepository;
import com.tetralinkz.tetralinkz.repositories.MatchHistoryRepository;
import com.tetralinkz.tetralinkz.repositories.TokenRepository;
import com.tetralinkz.tetralinkz.repositories.UserRepository;

@Service
public class GameService {
	private final UserRepository userRepo;
	private final TokenRepository tokenRepo;
	private final AvatarRepository avatarRepo;
	private final MatchHistoryRepository historyRepo;
	
	public GameService(UserRepository userRepo, TokenRepository tokenRepo, AvatarRepository avatarRepo,
			MatchHistoryRepository historyRepo) {
		this.userRepo = userRepo;
		this.tokenRepo = tokenRepo;
		this.avatarRepo = avatarRepo;
		this.historyRepo = historyRepo;
	}	
}
