package com.tetralinkz.tetralinkz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tetralinkz.tetralinkz.models.User;
import com.tetralinkz.tetralinkz.models.UserToken;

@Repository
public interface UserTokenRepository extends CrudRepository<UserToken, Long>{
	List<UserToken> findByUser(User user);
}
