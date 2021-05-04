package com.cg.onlineadvapi.web;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineadvapi.domain.Message;
import com.cg.onlineadvapi.service.MessageService;
import com.cg.onlineadvapi.serviceImpl.MapValidationErrorService;

/**
 * It is used to create of RESTful web services.
 * 
 * @author mohdansa
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

	/**
	 * It used to autowire MessageService bean automatically.
	 */
	@Autowired
	private MessageService messageService;

	/**
	 * It used to autowire MapValidationErrorService bean automatically.
	 */
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * It handle the HTTP POST requests to send Message to User.
	 * 
	 * @param message
	 * @param bindingResult
	 * @return ResponseEntity<Message>
	 */
	@PostMapping("/add")
	public ResponseEntity<?> sendNewMessage(@Valid @RequestBody Message message, BindingResult bindingResult) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(bindingResult);
		if (errorMap != null) {
			return errorMap;
		}
		Message savedMessage = messageService.sendMessage(message);
		return new ResponseEntity<Message>(savedMessage, HttpStatus.CREATED);

	}

	/**
	 * It handle the HTTP GET requests to get Message of User by userId.
	 * 
	 * @param senderId
	 * @return ResponseEntity<Message>
	 */
	@GetMapping("/sender/{senderId}")
	public ResponseEntity<?> getmessageBySenderIdentifier(@PathVariable Integer senderId) {

		List<Message> message = messageService.messageByUserId(senderId);

		return new ResponseEntity<List<Message>>(message, HttpStatus.OK);
	}

	/**
	 * It handle the HTTP GET requests to get all Message of User by advertiseId.
	 * 
	 * @param advertiseId
	 * @return ResponseEntity<Message>
	 */
	@GetMapping("/advertise/{advertiseId}")
	public ResponseEntity<?> getAllMessagesByAdvertiseId(@PathVariable Integer advertiseId) {
		List<Message> messageList = messageService.allMessageSentOnAdvertise(advertiseId);
		return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
	}

	/**
	 * It handle the HTTP DELETE requests to delete a Message of User.
	 * 
	 * @param senderId
	 * @param messageId
	 * @return ReponseEntity<String>
	 */
	@DeleteMapping("/{senderId}/{messageId}")
	public ResponseEntity<?> deleteMessage(@PathVariable Integer senderId, @PathVariable Integer messageId) {
		messageService.deleteMessageByMessageId(senderId, messageId);
		return new ResponseEntity<String>("Message was deleted successfully", HttpStatus.OK);
	}

	/**
	 * It handle the HTTP GET requests to get all the Message.
	 * 
	 * @return list of Messages.
	 */
	@GetMapping("/all")
	public List<Message> showAllMessages() {
		return messageService.showAllMessage();
	}
}
