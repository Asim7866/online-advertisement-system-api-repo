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

	@Mock //- mock bean
    private MessageRepository messageRepository;
	
    @InjectMocks // mock bean injection
    private MessageServiceImpl messageServiceImpl;
//    stubs
    	private Message firstMessage;
		private Message secondMessage;
		private Message thirdMessage;
    	private Message message;
    	List<Message> messageList;
    
    @BeforeEach
    public void setUp() {
    	MockitoAnnotations.initMocks(this); //invoke mock
    	messageList = new ArrayList<>();
    	firstMessage = new Message(1,1,2,"Asim","Hi");
    	secondMessage = new Message(1,1,2,"Asim","Hi");
    	thirdMessage = new Message(1,1,1,"Asim","Hi");
    	messageList.add(firstMessage);
        messageList.add(secondMessage);
    	message = new Message();
    }
    @AfterEach
    public void tearDown() {
        messageRepository.deleteAll();
        firstMessage = secondMessage = null;
        messageList = null;
    }	
    
    /**
     * Send Message Given Saved Message
     */
    @Test
    public void test_sendMessage_GivenMessage_ShouldReturnSavedMessages(){
    	BDDMockito.given(messageRepository.save(message))
    	.willReturn(firstMessage);
    	secondMessage = messageServiceImpl.sendMessage(message);
    	assertNotNull(secondMessage);
    	assertEquals(1,secondMessage.getAdvertiseId());
    	assertEquals(1,secondMessage.getSenderId());
    	assertEquals(2,secondMessage.getReceiverId());
    	assertEquals("Asim",secondMessage.getSenderUserName());
    	assertEquals("Hi",secondMessage.getMessage());
    }
    
    /**
     * Send Message
     * @throws Exception
     */
    @Test
    public void test_sendMessage() throws Exception{
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
     * Invalid sender id throw Exception
     * @throws Exception
     */
    @Test
    public void test_messagesSentByUser() throws Exception{
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
    	assertNull(message.getSenderUserName());
    	assertNull(message.getReceiverId());
    	assertNull(message.getMessage());
    }
    
    /**
     * invalid Message id Should Throw Exception
     * @throws Exception
     */
    @Test
    public void test_deleteMessageByMessageId() throws Exception{
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
    public void test_showAllMessageByUser() throws Exception{
    	BDDMockito.given(messageRepository.findAll())
    	.willThrow(new NoMessageException());
    	assertThrows(NoMessageException.class,()->messageServiceImpl.showAllMessage());
    }
    
    /**
     * given advertise id should return messages
     */
    @Test
    public void test_allMessageSentOnAdvertise_GivenAdvertiseId_ShouldReturnListOfAllAdvertiseMessages() {
    	BDDMockito.given(messageRepository.findByAdvertiseId(message.getAdvertiseId())).
    	willReturn(messageList);
    	List<Message> getMessageList = messageServiceImpl.allMessageSentOnAdvertise(message.getAdvertiseId());
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
    public void test_allMessageSentOnAdvertise() throws Exception{
    	BDDMockito.given(messageRepository.findByAdvertiseId(message.getAdvertiseId()))
    	.willThrow(new NoMessageException());
    	assertThrows(NoMessageException.class,()->messageServiceImpl.allMessageSentOnAdvertise(4));
    }
    
 
}