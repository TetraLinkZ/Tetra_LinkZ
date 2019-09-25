package com.tetralinkz.tetralinkz.services;

import org.springframework.stereotype.Service;

import com.tetralinkz.tetralinkz.repositories.AvatarRepository;
import com.tetralinkz.tetralinkz.repositories.MatchHistoryRepository;
import com.tetralinkz.tetralinkz.repositories.TokenRepository;
import com.tetralinkz.tetralinkz.repositories.UserAvatarRepository;
import com.tetralinkz.tetralinkz.repositories.UserRepository;
import com.tetralinkz.tetralinkz.repositories.UserTokenRepository;

@Service
public class GameService {
	private final UserRepository userRepo;
	private final TokenRepository tokenRepo;
	private final AvatarRepository avatarRepo;
	private final MatchHistoryRepository historyRepo;
	private final UserAvatarRepository uaRepo;
	private final UserTokenRepository utRepo;
	
	public GameService(UserRepository userRepo, TokenRepository tokenRepo, AvatarRepository avatarRepo,
			MatchHistoryRepository historyRepo, UserAvatarRepository uaRepo, UserTokenRepository utRepo) {
		this.userRepo = userRepo;
		this.tokenRepo = tokenRepo;
		this.avatarRepo = avatarRepo;
		this.historyRepo = historyRepo;
		this.uaRepo = uaRepo;
		this.utRepo = utRepo;
	}	
}
