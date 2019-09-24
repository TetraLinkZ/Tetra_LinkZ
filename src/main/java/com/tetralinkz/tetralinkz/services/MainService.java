package com.tetralinkz.tetralinkz.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.tetralinkz.tetralinkz.models.Avatar;
import com.tetralinkz.tetralinkz.models.Token;
import com.tetralinkz.tetralinkz.models.User;
import com.tetralinkz.tetralinkz.repositories.AvatarRepository;
import com.tetralinkz.tetralinkz.repositories.MatchHistoryRepository;
import com.tetralinkz.tetralinkz.repositories.TokenRepository;
import com.tetralinkz.tetralinkz.repositories.UserRepository;

@Service
public class MainService {
	private final UserRepository userRepo;
	private final TokenRepository tokenRepo;
	private final AvatarRepository avatarRepo;
	private final MatchHistoryRepository historyRepo;
	
	public MainService(UserRepository userRepo, TokenRepository tokenRepo, AvatarRepository avatarRepo,
			MatchHistoryRepository historyRepo) {
		this.userRepo = userRepo;
		this.tokenRepo = tokenRepo;
		this.avatarRepo = avatarRepo;
		this.historyRepo = historyRepo;
	}	
	
	public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
	
    // // // // // // // //
    // ADMIN CRUD SERVICES
    
    // INDEXING
    
    public List<Avatar> allAvatars(){
    	return avatarRepo.findAll();
    }
    
    public List<Token> allTokens() {
    	return tokenRepo.findAll();
    }
    
    //
    
    public Avatar getAvatarById(Long id) {
    	Optional<Avatar> optAvatar = avatarRepo.findById(id);
    	if(optAvatar.isPresent()) {
    		return optAvatar.get();
    	} else {
    		return null;
    	}
    }
    
    public Token getTokenById(Long id) {
    	Optional<Token> optToken = tokenRepo.findById(id);
    	if(optToken.isPresent()) {
    		return optToken.get();
    	} else {
    		return null;
    	}
    }
    
    // CREATE && UPDATE
    public Avatar createOrUpdateAvatar(Avatar avatar) {
    	return avatarRepo.save(avatar);
    }
    
    public Token createOrUpdateToken(Token token) {
    	return tokenRepo.save(token);
    }
}
