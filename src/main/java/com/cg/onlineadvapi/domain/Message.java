package com.cg.onlineadvapi.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Timestamp;

/**
 * This Class is a Model Class for Message Entity Table
 * @author mohdansa
 */
@Entity
@Table(name = "messages")
public class Message {

	/**
  * This Field is used to Identify Message : Auto Generated.
  * This field is used to identify Message : Auto Generated
  */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageId;
	/**
   * This Field is used to Identify Advertise.
	 */
	private Integer advertiseId;
	/**
	 * This Field is used to Identify Sender User.
	 */
	private Integer senderId;
	/**
	 * This Field is used to Identify Receiver User.
	 */
	private Integer receiverId;
	/**
	 * This Field is used to Identify Sender User Name.
	 */
	private String senderUserName;
	/**
   * This field is used to identify Advertise
	 */
	private Integer advertiseId;
	/**
	 * This field is used to identify Sender User
	 */
	private Integer senderId;
	/**
	 * This field is used to identify Reciever User
	 */
	private Integer recieverId;
	/**
	 * This field is used to identify Sender User Name
	 */
	private String senderUserName;
	/**
  * This field to get Message 
  */
	@NotBlank(message = "Message should not be Blank")
	@Size(min=1 , max=160,message = "Message Should be between 1 to 160 character")
	private String message;
	/**
  * This Field is used to get Send Date.
  */
	@JsonFormat(pattern="HH:mm dd-MM-yyyy")
	private Date send_At;
	
	
  /**
  * Constructor without Parameter.
  * Constructor without parameter
  */
	public Message() {
		super();
	}
  /**
	 * Constructor with Parameter used for Service Class testing.
	 * @param advertiseId
	 * @param senderId
	 * @param receiverId
	 * @param senderUserName
	 * @param message
	 */
	public Message(Integer advertiseId, Integer senderId, Integer receiverId, String senderUserName,
			@Size(min = 1, max = 160, message = "Message Should be between 1 to 160 character") String message) {
		super();
		this.advertiseId = advertiseId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.senderUserName = senderUserName;
		this.message = message;
	}



	/**
	 * Getters and Setters.
   */
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getAdvertiseId() {
		return advertiseId;
	}
	public void setAdvertiseId(Integer advertiseId) {
		this.advertiseId = advertiseId;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId= senderId;
	}
public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
  }
	public String getSenderUserName() {
		return senderUserName;
	}
	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getSend_At() {
		return send_At;
	}
	public void setSend_At(Date send_At) {
		this.send_At = send_At;
	}
	public Date getSeen_At() {
		return seen_At;
	}

	
/**
	 * It Configure a Callback for PreInsert Events of the Field.
	 */
  @PrePersist
	protected void sendOn() {
		this.send_At = new Date();
	}


}