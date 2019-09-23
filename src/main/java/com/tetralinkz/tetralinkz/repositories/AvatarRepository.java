package com.tetralinkz.tetralinkz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tetralinkz.tetralinkz.models.Avatar;

@Repository
public interface AvatarRepository extends CrudRepository<Avatar, Long>{

}
