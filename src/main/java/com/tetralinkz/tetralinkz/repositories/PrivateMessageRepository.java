package com.tetralinkz.tetralinkz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tetralinkz.tetralinkz.models.PrivateMessage;
import com.tetralinkz.tetralinkz.models.User;

@Repository
public interface PrivateMessageRepository extends CrudRepository<PrivateMessage, Long> {

	@Query("select m from PrivateMessage m where m.user = :user AND m.friend = :friend OR m.user = :friend AND m.friend = :user order by createdAt desc")
	List<PrivateMessage> getAllMessages(@Param("user") User uid, @Param("friend") User fid);

}
