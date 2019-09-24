package com.tetralinkz.tetralinkz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tetralinkz.tetralinkz.models.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {
	List<Token> findAll();
}
