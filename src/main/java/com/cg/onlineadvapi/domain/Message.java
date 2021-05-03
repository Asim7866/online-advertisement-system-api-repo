package com.cg.onlineadvapi.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Integer adv_id;
	/**
	 * This Field is used to Identify Receiver User.
	 */
	private Integer receiverId;
	/**
	 * sender ID
	 */
	private Integer senderId;
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
	 * This Field is used to Identify Sender User.
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id" ,referencedColumnName = "userid")
	private User user;
	
  
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
	public Message(Integer adv_id,Integer senderId, Integer receiverId,
			@Size(min = 1, max = 160, message = "Message Should be between 1 to 160 character") String message) {
		super();
		this.adv_id = adv_id;
		this.senderId=senderId;
		this.receiverId = receiverId;
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
	public void setAdv_id(Integer adv_id) {
		this.adv_id = adv_id;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public Integer getAdv_id() {
		return adv_id;
	}
	/**
	 * It Configure a Callback for PreInsert Events of the Field.
	 */
  @PrePersist
	protected void sendOn() {
		this.send_At = new Date();
	}
  
	@Override
	public String toString() {
		return "Message with values : {messageId=" + messageId + ", advertiseId=" + adv_id + ""
				+ ", receiverId=" + receiverId
				+ ", senderId=" + senderId + ", "
				+ "message=" + message
				+ ", send_At=" + send_At + ",}";
	}
  
  


}