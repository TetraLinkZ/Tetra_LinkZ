package com.tetralinkz.tetralinkz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tetralinkz.tetralinkz.models.UserToken;

@Repository
public interface UserTokenRepository extends CrudRepository<UserToken, Long>{
	
}
