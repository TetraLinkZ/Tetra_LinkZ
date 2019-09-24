package com.tetralinkz.tetralinkz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tetralinkz.tetralinkz.models.UserAvatar;

@Repository
public interface UserAvatarRepository extends CrudRepository<UserAvatar, Long>{

}
