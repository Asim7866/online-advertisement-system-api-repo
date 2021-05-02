package com.cg.onlineadvapi.serviceImpl;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineadvapi.domain.Message;
import com.cg.onlineadvapi.exception.NoMessageException;
import com.cg.onlineadvapi.exception.SameSenderException;
import com.cg.onlineadvapi.repository.MessageRepository;
import com.cg.onlineadvapi.service.MessageService;

/**
 * This Class implements all the function of the Service Interface.
 * @author mohdansa
 */
@Service
public class MessageServiceImpl implements MessageService{

	/**
	 * It is used to implement Logger.
	 */
	Logger logger =LoggerFactory.getLogger(MessageServiceImpl.class);
	
	/**
	 * It used to autowire MessageRepository bean automatically.
	 */
	@Autowired
	MessageRepository messageRepository;

	
	/**
	 * It is used to send a Message to a user.
	 * @param message
	 * @return message
	 */
	@Override
	public Message sendMessage(Message message) {
		logger.info("Sending Message Service Starting");
		logger.info("Sending Message Service Started");
		Message savedMessage = messageRepository.save(message);
		logger.info("Checking Sender and Reciever");
		if(savedMessage.getSenderId().equals(savedMessage.getReceiverId())) 
		{
			logger.info("Sender and Reciever Id is Same");
			logger.error("Sender "+message.getSenderUserName()+" is trying to send message to himself");	
			throw new SameSenderException("User cannot send message to himself");
			}
			logger.info("Message has been send by "+message.getSenderUserName());
			logger.info("Sending Message Service Closing" );
			logger.info("Sending Message Service Closed" );
			return savedMessage;
	}

	/**
	 * It is used to view Message for user.
	 * @param senderId
	 * @return message
	 */
	@Override
	public List<Message> messageByUserId(Integer senderId) {
		logger.info("View Message By User Service Starting");
		logger.info("View Message By User Service Started");
		logger.info("Getting Messages of User");
		List<Message> message = messageRepository.findBySenderId(senderId);
		logger.info("Checking Message of User");
		if(message.isEmpty()) {
			logger.info("No Message is Found Of User");
			logger.error("No Messages doesn't Exist for user with userid "+ senderId);
			throw new NoMessageException("User with userId "+senderId+" not Valid");
		}
		logger.info("Message has been Viewed by "+message.get(0).getSenderUserName());
		logger.info("Getting Message By User Service Closing");
		logger.info("Getting Message By User Service Closed");
		return message;
	}
	
	/**
	 * It is used to delete a Message.
	 * @param senderId
	 * @param messageId
	 */
	@Override
	public void deleteMessageByMessageId(Integer senderId,Integer messageId) {
		logger.info("Deleting Message of User Service Starting");
		logger.info("Deleting Message of User Service Started");
		logger.info("Getting User");
		List<Message> userMessages = messageByUserId(senderId);
		logger.info("Getting User Messages");
		Message foundMessage =new Message();
		for (Message message : userMessages) {
		foundMessage= messageRepository.findByMessageId(message.getMessageId());
		logger.info("Checking Messages of User");
		if(foundMessage==null) {
			logger.info("No Message Found");
			logger.error("Message Id "+messageId+" doesnt Exist");
			throw new NoMessageException("Can not delete Message.This message doesn't exist");
		}
		if(!message.getMessage().equals(messageId)){
			logger.info("No Message Found");
			logger.error("Message Id "+messageId+" doesnt Exist");
			throw new NoMessageException("Can not delete Message.This message doesn't exist");
		}
		}
		logger.info("Message with MessageId "+messageId+" deleted");
		messageRepository.delete(foundMessage);
		logger.info("Deleting Message of User Service Closing");
		logger.info("Deleting Message of User Service Closed");
	}
	
	/**
	 * It return all the Message.
	 * @return List of Messages
	 */
	@Override
	public List<Message> showAllMessage() {
		logger.info("Show All Messages Service Starting");
		logger.info("Show All Messages Service Started");
		logger.info("Getting Messages");
		List<Message> allMessageList = messageRepository.findAll();
		logger.info("Checking All Messages");
		if(allMessageList.isEmpty()==true) {
			logger.info("No Messages Found");
			logger.error("No Message Available");
			throw new NoMessageException("No Messages Found");
		}
		logger.info("All Messages Available");
		logger.info("Show All Messages Service Closing");
		logger.info("Show All Messages Service Closed");
		return allMessageList;
	}

	/**
	 * It return all the Message of Advertise.
	 * @return List of Messages of Advertise
	 */
	@Override
	public List<Message> allMessageSentOnAdvertise(Integer advertiseId) {
		logger.info("View All Messages Of Advertise Service Starting");
		logger.info("View All Messages Of Advertise Service Started");
		logger.info("Getting Messages of Advertise");
		List<Message> messageList = messageRepository.findByAdvertiseId(advertiseId);
		logger.info("Checking Messages of Advertise");
		if(messageList.isEmpty()) {
			logger.info("No Messages Found");
			logger.error("No Message Available for Advertise with ID "+advertiseId);
			throw new NoMessageException("No Messages Found");
		}
		logger.info("All Messages Available for Advertise with ID "+advertiseId);
		logger.info("View All Messages Of Advertise Service Closing");
		logger.info("View All Messages Of Advertise Service Closed");
		return messageList;
	}
	

	


	
	
	
	

}
