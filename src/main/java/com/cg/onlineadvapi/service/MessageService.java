package com.cg.onlineadvapi.service;
import java.util.List;
import com.cg.onlineadvapi.domain.Message;
/**
 * This Interface contains All the Business Logic.
 * @author mohdansa
 */
public interface MessageService {

	/**
	 * It is used to send a Message to a user.
	 * @param message
	 * @return message
	 */
	Message sendMessage(Message message);

	/**
	 * It is used to view Message for user.
	 * @param senderId
	 * @return message
	 */
	List<Message> messageByUserId(Integer senderId);

	/**
	 * It is used to delete a Message.
	 * @param messageId
	 */
	void deleteMessageByMessageId(Integer senderId,Integer messageId);

	/**
	 * It return all the Message.
	 * @return List of Messages
	 */
	List<Message> showAllMessage();

	/**
	 * It return all the Message of Advertise.
	 * @return List of Messages of Advertise
	 */
	List<Message> allMessageSentOnAdvertise(Integer advertiseId);

}
