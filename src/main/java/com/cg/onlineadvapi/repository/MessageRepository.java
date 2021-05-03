package com.cg.onlineadvapi.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.onlineadvapi.domain.Message;
/**
 * ___________As___________
 * @author mohdansa
 *
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

	/**
	 * It find Message send by user.
	 * @param senderId
	 * @return Message
	 */
	
	List<Message> findBySenderId(Integer senderId);
	
	/**
	 * It find list of Message of Advertise.
	 * @param advertiseId
	 * @return List of Message
	 */
	@Query("select a from Message a where a.adv_id=:advertiseId")
	List<Message> findByAdvertiseId(Integer advertiseId);

	/**
	 * It find Message by Message Id.
	 * @param messageId
	 * @return Message
	 */
	Message findByMessageId(Integer messageId);
	
}