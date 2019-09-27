package com.tetralinkz.tetralinkz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tetralinkz.tetralinkz.models.Avatar;
import com.tetralinkz.tetralinkz.models.User;
import com.tetralinkz.tetralinkz.models.UserAvatar;

@Repository
public interface UserAvatarRepository extends CrudRepository<UserAvatar, Long>{
	List<UserAvatar> findByUser(User user);
	UserAvatar findByUserAndAvatar(User u, Avatar a);
}
