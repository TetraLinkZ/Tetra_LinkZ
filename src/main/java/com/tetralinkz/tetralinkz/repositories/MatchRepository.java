package com.tetralinkz.tetralinkz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tetralinkz.tetralinkz.models.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long>{
	List<Match> findAll();
}
