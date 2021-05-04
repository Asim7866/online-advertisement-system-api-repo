package com.cg.onlineadvapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.cg.onlineadvapi.domain.Message;
import com.cg.onlineadvapi.exception.NoMessageException;
import com.cg.onlineadvapi.exception.SameSenderException;
import com.cg.onlineadvapi.repository.MessageRepository;
import com.cg.onlineadvapi.serviceImpl.MessageServiceImpl;


class MessageServiceTest {

	/**
	 * It is used to create and inject mocked instances
	 */
	@Mock 
    private MessageRepository messageRepository;
	
	/**
	 *  It allows you to specify a field on which an injection is to be performed.
	 */
    @InjectMocks 
    private MessageServiceImpl messageServiceImpl;
    
    	/**
    	 * Stubs Intializing For Testing
    	 */
    	private Message firstMessage;
		private Message secondMessage;
		private Message thirdMessage;
    	private Message message;
    	List<Message> messageList;
    
    @BeforeEach
    public void setUp() {
    	/**
    	 * It initializes fields annotated with Mockito annotations.
    	 */
    	MockitoAnnotations.initMocks(this); 
    	/**
    	 * Stubs Declaration For Testing
    	 */
    	messageList = new ArrayList<>();
    	firstMessage = new Message(1,1,2,"Hi");
    	secondMessage = new Message(1,1,2,"Hello");
    	thirdMessage = new Message(1,1,1,"Hey");
    	messageList.add(firstMessage);
        messageList.add(secondMessage);
    	message = new Message();
    }
    @AfterEach
    public void tearDown() {
    	/**
    	 * Stubs Clean-Up
    	 */
        messageRepository.deleteAll();
        firstMessage = secondMessage = null;
        messageList = null;
    }	
    
    /**
     * Send Message Given Saved Message
     */
    @Test
    public void test_sendMessage_GivenMessage_ShouldReturnSavedMessages(){
    	BDDMockito.given(messageRepository.save(firstMessage))
    	.willReturn(firstMessage);
    	secondMessage = messageServiceImpl.sendMessage(firstMessage);
    	assertNotNull(secondMessage);
    	assertEquals(1,secondMessage.getAdv_id());
    	assertEquals(1,secondMessage.getSenderId());
    	assertEquals(2,secondMessage.getReceiverId());
    	assertEquals("Hi",secondMessage.getMessage());
    }
    
    /**
     * Send Message
     * @throws Exception
     */
    @Test
    public void test_sendMessage_GivenSameSender_ShouldThrowSameSenderException() throws Exception{
    	BDDMockito.given(messageRepository.save(thirdMessage))
    	.willThrow(new SameSenderException());
    	assertThrows(SameSenderException.class,()->messageServiceImpl.sendMessage(thirdMessage));
    }
    
    
    /**
     * Given Sender Id Should Return 
     */
    @Test
    public void test_messagesSentByUser_GivenSenderId_ShouldReturnMessage() {
    	BDDMockito.given(messageRepository.findBySenderId(message.getSenderId())).
    	willReturn(messageList);
    	List<Message> getMessageList = messageServiceImpl.messageByUserId(message.getSenderId());
    	assertNotNull(getMessageList);
    	assertEquals(2,getMessageList.size());
    	assertEquals(1,getMessageList.get(0).getSenderId());
    	assertEquals(1,getMessageList.get(1).getSenderId());
    }
    
    /**
     * NonExisting sender id throw Exception
     * @throws Exception
     */
    @Test
    public void test_messagesSentByUser_GivenNonExistingSenderId_ShouldThrowNoMessageException() throws Exception{
    	BDDMockito.given(messageRepository.findBySenderId(4))
    	.willThrow(new NoMessageException());
    	assertThrows(NoMessageException.class,()->messageServiceImpl.messageByUserId(4));
    }

    /**
     * Given Message Id Should Delete Message
     */
    @Test
    public void test_deleteMessageByUserIdAndMessageId_GivenUserIdAndMessageId_ShouldDeleteMessageById() {
    	messageRepository.deleteById(message.getMessageId());
    	verify(messageRepository).deleteById(message.getMessageId());
    	assertNull(message.getMessageId());
    	assertNull(message.getSenderId());
    	assertNull(message.getReceiverId());
    	assertNull(message.getMessage());
    }
    
    /**
     * NonExisting Message id Should Throw Exception
     * @throws Exception
     */
    @Test
    public void test_deleteMessageByMessageId_GivenNonExistingMessageId_ShouldThrowNoMessageException() throws Exception{
    	BDDMockito.given(messageRepository.findBySenderId(message.getMessageId()))
    	.willThrow(new NoMessageException());
    	assertThrows(NoMessageException.class,()->messageServiceImpl.deleteMessageByMessageId(1,4));
    }
    
    /**
     * Show All Messages
     */
    @Test
    public void test_showAllMessageByUser_ShouldReturnListOfUserMessages() {
    	BDDMockito.given(messageRepository.findAll()).
    	willReturn(messageList);
    	List<Message> getMessageList = messageServiceImpl.showAllMessage();
    	assertNotNull(messageList);
    	assertNotNull(getMessageList);
    	assertEquals(2,getMessageList.size());
    	assertEquals(firstMessage, getMessageList.get(0));
    	assertEquals(secondMessage, getMessageList.get(1));
    }
    
    /**
     * Throw exception when no Message available
     */
    @Test
    public void test_showAllMessageByUser_ShouldThrowNoMessageException() throws Exception{
    	BDDMockito.given(messageRepository.findAll())
    	.willThrow(new NoMessageException());
    	assertThrows(NoMessageException.class,()->messageServiceImpl.showAllMessage());
    }
    
    /**
     * given advertise id should return messages
     */
    @Test
    public void test_allMessageSentOnAdvertise_GivenAdvertiseId_ShouldReturnListOfAllAdvertiseMessages() {
    	BDDMockito.given(messageRepository.findByAdvertiseId(message.getAdv_id())).
    	willReturn(messageList);
    	List<Message> getMessageList = messageServiceImpl.allMessageSentOnAdvertise(message.getAdv_id());
    	assertNotNull(messageList);
    	assertNotNull(getMessageList);
    	assertEquals(2,getMessageList.size());
    	assertEquals(firstMessage, getMessageList.get(0));
    	assertEquals(secondMessage, getMessageList.get(1));
    }
    
    /**
     * No messages found throw exception
     * @throws Exception
     */
    @Test
    public void test_allMessageSentOnAdvertise_GivenNonExistingAdvertiseId_ShouldThrowNoMessageException() throws Exception{
    	BDDMockito.given(messageRepository.findByAdvertiseId(message.getAdv_id()))
    	.willThrow(new NoMessageException());
    	assertThrows(NoMessageException.class,()->messageServiceImpl.allMessageSentOnAdvertise(4));
    }
    
 
}