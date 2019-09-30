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

	//public List<PrivateMessage> getMessage(Long uid, Long fid){
		
		@Query(
				value="SELECT m from PrivateMessage m WHERE m.user ='"uid"' "
						+ "AND m.friend ='"+fid+"'"
				)
		//List<PrivateMessage> findMessage();
	}

}

// @Query("select m from PrivateMessage where m.user = u AND m.friend = f") 
//List<PrivateMessage> getAllMessages(@Param("u") User u, @Param("f") User f );
/*
 * //public default List<PrivateMessage> getMessages( User u, User f){
 * 
 * @Query("") //} }
 */
