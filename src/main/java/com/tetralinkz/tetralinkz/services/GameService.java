package com.tetralinkz.tetralinkz.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tetralinkz.tetralinkz.models.Match;
import com.tetralinkz.tetralinkz.repositories.AvatarRepository;
import com.tetralinkz.tetralinkz.repositories.MatchHistoryRepository;
import com.tetralinkz.tetralinkz.repositories.MatchRepository;
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
	private final MatchRepository matchRepo;
	
	public GameService(UserRepository userRepo,MatchRepository matchRepo, TokenRepository tokenRepo, AvatarRepository avatarRepo,
			MatchHistoryRepository historyRepo, UserAvatarRepository uaRepo, UserTokenRepository utRepo) {
		this.userRepo = userRepo;
		this.matchRepo =  matchRepo;
		this.tokenRepo = tokenRepo;
		this.avatarRepo = avatarRepo;
		this.historyRepo = historyRepo;
		this.uaRepo = uaRepo;
		this.utRepo = utRepo;
	}	
	
	public Match newMatch(Match m) {
		return matchRepo.save(m);
	}
	public Match findMatchById(Long id) {
		Optional<Match> o = matchRepo.findById(id);
		if(o.isPresent()) {
			return o.get();
		}else {
			return null;
		}
	}
}
