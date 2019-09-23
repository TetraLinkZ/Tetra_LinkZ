package com.tetralinkz.tetralinkz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tetralinkz.tetralinkz.models.MatchHistory;

@Repository
public interface MatchHistoryRepository extends CrudRepository<MatchHistory, Long>{

}
